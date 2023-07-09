package com.example.myOrderManagement.Dto;

import com.example.myOrderManagement.Entity.Order;
import com.example.myOrderManagement.Entity.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductOrderDto {
    private Long id;

    private Product product;

    private Order order;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal vat;
}
