package com.example.reporting_test.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.reporting_test.model.DataDummy;
import com.example.reporting_test.model.GenerateNoteTransactionData;
import com.example.reporting_test.model.TransactionData;
import com.example.reporting_test.service.CsvUtilService;
import com.example.reporting_test.service.MarkdownUtilService;
import com.example.reporting_test.service.PdfUtilService;
import com.lowagie.text.DocumentException;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class ReportingController {

    final PdfUtilService pdfUtilService;
    final MarkdownUtilService markdownUtilService;
    final CsvUtilService csvUtilService;
    List<TransactionData> data;

    @PostConstruct
    public void init() {
        data = DataDummy.getDummyTransactions();
    }

    @GetMapping("/")
    public String getTransaction(Model model) {
        // general info
        final String dummySeller = "Roben Diaz";
        final String dummyStore = "PS.SWALAYAN & DEPT.STORE";
        final LocalDateTime dateCreated = LocalDateTime.now();
        final Integer dummyPage = 1;

        // nota info
        final LocalDateTime dateNote = LocalDateTime.of(2022, 3, 1, 00, 00);
        final String numberNote = "001-727254";
        final String customerNote = "SI254-H.ACEP";
        final String address = "jl. panglima sudirman no.21";
        final String customerName = "H.ACEP";


        var subTotal = data.stream().map((tr) -> {
            return tr.getTotalPrice();
        }).reduce(0.0, (a, b) -> a + b);

        var potongan = 0.0;

        model.addAttribute("generateNoteTransactionData", GenerateNoteTransactionData.builder()
        .seller(dummySeller)
        .store(dummyStore)
        .pageNote(dummyPage)
        .dateCreatedNote(dateCreated)
        .timeCreatedNote(dateCreated)
        .dateTransactionNote(dateNote)
        .transactionIdNote(numberNote)
        .customerNameNote(customerNote)
        .customerAddressNote(address)
        .transactions(data)
        .customerName(customerName)
        .subtotal(subTotal)
        .potongan(potongan)
        .build());
        return "pages/report";
    }


    @GetMapping("/exports/pdf")
    public void exportPdf(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        final String fileName = "Invoice_" + LocalDateTime.now().getNano();

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + fileName + ".pdf";
        response.setHeader(headerKey, headerValue);

        pdfUtilService.exportReport(response, data);
    }

    @GetMapping("/exports/text")
    public ResponseEntity<byte[]> getMethodName() {
        // general info
        final String dummySeller = "Roben Diaz";
        final String dummyStore = "PS.SWALAYAN & DEPT.STORE";
        final LocalDateTime dateCreated = LocalDateTime.now();
        final Integer dummyPage = 1;

        // nota info
        final LocalDateTime dateNote = LocalDateTime.of(2022, 3, 1, 00, 00);
        final String numberNote = "001-727254";
        final String customerNote = "SI254-H.ACEP";
        final String address = "jl. panglima sudirman no.21";
        final String customerName = "H.ACEP";
        final String fileName = "Invoice_" + LocalDateTime.now().getNano();

        var subTotal = data.stream().map((tr) -> {
            return tr.getTotalPrice();
        }).reduce(0.0, (a, b) -> a + b);

        var potongan = 0.0;

        var byteTxt = markdownUtilService.generateMarkdown(
            GenerateNoteTransactionData.builder()
            .seller(dummySeller)
            .store(dummyStore)
            .pageNote(dummyPage)
            .dateCreatedNote(dateCreated)
            .timeCreatedNote(dateCreated)
            .dateTransactionNote(dateNote)
            .transactionIdNote(numberNote)
            .customerNameNote(customerNote)
            .customerAddressNote(address)
            .transactions(data)
            .customerName(customerName)
            .subtotal(subTotal)
            .potongan(potongan)
            .build()
            ).getBytes();

        return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".txt")
        .body(byteTxt);
    }
    

    @GetMapping("/exports/csv")
    public void generateCsv(HttpServletResponse response) throws IOException {
        // general info
        final String dummySeller = "Roben Diaz";
        final String dummyStore = "PS.SWALAYAN & DEPT.STORE";
        final LocalDateTime dateCreated = LocalDateTime.now();
        final Integer dummyPage = 1;

        // nota info
        final LocalDateTime dateNote = LocalDateTime.of(2022, 3, 1, 00, 00);
        final String numberNote = "001-727254";
        final String customerNote = "SI254-H.ACEP";
        final String address = "jl. panglima sudirman no.21";
        final String customerName = "H.ACEP";
        final String fileName = "Invoice_" + LocalDateTime.now().getNano();

        var subTotal = data.stream().map((tr) -> {
            return tr.getTotalPrice();
        }).reduce(0.0, (a, b) -> a + b);

        var potongan = 0.0;
        // // Sample data
        // List<String[]> data = Arrays.asList(
        //         new String[]{"ID", "Name", "Age"},
        //         new String[]{"1", "Alice", "30"},
        //         new String[]{"2", "Bob", "25"}
        // );

        // // Create CSV string using OpenCSV
        // StringBuilder csvString = new StringBuilder();
        // try (StringWriter writer = new StringWriter(); CSVWriter csvWriter = new CSVWriter(writer)) {
        //     csvWriter.writeAll(data);
        //     csvString.append(writer.toString());
        // }

        var csvString = csvUtilService.generateCsv(GenerateNoteTransactionData.builder()
        .seller(dummySeller)
        .store(dummyStore)
        .pageNote(dummyPage)
        .dateCreatedNote(dateCreated)
        .timeCreatedNote(dateCreated)
        .dateTransactionNote(dateNote)
        .transactionIdNote(numberNote)
        .customerNameNote(customerNote)
        .customerAddressNote(address)
        .transactions(data)
        .customerName(customerName)
        .subtotal(subTotal)
        .potongan(potongan)
        .build()
        );

        // Configure response for CSV download
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".csv");

        // Write CSV string to response
        response.getWriter().write(csvString);
    }
}
