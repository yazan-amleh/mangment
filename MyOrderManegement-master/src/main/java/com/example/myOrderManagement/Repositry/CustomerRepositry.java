package com.example.myOrderManagement.Repositry;

import com.example.myOrderManagement.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepositry extends JpaRepository<Customer, Integer> {

}
