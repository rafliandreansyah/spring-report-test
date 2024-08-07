package com.example.reporting_test.service;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.reporting_test.model.GenerateNoteTransactionData;
import com.example.reporting_test.model.TransactionData;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PdfUtilService {

    private void writeHeader(PdfPTable header1Table, String name, String store, String date, String time, String page) {
        PdfPCell cell = new PdfPCell();
        cell.setBorderColor(Color.white);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setSize(12);
        font.setColor(Color.black);

        cell.setPhrase(new Phrase(name, font));
        header1Table.addCell(cell);


        cell.setPhrase(new Phrase("Tanggal", font));
        header1Table.addCell(cell);
        cell.setPhrase(new Phrase(": " + date, font));
        header1Table.addCell(cell);

        cell.setPhrase(new Phrase(store, font));
        header1Table.addCell(cell);

        cell.setPhrase(new Phrase("Jam", font));
        header1Table.addCell(cell);
        cell.setPhrase(new Phrase(": " + time, font));
        header1Table.addCell(cell);

        cell.setPhrase(new Phrase("", font));
        header1Table.addCell(cell);

        cell.setPhrase(new Phrase("Halaman", font));
        header1Table.addCell(cell);
        cell.setPhrase(new Phrase(": " + page, font));
        header1Table.addCell(cell);
    }

    private void writeNoteHeader(PdfPTable noteTable, String date, String notaNo, String customer, String address) {
        PdfPCell cell = new PdfPCell();
        cell.setBorderColor(Color.white);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setSize(12);
        font.setColor(Color.black);

        // TANGGAL
        cell.setPhrase(new Paragraph("", font));
        noteTable.addCell(cell);
        cell.setPhrase(new Paragraph("TANGGAL", font));
        noteTable.addCell(cell);
        cell.setPhrase(new Paragraph(": " + date, font));
        noteTable.addCell(cell);

        // NOMOR
        cell.setPhrase(new Paragraph("", font));
        noteTable.addCell(cell);
        cell.setPhrase(new Paragraph("NOMOR", font));
        noteTable.addCell(cell);
        cell.setPhrase(new Paragraph(": " + notaNo, font));
        noteTable.addCell(cell);

        // PELANGGAN
        cell.setPhrase(new Paragraph("", font));
        noteTable.addCell(cell);
        cell.setPhrase(new Paragraph("PELANGGAN", font));
        noteTable.addCell(cell);
        cell.setPhrase(new Paragraph(": " + customer, font));
        noteTable.addCell(cell);

        // ALAMAT
        cell.setPhrase(new Paragraph("", font));
        noteTable.addCell(cell);
        cell.setPhrase(new Paragraph("ALAMAT", font));
        noteTable.addCell(cell);
        cell.setPhrase(new Paragraph(": " + address, font));
        noteTable.addCell(cell);


    }

    private void writeTransactionHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.gray);
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setSize(12);
        font.setColor(Color.white);

        cell.setPhrase(new Phrase("NO.", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("NO.PLU", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("NAMA", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("QTY", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("H.STN", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("NETTO", font));
        table.addCell(cell);
    }

    private void writeTransaction(PdfPTable table, List<TransactionData> transactionDataList) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setSize(12);
        font.setColor(Color.black);

        var index = 1;
        for (TransactionData transaction : transactionDataList) {
            cell.setPhrase(new Phrase(index + ".", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(transaction.getNoPlu(), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(transaction.getProductName(), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(String.valueOf(transaction.getQuantity()), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(String.valueOf(transaction.getHargaSatuanFormatCurrency()), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(String.valueOf(transaction.getNettoFormatCurrency()), font));
            table.addCell(cell);
            index += 1;
        }

    }

    private void writeSubtotal(PdfPTable table, String subtotalFormat, String potonganFormat, String totalFormat) {

        PdfPCell cell = new PdfPCell();
        cell.setPadding(2);

        PdfPCell cellWhite = new PdfPCell();
        cellWhite.setBorderColor(Color.white);
        cellWhite.setPadding(2);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setSize(12);
        font.setColor(Color.black);

        var subTotal = subtotalFormat;
        var potongan = potonganFormat;

        // SUB TOTAL
        cellWhite.setPhrase(new Phrase("", font));
        table.addCell(cellWhite);
        cell.setPhrase(new Phrase("SUB TOTAL", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase(subTotal, font));
        table.addCell(cell);

        // POTONGAN
        cellWhite.setPhrase(new Phrase("", font));
        table.addCell(cellWhite);
        cell.setPhrase(new Phrase("POTONGAN", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase(potongan, font));
        table.addCell(cell);

        // TOTAL
        cellWhite.setPhrase(new Phrase("", font));
        table.addCell(cellWhite);
        cell.setPhrase(new Phrase("TOTAL", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase(totalFormat, font));
        table.addCell(cell);
    }


    private void writeTransactionSignature(PdfPTable table, PdfPTable table2, String customerName){
        PdfPCell cellWhite = new PdfPCell();
        cellWhite.setBorderColor(Color.white);
        cellWhite.setPadding(2);
        cellWhite.setHorizontalAlignment(Element.ALIGN_CENTER);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setSize(12);
        font.setColor(Color.black);

        cellWhite.setPhrase(new Phrase("PELANGGAN", font));
        table.addCell(cellWhite);
        cellWhite.setPhrase(new Phrase("HORMAT KAMI", font));
        table.addCell(cellWhite);


        table2.setSpacingBefore(50);
        cellWhite.setPhrase(new Phrase("( " + customerName + " )", font));
        table2.addCell(cellWhite);
        cellWhite.setPhrase(new Phrase("(              )", font));
        table2.addCell(cellWhite);
    }

    public void exportReport(HttpServletResponse response, GenerateNoteTransactionData data) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setSize(24);
        font.setColor(Color.black);

        // Header Table
        PdfPTable header1Table = new PdfPTable(3);
        header1Table.setWidthPercentage(100f);
        header1Table.setWidths(new float[]{7f, 1f, 2f});
        header1Table.setSpacingBefore(10);
        writeHeader(header1Table, "D'BEST FATMAWATI", "PS.SWALAYAN & DEPT.STORE", data.getDateCreatedNoteFormat(), data.getTimeCreatedNoteFormat(), "1");
        document.add(header1Table);


        // Nota
        Paragraph p = new Paragraph("NOTA PENJUALAN");
        p.setSpacingBefore(30);
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);
        // Nota Table
        PdfPTable notaTable = new PdfPTable(3);
        notaTable.setWidthPercentage(100f);
        notaTable.setWidths(new float[]{2.6f, 1.4f, 4f});
        notaTable.setSpacingBefore(5);
        writeNoteHeader(notaTable, data.getDateTransactionNoteFormat(), data.getTransactionIdNote(), data.getCustomerNameNote(), "");
        document.add(notaTable);


        // Transaction Table
        PdfPTable transactionTable = new PdfPTable(6);
        transactionTable.setWidthPercentage(100f);
        transactionTable.setWidths(new float[]{1f, 2f, 3.5f, 2f, 2f, 2f});
        transactionTable.setSpacingBefore(10);
        writeTransactionHeader(transactionTable);
        writeTransaction(transactionTable, data.getTransactions());
        document.add(transactionTable);

        // Transaction table total
        PdfPTable transactionTotalTable = new PdfPTable(3);
        transactionTotalTable.setWidthPercentage(100f);
        transactionTotalTable.setWidths(new float[]{5.5f, 1.5f, 2f});
        transactionTotalTable.setSpacingBefore(10);
        writeSubtotal(transactionTotalTable, data.getSubTotalFormat(), data.getPotonganFormat(), data.getTotalFormat());
        document.add(transactionTotalTable);


        // Signature table
        PdfPTable signtureTable = new PdfPTable(2);
        signtureTable.setWidthPercentage(100f);
        signtureTable.setWidths(new float[]{1f, 1f});
        signtureTable.setSpacingBefore(100);

        PdfPTable signtureTable2 = new PdfPTable(2);
        signtureTable2.setWidthPercentage(100f);
        signtureTable2.setWidths(new float[]{1f, 1f});
        signtureTable2.setSpacingBefore(10);
        writeTransactionSignature(signtureTable, signtureTable2, data.getCustomerName());
        document.add(signtureTable);
        document.add(signtureTable2);

        document.close();
    }

}
