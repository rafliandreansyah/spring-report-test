package com.example.reporting_test.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.reporting_test.model.OrderData;
import com.example.reporting_test.model.TransactionData;
import com.example.reporting_test.repository.CusmsRepository;
import com.example.reporting_test.repository.InvblRepository;
import com.example.reporting_test.repository.Reg001Repository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportingServiceImpl implements ReportingService {

    private final CusmsRepository cusmsRepository;
    private final InvblRepository invblRepository;
    private final Reg001Repository reg001Repository;

    @Override
    public OrderData getOrderData(String registerNumber, String structNumber) {

        // Load reg001
        var dataRegs = reg001Repository.findByNoorAndRegister(structNumber, registerNumber);
        if (dataRegs == null || dataRegs.isEmpty()) {
            return null;
        }

        // Get data customer
        var customerData = cusmsRepository.findByPlg(dataRegs.get(0).getPlg());

        var transactionData = new ArrayList<TransactionData>();

        int no = 1;
        for(var dataReg: dataRegs) {
            var invbl = invblRepository.findByPlu(dataReg.getPlu());
            transactionData.add(new TransactionData(String.valueOf(no), dataReg.getPlu(), invbl.getNama(), dataReg.getQty().intValue(), dataReg.getHarga(), dataReg.getTharga(), dataReg.getDisc()));
            no++;
        }

        return OrderData.builder()
            .customer(customerData.getNama())
            .address("")
            .transactionNumber(dataRegs.get(0).getNoor())
            .transactionDate(dataRegs.get(1).getTgl().toString())
            .transactions(transactionData)
            .noPlgCustomer(dataRegs.get(1).getPlg())
            .cashier(dataRegs.get(1).getKasir())
            .build();
    }




}
