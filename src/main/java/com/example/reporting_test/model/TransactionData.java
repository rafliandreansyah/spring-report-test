package com.example.reporting_test.model;

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

}
