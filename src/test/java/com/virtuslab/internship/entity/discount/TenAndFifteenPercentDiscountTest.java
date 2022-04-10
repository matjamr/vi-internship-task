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

class TenAndFifteenPercentDiscountTest {
    @Test
    void shouldNotApply15PercentDiscountAnd10PercentDiscount() {
        // Given
        ProductDb productDb = new ProductDb();
        Product cheese = productDb.getProduct("Cheese");

        List<ReceiptEntry> receiptEntries = new ArrayList<>();

        receiptEntries.add(new ReceiptEntry(cheese, 2));

        Receipt receipt = new Receipt(receiptEntries);
        FifteenPercentDiscount fifteenPercentDiscount = new FifteenPercentDiscount();
        TenPercentDiscount tenPercentDiscount = new TenPercentDiscount();
        BigDecimal expectedTotalPrice = cheese.price().multiply(BigDecimal.valueOf(2));

        // When
        Receipt receiptAfterDiscount = tenPercentDiscount.apply(fifteenPercentDiscount.apply(receipt));

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(0, receiptAfterDiscount.discounts().size());
    }

    @Test
    void shouldApply15PercentDiscountWhenThereAre3DiaryProductsAnd10PercentDiscountAsPriceIsMoreThan50() {
        // Given
        ProductDb productDb = new ProductDb();
        Product cheese = productDb.getProduct("Cheese");

        List<ReceiptEntry> receiptEntries = new ArrayList<>();

        receiptEntries.add(new ReceiptEntry(cheese, 3));

        Receipt receipt = new Receipt(receiptEntries);
        FifteenPercentDiscount fifteenPercentDiscount = new FifteenPercentDiscount();
        TenPercentDiscount tenPercentDiscount = new TenPercentDiscount();
        BigDecimal expectedTotalPrice = cheese.price().multiply(BigDecimal.valueOf(3)).multiply(BigDecimal.valueOf(0.85).multiply(BigDecimal.valueOf(0.9)));

        // When
        Receipt receiptAfterDiscount = tenPercentDiscount.apply(fifteenPercentDiscount.apply(receipt));

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(2, receiptAfterDiscount.discounts().size());
    }
}
