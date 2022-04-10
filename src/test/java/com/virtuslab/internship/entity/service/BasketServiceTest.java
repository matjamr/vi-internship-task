package com.virtuslab.internship.entity.service;

import com.virtuslab.internship.entity.basket.Basket;
import com.virtuslab.internship.entity.product.ProductDb;
import com.virtuslab.internship.exception.NoProductException;
import com.virtuslab.internship.service.BasketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BasketServiceTest {

    @InjectMocks
    private BasketService basketService;

    @Mock
    Basket basket;

    @Mock
    ProductDb productDb;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class AddProduct {
        @Test
        void ProductNameDoesNotExistShouldThrowNoProductError() {
            // given
            final String name = "some false name";

            // when
            when(productDb.contains(anyString())).thenReturn(false);

            // then
            assertThrows(NoProductException.class, ()->basketService.addProduct(name));
        }

        @Test
        void ProperDataShouldAddProductToBasket() throws NoProductException {
            // given
            final String name = "Apple";

            // when
            when(productDb.contains(anyString())).thenReturn(true);
            basketService.addProduct(name);

            // then
            verify(basket, times(1)).addProduct(productDb.getProduct(name));

        }
    }

}
