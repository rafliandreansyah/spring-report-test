package com.example.reporting_test.controller;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.reporting_test.model.TransactionData;
import java.time.*;

@Controller
public class ReportingController {

    @GetMapping("/")
    public String getTransaction(Model model) {
        // general info
        final String dummySeller = "Roben Diaz";
        final String dummyStore = "PS.SWALAYAN & DEPT.STORE";
        final String dateCreated = LocalDateTime.now().toString();

        // nota info
        final String dateNote = "03/01/22";
        final String numberNote = "001-727254";
        final String customerNote = "SI254-H.ACEP";
        final String address = "jl. panglima sudirman no.21 surabaya";

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

        var subTotal = data.stream().map((tr) -> {
            return tr.getTotalPrice();
        }).reduce(0.0, (a, b) -> a + b);

        var potongan = 0;
        var total = subTotal - potongan;

        model.addAttribute("seller", dummySeller);
        model.addAttribute("store", dummyStore);
        model.addAttribute("transactions", data);
        model.addAttribute("subTotal", subTotal);
        model.addAttribute("potongan", potongan);
        model.addAttribute("total", total);
        model.addAttribute("pages", "report");
        return "pages/report";
    }

}
