package com.virtuslab.internship.entity.discount;

import com.virtuslab.internship.entity.product.Product;
import com.virtuslab.internship.entity.product.ProductDb;
import com.virtuslab.internship.entity.receipt.Receipt;
import com.virtuslab.internship.entity.receipt.ReceiptEntry;

import java.math.BigDecimal;

public class FifteenPercentDiscount {
    private final static String NAME = "FifteenPercentDiscount";

    public Receipt apply(final Receipt receipt) {
        if (shouldApply(receipt)) {
            var totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.85));
            var discounts = receipt.discounts();
            discounts.add(NAME);
            return new Receipt(receipt.entries(), discounts, totalPrice);
        }
        return receipt;
    }

    private boolean shouldApply(final Receipt receipt) {
        final ProductDb productDb = new ProductDb();
        for(ReceiptEntry receiptEntry: receipt.entries()) {
            Product product = productDb.getProduct(receiptEntry.product().name());
            if(product.type().equals(Product.Type.GRAINS) || receiptEntry.quantity() >= 3) {
                return true;
            }
        }
        return false;
    }
}
