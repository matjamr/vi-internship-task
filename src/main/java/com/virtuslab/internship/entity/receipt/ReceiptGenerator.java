package com.virtuslab.internship.entity.receipt;

import com.virtuslab.internship.entity.basket.Basket;
import com.virtuslab.internship.entity.product.Product;
import com.virtuslab.internship.entity.product.ProductDb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        List<ReceiptEntry> receiptEntries = countAndGetReceiptEntries(basket);


        return new Receipt(receiptEntries);
    }

    private static List<ReceiptEntry> countAndGetReceiptEntries(Basket basket) {
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        List<Product> products = basket.getProducts();
        List<String> nameList = products.stream().map(Product::name).collect(Collectors.toList());
        HashSet<String> names = new HashSet<>(nameList);

        ProductDb db = new ProductDb();

        for (String name : names) {
            receiptEntries.add(new ReceiptEntry(db.getProduct(name), Collections.frequency(nameList, name)));
        }

        return new ArrayList<>(receiptEntries);
    }
}
