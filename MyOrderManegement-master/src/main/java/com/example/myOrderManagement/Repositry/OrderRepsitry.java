package com.example.myOrderManagement.Repositry;

import com.example.myOrderManagement.Entity.Customer;
import com.example.myOrderManagement.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepsitry extends JpaRepository<Order, Integer> {
}
