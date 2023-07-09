package com.example.myOrderManagement.Service.imp;

import com.example.myOrderManagement.Dto.StockDto;
import com.example.myOrderManagement.Entity.Customer;
import com.example.myOrderManagement.Entity.Product;
import com.example.myOrderManagement.Entity.Stock;
import com.example.myOrderManagement.Exception.ResourceNotFoundException;
import com.example.myOrderManagement.Repositry.StockRepsitry;
import com.example.myOrderManagement.Service.StockService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImplement implements StockService {
    private StockRepsitry stockRepsitry;
    
    StockServiceImplement(StockRepsitry stockRepsitry){
        this.stockRepsitry = stockRepsitry;
    }
    
    @Override
    public StockDto createProduct(StockDto stockDto) {
        Stock stock = mapToEntity(stockDto);
        Stock savedStock = stockRepsitry.save(stock);
        
        StockDto stockResponse = mapToDto(savedStock);
        return stockResponse;
    }

    @Override
    public List<StockDto> getAllProducts() {
        List<Stock> stocks = stockRepsitry.findAll();
        return stocks.stream().map(stock -> mapToDto(stock)).collect(Collectors.toList());
    }

    @Override
    public StockDto getProductById(int id) {
        Stock stock = stockRepsitry.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock","id", id));
        return mapToDto(stock);
    }

    @Override
    public StockDto updateProduct(StockDto stockDto, int id) {
        Stock stock = stockRepsitry.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock","id", id));
        stock.setId(stockDto.getId());
        stock.setProduct(stockDto.getProduct());
        stock.setUpdateAt(stockDto.getUpdateAt());
        stock.setQuantity(stockDto.getQuantity());
        Stock savedStock = stockRepsitry.save(stock);
        return mapToDto(savedStock);
    }

    @Override
    public void deleteProduct(int id) {
        Stock stock = stockRepsitry.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock","id", id));
        stockRepsitry.delete(stock);
    }

    private StockDto mapToDto(Stock stock) {
        StockDto stockDto = new StockDto();
        stockDto.setId(stock.getId());
        stockDto.setProduct(stock.getProduct());
        stockDto.setQuantity(stock.getQuantity());
        stockDto.setUpdateAt(stock.getUpdateAt());
        return stockDto;
    }

    private Stock mapToEntity(StockDto stockDto) {
        Stock stock = new Stock();
        stock.setId(stockDto.getId());
        stock.setProduct(stockDto.getProduct());
        stock.setQuantity(stockDto.getQuantity());
        stock.setUpdateAt(stockDto.getUpdateAt());
        return stock;
    }
}
