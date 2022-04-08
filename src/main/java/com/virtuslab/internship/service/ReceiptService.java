package com.virtuslab.internship.service;

import com.virtuslab.internship.entity.basket.Basket;
import com.virtuslab.internship.entity.discount.FifteenPercentDiscount;
import com.virtuslab.internship.entity.discount.TenPercentDiscount;
import com.virtuslab.internship.entity.receipt.Receipt;
import com.virtuslab.internship.entity.receipt.ReceiptGenerator;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {

    private final Basket basket;

    public ReceiptService(Basket basket) {
        this.basket = basket;
    }

    public Receipt getReceipt() {
        ReceiptGenerator receiptGenerator = new ReceiptGenerator();
        Receipt receipt = receiptGenerator.generate(basket);

        return applyDiscounts(receipt);
    }

    private static Receipt applyDiscounts(final Receipt receipt) {
        final FifteenPercentDiscount fifteenPercentDiscount = new FifteenPercentDiscount();
        final TenPercentDiscount tenPercentDiscount = new TenPercentDiscount();
        return tenPercentDiscount.apply(fifteenPercentDiscount.apply(receipt));
    }
}
