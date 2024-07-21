package com.example.reporting_test.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import com.example.reporting_test.model.GenerateNoteTransactionData;
import com.example.reporting_test.util.Table;

@Service
public class MarkdownUtilService {

    public String generateMarkdown(GenerateNoteTransactionData data) {
        Table.Builder tableBuilder = new Table.Builder();
        tableBuilder.addRow("NO.", "NO.PLU", "NAME", "QTY", "HARGA SATUAN", "NETTO");
        for (int i = 0; i < data.getTransactions().size(); i++) {
            var transaction = data.getTransactions().get(i);
            tableBuilder.addRow(i + 1, transaction.getNoPlu(), transaction.getProductName(), transaction.getQuantity(), transaction.getHargaSatuanFormatCurrency(), transaction.getNettoFormatCurrency());
        }
        String[] rows = tableBuilder.build().serialize().split("\n");
        String headerRow = rows[0];
        int headerLength = headerRow.length();

        // Builder Info Store
        final String userStoreName = data.getSeller();
        final String storeName = data.getStore();

        // Created
        final String date = "TANGGAL : " + data.getDateCreatedNoteFormat();
        final String time = "JAM     : " + data.getTimeCreatedNoteFormat();
        final String page = "HALAMAN :    " + data.getPageNote() +"    ";

        // Info Note
        final String dateNote = "TANGGAL  : " + data.getDateTransactionNoteFormat();
        final String numberNote = "NOMOR    : " + data.getTransactionIdNote();
        final String customerNote = "PELANGGAN: " + data.getCustomerNameNote();
        final String addressNote = "ALAMAT   : " + data.getCustomerAddressNote();


        // Total
        final String subTotal = data.getSubTotalFormat();
        final String pengurangan = data.getPotonganFormat();
        final String total = data.getTotalFormat();

        String customerName = "(" + data.getCustomerName() + ")";
        final String name = "(           )";

        StringBuilder stringBuilder = new StringBuilder();
        // Row 1
        stringBuilder.append(userStoreName);
        stringBuilder.append(" ".repeat(headerLength - 2 - userStoreName.length() - date.length()));
        stringBuilder.append(date);
        stringBuilder.append("\n");

        // Row 2
        stringBuilder.append(storeName);
        stringBuilder.append(" ".repeat(headerLength - 2 - storeName.length() - time.length()));
        stringBuilder.append(time);
        stringBuilder.append("\n");

        // Row 3
        stringBuilder.append(" ".repeat(headerLength - 2 - page.length()));
        stringBuilder.append(page);
        stringBuilder.append("\n\n");

        // Row 4 Note
        stringBuilder.append(" ".repeat(((headerLength) / 2) - "NOTA PENJUALAN".length()));
        stringBuilder.append("NOTA PENJUALAN");
        stringBuilder.append("\n");

        // Row 5 Note
        stringBuilder.append(" ".repeat(((headerLength) / 2) - "NOTA PENJUALAN".length()));
        stringBuilder.append(dateNote);
        stringBuilder.append("\n");

        // Row 6 Note
        stringBuilder.append(" ".repeat(((headerLength) / 2) - "NOTA PENJUALAN".length()));
        stringBuilder.append(numberNote);
        stringBuilder.append("\n");

        // Row 7 Note
        stringBuilder.append(" ".repeat(((headerLength) / 2) - "NOTA PENJUALAN".length()));
        stringBuilder.append(customerNote);
        stringBuilder.append("\n");

        // Row 8 Note
        stringBuilder.append(" ".repeat(((headerLength) / 2) - "NOTA PENJUALAN".length()));
        stringBuilder.append(addressNote);
        stringBuilder.append("\n\n");

        // Table
        stringBuilder.append(tableBuilder.build());
        stringBuilder.append("\n\n\n\n\n\n");

        // Subtotal
        stringBuilder.append("-".repeat(headerLength));
        stringBuilder.append("\n");
        stringBuilder.append(" ".repeat(((headerLength) / 3) - "SUB TOTAL".length()));
        stringBuilder.append("SUB TOTAL");
        stringBuilder.append(" ".repeat(headerLength - subTotal.length() - (headerLength) / 3));
        stringBuilder.append(subTotal);
        stringBuilder.append("\n");
        stringBuilder.append("-".repeat(headerLength));
        stringBuilder.append("\n");
        stringBuilder.append(" ".repeat(((headerLength) / 3) - 1 - "POTONGAN".length()));
        stringBuilder.append("POTONGAN");
        stringBuilder.append(" ".repeat(headerLength + 1 - pengurangan.length() - (headerLength) / 3));
        stringBuilder.append(pengurangan);
        stringBuilder.append("\n");
        stringBuilder.append("-".repeat(headerLength));
        stringBuilder.append("\n");
        stringBuilder.append(" ".repeat(((headerLength) / 3) - 4 - "TOTAL".length()));
        stringBuilder.append("TOTAL");
        stringBuilder.append(" ".repeat(headerLength + 4 - total.length() - (headerLength) / 3));
        stringBuilder.append(total);
        stringBuilder.append("\n");
        stringBuilder.append("-".repeat(headerLength));
        stringBuilder.append("\n");
        stringBuilder.append("\n");

        
        if (customerName.length() > 20) {
            customerName = customerName.split(" ")[0] + ")";
        }
        //Signature
        stringBuilder.append(" ".repeat(((headerLength) / 4) - "PELANGGAN".length()));
        stringBuilder.append("PELANGGAN");
        stringBuilder.append(" ".repeat(headerLength - "HORMAT KAMI".length() - ((headerLength) / 4) - "PELANGGAN".length()));
        stringBuilder.append("HORMAT KAMI");
        stringBuilder.append("\n\n\n\n");
        stringBuilder.append(" ".repeat(((headerLength) / 4) - customerName.length()));
        stringBuilder.append(customerName);
        stringBuilder.append(" ".repeat(headerLength - name.length() - ((headerLength) / 4) - customerName.length()));
        stringBuilder.append(name);

        System.out.println(stringBuilder);
        System.out.println(headerRow.length());

        return stringBuilder.toString();

        
    }

    public static void exportStringToFile(String content, String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Files.write(path, content.getBytes()); // Specify character encoding (optional)
    }
}

