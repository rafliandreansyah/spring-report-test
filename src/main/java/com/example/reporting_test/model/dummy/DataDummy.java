package com.example.reporting_test.model.dummy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.reporting_test.model.TransactionData;

public class DataDummy {

    public static List<TransactionData> getDummyTransactions() {
        var data = new ArrayList<TransactionData>();
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            var price = rand.nextInt(100000);
            var quantity = rand.nextInt(1, 10);
            var netto = price * quantity;
            data.add(TransactionData.builder()
                    .id(String.valueOf(rand.nextInt(1000000)))
                    .noPlu(String.valueOf(rand.nextInt(100000)))
                    .productName("Product " + (i + 1))
                    .price(Double.valueOf(price))
                    .quantity(quantity)
                    .totalPrice(Double.valueOf(netto))
                    .build());
        }

        return data;
    }

}
