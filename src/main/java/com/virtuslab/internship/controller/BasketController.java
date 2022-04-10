package com.virtuslab.internship.controller;

import com.virtuslab.internship.entity.basket.Basket;
import com.virtuslab.internship.entity.product.Product;
import com.virtuslab.internship.entity.product.ProductDb;
import com.virtuslab.internship.entity.receipt.DTO.ReceiptDTO;
import com.virtuslab.internship.exception.NoProductException;
import com.virtuslab.internship.service.BasketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/basket")
public class BasketController {

    private final BasketService basketService;

    public BasketController(final BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping
    public void addProduct(@RequestBody ReceiptDTO receiptDTO) {
        try {
            basketService.addProduct(receiptDTO.name());
        } catch(NoProductException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage(),
                    e
            );
        }

    }

    @GetMapping
    public List<Product> getProducts() {
        return basketService.getProducts();
    }
}
