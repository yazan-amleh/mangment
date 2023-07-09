package com.example.myOrderManagement.Repositry;

import com.example.myOrderManagement.Entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepositry extends JpaRepository<ProductOrder, Integer>{

}
