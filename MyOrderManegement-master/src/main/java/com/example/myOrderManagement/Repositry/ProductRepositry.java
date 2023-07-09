package com.example.myOrderManagement.Repositry;

import com.example.myOrderManagement.Entity.Customer;
import com.example.myOrderManagement.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositry extends JpaRepository<Product, Integer> {
}
