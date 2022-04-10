package com.virtuslab.internship.entity.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtuslab.internship.controller.BasketController;
import com.virtuslab.internship.entity.basket.Basket;
import com.virtuslab.internship.entity.product.Product;
import com.virtuslab.internship.entity.product.ProductDb;
import com.virtuslab.internship.entity.receipt.DTO.ReceiptDTO;
import com.virtuslab.internship.exception.NoProductException;
import com.virtuslab.internship.service.BasketService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = BasketController.class)
public class BasketControllerTest {

    @MockBean
    private BasketService basketService;

    @MockBean
    private ProductDb productDb;

    @Autowired
    private MockMvc mockMvc;


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Nested
    class AddProduct {
        @Test
        void addProductValidDataShouldReturn200() throws Exception {
            // given
            final String name = "Apple";
            final ReceiptDTO receiptDTO = new ReceiptDTO(name);

            // when
            doNothing().when(basketService).addProduct(name);

            // then
            mockMvc.perform(post("/api/basket")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(receiptDTO)))
                            .andExpect(status().isOk());
        }

        @Test
        void addProductInValidDataShouldReturn400() throws Exception {
            // given
            final String name =  "aaaa";
            final ReceiptDTO receiptDTO = new ReceiptDTO(name);

            // when
            doThrow(NoProductException.class).when(basketService).addProduct(anyString());

            // then
            mockMvc.perform(post("/api/basket")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(receiptDTO)))
                            .andExpect(status().isBadRequest());
        }

        @Test
        void addProductProductNameIsNullShouldReturn400() throws Exception {
            // given
            final String name = null;
            final ReceiptDTO receiptDTO = new ReceiptDTO(name);

            // when
            doThrow(NoProductException.class).when(basketService).addProduct(any());

            // then
            mockMvc.perform(post("/api/basket")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(receiptDTO)))
                            .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class GetProducts {
        @Test
        void NoDataToSendAndReceiveShouldReturn200() throws Exception {
            // given

            // when

            // then
            mockMvc.perform(get("/api/basket")
                            .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk());
        }
    }
}
