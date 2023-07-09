package com.example.myOrderManagement.Repositry;

import com.example.myOrderManagement.Entity.Customer;
import com.example.myOrderManagement.Entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepsitry extends JpaRepository<Stock, Integer> {
}
