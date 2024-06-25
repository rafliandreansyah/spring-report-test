package com.example.reporting_test.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.example.reporting_test.service.PdfUtilService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.reporting_test.model.TransactionData;

import javax.swing.text.DateFormatter;
import java.time.*;

@Controller
@RequiredArgsConstructor
public class ReportingController {

    final PdfUtilService pdfUtilService;

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
        final String customerName = "H.ACEP";

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

        var potongan = 0.0;
        var total = subTotal - potongan;

        NumberFormat formatter = NumberFormat.getCurrencyInstance();


        model.addAttribute("seller", dummySeller);
        model.addAttribute("store", dummyStore);
        model.addAttribute("transactions", data);
        model.addAttribute("subTotal", formatter.format(subTotal));
        model.addAttribute("potongan", formatter.format(potongan));
        model.addAttribute("total", formatter.format(total));
        model.addAttribute("dateNote", dateNote);
        model.addAttribute("numberNote", numberNote);
        model.addAttribute("customerNote", customerNote);
        model.addAttribute("address", address);
        model.addAttribute("customerName", customerName);
        model.addAttribute("pages", "report");
        return "pages/report";
    }


    @GetMapping("/exports/pdf")
    public void exportPdf(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

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
        pdfUtilService.exportReport(response, data);
    }

}
