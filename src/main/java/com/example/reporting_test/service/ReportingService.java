/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.example.reporting_test.service;

import com.example.reporting_test.model.OrderData;


public interface ReportingService {

    public OrderData getOrderData(String registerNumber, String structNumber);

}
