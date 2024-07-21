package com.example.reporting_test.model;

import java.text.NumberFormat;
import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TransactionData {

    private String id;

    private String noPlu;

    private String productName;

    private Integer quantity;

    private Double price;

    private Double totalPrice;

    private Double discount;

    public String getHargaSatuanFormatCurrency() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        formatter.setMaximumFractionDigits(0);
        return formatter.format(price);
    }

    public String getNettoFormatCurrency() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        formatter.setMaximumFractionDigits(0);
        return formatter.format(totalPrice);
    }

}
