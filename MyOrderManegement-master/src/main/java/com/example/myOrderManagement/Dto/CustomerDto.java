package com.example.myOrderManagement.Dto;

import com.example.myOrderManagement.Entity.Order;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CustomerDto {

    private Long id;

    private String firstName;

    private String lastName;

    private Date bornAt;

    private List<Order> orders;
}
