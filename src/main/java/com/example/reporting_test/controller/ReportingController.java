package com.example.reporting_test.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.reporting_test.model.DataPrintNota;
import com.example.reporting_test.model.GenerateNoteTransactionData;
import com.example.reporting_test.model.OrderData;
import com.example.reporting_test.service.CsvUtilService;
import com.example.reporting_test.service.MarkdownUtilService;
import com.example.reporting_test.service.PdfUtilService;
import com.example.reporting_test.service.ReportingService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;




@Controller
@RequiredArgsConstructor
public class ReportingController {

    final PdfUtilService pdfUtilService;
    final MarkdownUtilService markdownUtilService;
    final CsvUtilService csvUtilService;
    private final ReportingService reportingService;

    // @PostConstruct
    // public void init() {
    //     data = DataDummy.getDummyTransactions();
    // }

    @GetMapping("/export/layar")
    public String getTransaction(Model model, @RequestParam("register")Integer register, @RequestParam("no-struct") Integer noStruct, ModelMap modelMap) {

        var dataMap = (OrderData) modelMap.get("data");

        // general info
        final String seller = dataMap.getCashier();
        final String store = "PS.SWALAYAN & DEPT.STORE";
        final LocalDateTime dateCreated = LocalDateTime.now();
        final Integer dummyPage = 1;

        // nota info
        final String trxDate = dataMap.getTransactionDate();
        LocalDate trxDateConvert = null;
        if (trxDate != null) {
            trxDateConvert = LocalDate.parse(trxDate);
        }
        final LocalDateTime dateNote = trxDateConvert != null ? trxDateConvert.atStartOfDay() : null;
        final String numberNote = register + "-" + noStruct.toString();
        final String address = "";
        final String customerName =  dataMap.getCustomer();
        final String customerNote = dataMap.getNoPlgCustomer() + "-" + customerName;


        var subTotal = dataMap.getTransactions().stream().map((tr) -> {
            return tr.getTotalPrice();
        }).reduce(0.0, (a, b) -> a + b);

        var potongan = dataMap.getTransactions().stream().map((tr) -> {
            return tr.getDiscount();
        }).reduce(0.0, (a, b) -> a + b);

        model.addAttribute("generateNoteTransactionData", GenerateNoteTransactionData.builder()
        .seller(seller)
        .store(store)
        .pageNote(dummyPage)
        .dateCreatedNote(dateCreated)
        .timeCreatedNote(dateCreated)
        .dateTransactionNote(dateNote)
        .transactionIdNote(numberNote)
        .customerNameNote(customerNote)
        .customerAddressNote(address)
        .transactions(dataMap.getTransactions())
        .customerName(customerName)
        .subtotal(subTotal)
        .potongan(potongan)
        .build());
        return "pages/report";
    }

    @GetMapping("/")
    public String getPrintNotaForm(DataPrintNota dataPrintNota, Model model, @RequestParam(name = "errorMessage", required = false) String errorMessage) {
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }
        model.addAttribute("dataPrintNota", dataPrintNota);
        model.addAttribute("pages", "print-nota");
        return "pages/form-print-nota";
    }

    @PostMapping("/print-nota")
    public String postPrintNota(@ModelAttribute("dataPrintNota") DataPrintNota dataPrintNota, RedirectAttributes redirectAttributes) {

        var orderData = reportingService.getOrderData(dataPrintNota.getRegister(), dataPrintNota.getNostruk());

        if(orderData == null) {
            redirectAttributes.addAttribute("errorMessage", "Data tidak ditemukan dengan register: " + dataPrintNota.getRegister() + " dan nomer Struk: " + dataPrintNota.getNostruk());
            return "redirect:";
        }

        var urlRedirect = "/";
        if("pdf".equals(dataPrintNota.getPrintVia())){
            urlRedirect = "/exports/pdf";
        } else if ("csv".equals(dataPrintNota.getPrintVia())) {
            urlRedirect = "/exports/csv";
        } else if ("txt".equals(dataPrintNota.getPrintVia())) {
            urlRedirect = "/exports/text";
        } else {
            urlRedirect = "/export/layar";
        }
        
        redirectAttributes.addAttribute("register", dataPrintNota.getRegister());
        redirectAttributes.addAttribute("no-struct", dataPrintNota.getNostruk());
        redirectAttributes.addFlashAttribute("data", orderData);
        return "redirect:" + urlRedirect;
    }
    
    


    @GetMapping("/exports/pdf")
    public String exportPdf(HttpServletResponse response, @RequestParam("register")Integer register, @RequestParam("no-struct") Integer noStruct, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        

        var dataMap = (OrderData) modelMap.get("data");
        
        response.setContentType("application/pdf");
        final String fileName = "Invoice_" + LocalDateTime.now().getNano();

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + fileName + ".pdf";
        response.setHeader(headerKey, headerValue);

        // general info
        final String seller = dataMap.getCashier();
        final String store = "PS.SWALAYAN & DEPT.STORE";
        final LocalDateTime dateCreated = LocalDateTime.now();
        final Integer dummyPage = 1;

        final String trxDate = dataMap.getTransactionDate();
        LocalDate trxDateConvert = null;
        if (trxDate != null) {
            trxDateConvert = LocalDate.parse(trxDate);
        }
        final LocalDateTime dateNote = trxDateConvert != null ? trxDateConvert.atStartOfDay() : null;
        final String numberNote = register + "-" + noStruct.toString();
        final String address = "";
        final String customerName =  dataMap.getCustomer();
        final String customerNote = dataMap.getNoPlgCustomer() + "-" + customerName;

        var subTotal = dataMap.getTransactions().stream().map((tr) -> {
            return tr.getTotalPrice();
        }).reduce(0.0, (a, b) -> a + b);

        var potongan = dataMap.getTransactions().stream().map((tr) -> {
            return tr.getDiscount();
        }).reduce(0.0, (a, b) -> a + b);

        var generatePdfData = GenerateNoteTransactionData.builder()
        .seller(seller)
        .store(store)
        .pageNote(dummyPage)
        .dateCreatedNote(dateCreated)
        .timeCreatedNote(dateCreated)
        .dateTransactionNote(dateNote)
        .transactionIdNote(numberNote)
        .customerNameNote(customerNote)
        .customerAddressNote(address)
        .transactions(dataMap.getTransactions())
        .customerName(customerName)
        .subtotal(subTotal)
        .potongan(potongan)
        .build();

        try {

            pdfUtilService.exportReport(response, generatePdfData);
            return "";
        }catch(Exception e) {
            e.printStackTrace();
            redirectAttributes.addAttribute("errorMessage", "Gagal melakukan export pdf");
            return "redirect:";
        }
    }

    @GetMapping("/exports/text")
    public ResponseEntity<byte[]> getMethodName(@RequestParam("register")Integer register, @RequestParam("no-struct") Integer noStruct, ModelMap modelMap) {
        
        var dataMap = (OrderData) modelMap.get("data");
        
        // general info
        final String seller = dataMap.getCashier();
        final String store = "PS.SWALAYAN & DEPT.STORE";
        final LocalDateTime dateCreated = LocalDateTime.now();
        final Integer dummyPage = 1;
        final String fileName = "Invoice_" + LocalDateTime.now().getNano();

        // nota info
        final String trxDate = dataMap.getTransactionDate();
        LocalDate trxDateConvert = null;
        if (trxDate != null) {
            trxDateConvert = LocalDate.parse(trxDate);
        }
        final LocalDateTime dateNote = trxDateConvert != null ? trxDateConvert.atStartOfDay() : null;
        final String numberNote = register + "-" + noStruct.toString();
        final String address = "";
        final String customerName =  dataMap.getCustomer();
        final String customerNote = dataMap.getNoPlgCustomer() + "-" + customerName;

        var subTotal = dataMap.getTransactions().stream().map((tr) -> {
            return tr.getTotalPrice();
        }).reduce(0.0, (a, b) -> a + b);

        var potongan = dataMap.getTransactions().stream().map((tr) -> {
            return tr.getDiscount();
        }).reduce(0.0, (a, b) -> a + b);

        var byteTxt = markdownUtilService.generateMarkdown(
            GenerateNoteTransactionData.builder()
            .seller(seller)
            .store(store)
            .pageNote(dummyPage)
            .dateCreatedNote(dateCreated)
            .timeCreatedNote(dateCreated)
            .dateTransactionNote(dateNote)
            .transactionIdNote(numberNote)
            .customerNameNote(customerNote)
            .customerAddressNote(address)
            .transactions(dataMap.getTransactions())
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
    public String generateCsv(HttpServletResponse response, @RequestParam("register")Integer register, @RequestParam("no-struct") Integer noStruct, ModelMap modelMap, RedirectAttributes redirectAttributes) {

        var dataMap = (OrderData) modelMap.get("data");

        // general info
        final String seller = dataMap.getCashier();
        final String store = "PS.SWALAYAN & DEPT.STORE";
        final LocalDateTime dateCreated = LocalDateTime.now();
        final Integer dummyPage = 1;

        // nota info
        final String fileName = "Invoice_" + LocalDateTime.now().getNano();

        final String trxDate = dataMap.getTransactionDate();
        LocalDate trxDateConvert = null;
        if (trxDate != null) {
            trxDateConvert = LocalDate.parse(trxDate);
        }
        final LocalDateTime dateNote = trxDateConvert != null ? trxDateConvert.atStartOfDay() : null;
        final String numberNote = register + "-" + noStruct.toString();
        final String address = "";
        final String customerName =  dataMap.getCustomer();
        final String customerNote = dataMap.getNoPlgCustomer() + "-" + customerName;

        var subTotal = dataMap.getTransactions().stream().map((tr) -> {
            return tr.getTotalPrice();
        }).reduce(0.0, (a, b) -> a + b);

        var potongan = dataMap.getTransactions().stream().map((tr) -> {
            return tr.getDiscount();
        }).reduce(0.0, (a, b) -> a + b);

        try {
            var csvString = csvUtilService.generateCsv(GenerateNoteTransactionData.builder()
            .seller(seller)
            .store(store)
            .pageNote(dummyPage)
            .dateCreatedNote(dateCreated)
            .timeCreatedNote(dateCreated)
            .dateTransactionNote(dateNote)
            .transactionIdNote(numberNote)
            .customerNameNote(customerNote)
            .customerAddressNote(address)
            .transactions(dataMap.getTransactions())
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
            return null;
        }catch(Exception e){
            redirectAttributes.addAttribute("errorMessage", "Gagal melakukan export csv");
            return "redirect:/";
        }
    }
}
