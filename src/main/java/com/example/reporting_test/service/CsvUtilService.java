package com.example.reporting_test.service;

import java.io.IOException;
import java.io.StringWriter;

import org.springframework.stereotype.Service;

import com.example.reporting_test.model.GenerateNoteTransactionData;
import com.example.reporting_test.model.TransactionData;
import com.opencsv.CSVWriter;

@Service
public class CsvUtilService {

    public String generateCsv(GenerateNoteTransactionData transactionData) throws IOException 
    {
        // Info general
        String[] row1Note = {transactionData.getSeller(), " ", " ", " ", "TANGGAL", transactionData.getDateCreatedNoteFormat()};
        String[] row2Note = {transactionData.getStore(), " ", " ", " ", "JAM",transactionData.getTimeCreatedNoteFormat()};
        String[] row3Note = {" ", " ", " ", " ", "TANGGAL", transactionData.getDateCreatedNoteFormat()};
        
        // Space
        String[] spaceNote = {"", " ", " ", " ", " ", ""};

        // Info Note
        String[] row5Note = {" ", " ", "NOTA PENJUALAN", " ", " ", ""};
        String[] row6Note = {" ", " ", "TANGGAL", transactionData.getDateTransactionNoteFormat(), " ", ""};
        String[] row7Note = {" ", " ", "NOMOR", transactionData.getTransactionIdNote(), " ", ""};
        String[] row8Note = {" ", " ", "PELANGGAN", transactionData.getCustomerNameNote(), " ", ""};
        String[] row9Note = {" ", " ", "ALAMAT", transactionData.getCustomerAddressNote(), " ", ""};

        // Table HEADER
        String[] headerCSV = {"NO.", "NO.PLU", "NAMA", "QTY", "H.STN", "NETTO"};


        // Total
        String[] subTotalRow = {" ", " ", " ", " ", "SUBTOTAL", transactionData.getSubTotalFormat()};
        String[] potonganRow = {" ", " ", " ", " ", "POTONGAN", transactionData.getPotonganFormat()};
        String[] totalRow = {" ", " ", " ", " ", "TOTAL", transactionData.getTotalFormat()};

        // Signature
        String[] row1Signature = {" ", "PELANGGAN", " ", " ", "HORMAT KAMI", " "};
        String[] row2Signature = {" ", "( " + transactionData.getCustomerName() +" )", " ", " ", "(       )", " "};

        // Generate to string
        StringBuilder csvString = new StringBuilder();
        try (StringWriter sw = new StringWriter(); CSVWriter csvWriter = new CSVWriter(sw)) {
            // General Info
            csvWriter.writeNext(row1Note);
            csvWriter.writeNext(row2Note);
            csvWriter.writeNext(row3Note);
            csvWriter.writeNext(spaceNote);

            // Info Note
            csvWriter.writeNext(row5Note);
            csvWriter.writeNext(row6Note);
            csvWriter.writeNext(row7Note);
            csvWriter.writeNext(row8Note);
            csvWriter.writeNext(row9Note);

            //Header TABLE CSV
            csvWriter.writeNext(headerCSV);
            // Looping data list table
            var index = 1;
            for (TransactionData data : transactionData.getTransactions() ){
                String[] dataItem = {index + ".", data.getNoPlu(), data.getProductName(), data.getQuantity().toString(), data.getHargaSatuanFormatCurrency(), data.getNettoFormatCurrency()};
                csvWriter.writeNext(dataItem);

                index += 1;
            }


            // Total
            csvWriter.writeNext(subTotalRow);
            csvWriter.writeNext(potonganRow);
            csvWriter.writeNext(totalRow);
            csvWriter.writeNext(spaceNote);
            csvWriter.writeNext(spaceNote);

            // Signature
            csvWriter.writeNext(row1Signature);
            csvWriter.writeNext(spaceNote);
            csvWriter.writeNext(row2Signature);

            // Append all to stringbuilder
            csvString.append(sw.toString());
        }

        return csvString.toString();
    }

}
