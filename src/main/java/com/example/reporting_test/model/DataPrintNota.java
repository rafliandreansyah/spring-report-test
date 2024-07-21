package com.example.reporting_test.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DataPrintNota {

    private String register;
    private String nostruk;
    private String printVia;
}
