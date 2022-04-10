package com.virtuslab.internship.service;

import com.virtuslab.internship.entity.basket.Basket;
import com.virtuslab.internship.entity.product.Product;
import com.virtuslab.internship.entity.product.ProductDb;
import com.virtuslab.internship.exception.NoProductException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {
    private final Basket basket;
    private final ProductDb productDb;

    public BasketService(Basket basket, ProductDb productDb) {
        this.basket = basket;
        this.productDb = productDb;
    }

    public List<Product> getProducts() {
        return basket.getProducts();
    }

    public void addProduct(final String productName) throws NoProductException {
        if (!productDb.contains(productName)) {
            throw new NoProductException(
                    "Product with given name: " + productName + " does not exist"
            );
        }
        basket.addProduct(productDb.getProduct(productName));
    }


}
