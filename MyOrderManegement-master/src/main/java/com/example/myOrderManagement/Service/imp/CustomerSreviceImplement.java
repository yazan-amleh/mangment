package com.example.myOrderManagement.Service.imp;

import com.example.myOrderManagement.Dto.CustomerDto;
import com.example.myOrderManagement.Entity.Customer;
import com.example.myOrderManagement.Exception.ResourceNotFoundException;
import com.example.myOrderManagement.Repositry.CustomerRepositry;
import com.example.myOrderManagement.Service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerSreviceImplement implements CustomerService {
    private CustomerRepositry customerRepositry;

    public CustomerSreviceImplement(CustomerRepositry customerRepositry) {
        this.customerRepositry = customerRepositry;
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer doctor = mapToEntity(customerDto);
        Customer savedDoctor = customerRepositry.save(doctor);

        CustomerDto customerResponse = mapToDto(savedDoctor);
        return customerResponse;
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        List<Customer> doctors = customerRepositry.findAll();
        return doctors.stream().map(doctor -> mapToDto(doctor)).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(int id) {
        Customer customer = customerRepositry.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor","id", id));
        return mapToDto(customer);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto, int id) {
        Customer customer = customerRepositry.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer","id", id));
        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setOrders(customerDto.getOrders());
        customer.setBornAt(customerDto.getBornAt());
        Customer savedCustomer = customerRepositry.save(customer);
        return mapToDto(savedCustomer);
    }

    @Override
    public void deleteCustomer(int id) {
        Customer customer = customerRepositry.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer","id", id));
        customerRepositry.delete(customer);
    }

    private CustomerDto mapToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setBornAt(customer.getBornAt());
        customerDto.setOrders(customer.getOrders());
        return customerDto;
    }

    private Customer mapToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setOrders(customerDto.getOrders());
        customer.setBornAt(customerDto.getBornAt());
        return customer;
    }
}
