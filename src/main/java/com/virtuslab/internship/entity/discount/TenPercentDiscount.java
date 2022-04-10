package com.virtuslab.internship.entity.discount;

import com.virtuslab.internship.entity.receipt.Receipt;

import java.math.BigDecimal;

public class TenPercentDiscount {

    private final static String NAME = "TenPercentDiscount";

    public Receipt apply(final Receipt receipt) {
        if (shouldApply(receipt)) {
            var totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.9));
            var discounts = receipt.discounts();
            discounts.add(NAME);
            return new Receipt(receipt.entries(), discounts, totalPrice);
        }
        return receipt;
    }

    private boolean shouldApply(final Receipt receipt) {
        return receipt.totalPrice().compareTo(BigDecimal.valueOf(50)) >= 0;
    }
}
