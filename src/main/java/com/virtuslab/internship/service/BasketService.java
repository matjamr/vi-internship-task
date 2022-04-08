package com.virtuslab.internship.service;

import com.virtuslab.internship.entity.basket.Basket;
import com.virtuslab.internship.entity.product.Product;
import com.virtuslab.internship.entity.product.ProductDb;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BasketService {
    private final Basket basket;
    private final ProductDb productDb;

    public BasketService(final Basket basket, final ProductDb productDb) {
        this.basket = basket;
        this.productDb = productDb;
    }

    public List<Product> getProducts() {
        return basket.getProducts();
    }

    // Works fine
    public void addProduct(final String productName) throws NoSuchElementException{
        basket.addProduct(productDb.getProduct(productName));
    }
}
