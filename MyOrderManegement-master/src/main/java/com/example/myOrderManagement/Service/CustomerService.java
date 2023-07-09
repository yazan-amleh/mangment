package com.example.myOrderManagement.Service;

import com.example.myOrderManagement.Dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);

    List<CustomerDto> getAllCustomer();

    CustomerDto getCustomerById(int id);

    CustomerDto updateCustomer(CustomerDto customerDto,int id);

    void deleteCustomer(int id);
}
