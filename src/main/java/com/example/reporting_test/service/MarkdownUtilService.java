package com.example.reporting_test.service;

import com.example.reporting_test.model.TransactionData;
import com.example.reporting_test.util.Table;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class MarkdownUtilService {

    public void generateMarkdown(List<TransactionData> transactionDataList) {
        Table.Builder tableBuilder = new Table.Builder();
        tableBuilder.addRow("NO.", "NO.PLU", "NAME", "QTY", "HARGA SATUAN", "NETTO");
        for (int i = 0; i < transactionDataList.size(); i++) {
            var transaction = transactionDataList.get(i);
            tableBuilder.addRow(i + 1, transaction.getNoPlu(), transaction.getProductName(), transaction.getQuantity(), transaction.getPrice(), transaction.getTotalPrice());
        }
        String[] rows = tableBuilder.build().serialize().split("\n");
        String headerRow = rows[0];
        int headerLength = headerRow.length();

        // Builder Info Store
        final String userStoreName = "D'BEST FATMAWATI";
        final String storeName = "PS.SWALAYAN & DEPT.STORE";

        // Created
        final String date = "TANGGAL : 22/06/24";
        final String time = "JAM     : 16:35:41";
        final String page = "HALAMAN :    1    ";

        // Info Note
        final String dateNote = "TANGGAL  : 03/01/22";
        final String numberNote = "NOMOR    : 001-727254";
        final String customerNote = "PELANGGAN: SI254     -H.ACEP ";
        final String addressNote = "ALAMAT   : Jl. Panglima Sudirman no 21";


        // Total
        final String subTotal = "397.930";
        final String pengurangan = "0";
        final String total = "397.930";

        final String customerName = "(H.ACEP)";
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

        try {
            exportStringToFile(stringBuilder.toString(), "my_export.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exportStringToFile(String content, String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Files.write(path, content.getBytes()); // Specify character encoding (optional)
    }
}
