package com.virtuslab.internship.controller;

import com.virtuslab.internship.entity.receipt.Receipt;
import com.virtuslab.internship.service.ReceiptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/receipt")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(final ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping
    public Receipt getReceipt() {
        return receiptService.getReceipt();
    }
}
