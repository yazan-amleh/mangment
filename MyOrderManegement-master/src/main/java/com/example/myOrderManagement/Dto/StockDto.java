package com.example.myOrderManagement.Dto;

import com.example.myOrderManagement.Entity.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StockDto {
    private Long id;

    private Product product;

    private Integer quantity;

    private Date updateAt;
}
