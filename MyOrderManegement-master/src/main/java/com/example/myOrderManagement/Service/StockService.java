package com.example.myOrderManagement.Service;

import com.example.myOrderManagement.Dto.ProductDto;
import com.example.myOrderManagement.Dto.StockDto;

import java.util.List;

public interface StockService {
    StockDto createProduct(StockDto stockDto);

    List<StockDto> getAllProducts();

    StockDto getProductById(int id);

    StockDto updateProduct(StockDto stockDto, int id);

    void deleteProduct(int id);
}
