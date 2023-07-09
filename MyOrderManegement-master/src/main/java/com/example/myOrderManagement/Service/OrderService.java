package com.example.myOrderManagement.Service;

import com.example.myOrderManagement.Dto.CustomerDto;
import com.example.myOrderManagement.Dto.OrderDto;
import com.example.myOrderManagement.Entity.Customer;
import com.example.myOrderManagement.Entity.ProductOrder;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);

    List<OrderDto> getAllOrders();

    OrderDto getOrderById(int id);

    OrderDto updateOrder(OrderDto orderDto,int id);

    void deleteOrder(int id);
}
