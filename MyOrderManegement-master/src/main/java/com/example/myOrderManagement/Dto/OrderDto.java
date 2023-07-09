package com.example.myOrderManagement.Dto;


import com.example.myOrderManagement.Entity.Customer;
import com.example.myOrderManagement.Entity.ProductOrder;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class OrderDto {

    private Long id;

    private Customer customer;

    private Date orderedAt;

    Set<ProductOrder> productOrders;
}
