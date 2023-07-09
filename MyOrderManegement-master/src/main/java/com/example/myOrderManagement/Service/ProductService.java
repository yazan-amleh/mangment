package com.example.myOrderManagement.Service;

import com.example.myOrderManagement.Dto.CustomerDto;
import com.example.myOrderManagement.Dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    ProductDto getProductById(int id);

    ProductDto updateProduct(ProductDto productDto, int id);

    void deleteProduct(int id);
}
