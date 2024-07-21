package com.example.reporting_test.model;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GenerateNoteTransactionData {

    private String seller;
    private String store;
    private LocalDateTime dateCreatedNote;
    private LocalDateTime timeCreatedNote;
    private Integer pageNote;
    private LocalDateTime dateTransactionNote;
    private String transactionIdNote;
    private String customerNameNote;
    private String customerAddressNote;
    private String customerNote;
    private Double subtotal;
    private Double potongan;
    private String customerName;
    private List<TransactionData> transactions;

    public String getDateCreatedNoteFormat() {
        String patternFormat = "dd/MM/yy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternFormat);
        return dateCreatedNote.format(formatter);
    }

    public String getTimeCreatedNoteFormat() {
        String patternFormat = "HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternFormat);
        return timeCreatedNote.format(formatter);
    }

    public String getDateTransactionNoteFormat() {
        String patternFormat = "dd/MM/yy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternFormat);
        return dateTransactionNote.format(formatter);
    }

    public String getSubTotalFormat() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        formatter.setMaximumFractionDigits(0);
        return formatter.format(subtotal);
    }

    public String getPotonganFormat() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        formatter.setMaximumFractionDigits(0);
        return formatter.format(potongan);
    }

    public String getTotalFormat() {
        var total = subtotal - potongan;
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        formatter.setMaximumFractionDigits(0);
        return formatter.format(total);

    }

}
