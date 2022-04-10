package com.virtuslab.internship.entity.discount;

import com.virtuslab.internship.entity.product.Product;
import com.virtuslab.internship.entity.product.ProductDb;
import com.virtuslab.internship.entity.receipt.Receipt;
import com.virtuslab.internship.entity.receipt.ReceiptEntry;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FifteenPercentDiscountTest {
    @Test
    void shouldApply15PercentDiscountWhenThereAre3DiaryProducts() {
        // Given
        ProductDb productDb = new ProductDb();
        Product cheese = productDb.getProduct("Milk");

        List<ReceiptEntry> receiptEntries = new ArrayList<>();

        receiptEntries.add(new ReceiptEntry(cheese, 3));

        Receipt receipt = new Receipt(receiptEntries);
        FifteenPercentDiscount fifteenPercentDiscount = new FifteenPercentDiscount();
        BigDecimal expectedTotalPrice = cheese.price().multiply(BigDecimal.valueOf(3)).multiply(BigDecimal.valueOf(0.85));

        // When
        Receipt receiptAfterDiscount = fifteenPercentDiscount.apply(receipt);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(1, receiptAfterDiscount.discounts().size());
    }



    @Test
    void shouldNotApply15PercentDiscountWhenThereAreNot3DiaryProducts() {
        // Given
        ProductDb productDb = new ProductDb();
        Product cheese = productDb.getProduct("Cheese");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cheese, 2));

        Receipt receipt = new Receipt(receiptEntries);
        FifteenPercentDiscount fifteenPercentDiscount = new FifteenPercentDiscount();
        BigDecimal expectedTotalPrice = cheese.price().multiply(BigDecimal.valueOf(2));

        // When
        Receipt receiptAfterDiscount = fifteenPercentDiscount.apply(receipt);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(0, receiptAfterDiscount.discounts().size());
    }
}
