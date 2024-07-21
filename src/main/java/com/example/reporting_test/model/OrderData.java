package com.example.reporting_test.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OrderData {

    private String transactionDate;

    private String transactionNumber;

    private String customer;

    private String noPlgCustomer;

    private String address;

    private String cashier;

    private List<TransactionData> transactions;

}
