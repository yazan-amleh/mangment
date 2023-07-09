package com.example.myOrderManagement.Service.imp;

import com.example.myOrderManagement.Dto.ProductOrderDto;
import com.example.myOrderManagement.Dto.StockDto;
import com.example.myOrderManagement.Entity.ProductOrder;
import com.example.myOrderManagement.Entity.Stock;
import com.example.myOrderManagement.Exception.ResourceNotFoundException;
import com.example.myOrderManagement.Repositry.ProductOrderRepositry;
import com.example.myOrderManagement.Service.ProductOrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductOrderServiceImplement implements ProductOrderService {
    private ProductOrderRepositry productOrderRepositry;

    public ProductOrderServiceImplement(ProductOrderRepositry productOrderRepositry) {
        this.productOrderRepositry = productOrderRepositry;
    }


    @Override
    public ProductOrderDto createProductOrder(ProductOrderDto ProductOrderDto) {
        ProductOrder productOrder = new ProductOrder();
        ProductOrder savedProductOrder = productOrderRepositry.save(productOrder);

        ProductOrderDto productOrderResponse = mapToDto(savedProductOrder);
        return productOrderResponse;
    }

    @Override
    public List<ProductOrderDto> getAllProductOrder() {
        List<ProductOrder> productOrders = productOrderRepositry.findAll();
        return productOrders.stream().map(productOrder -> mapToDto(productOrder)).collect(Collectors.toList());
    }

    @Override
    public ProductOrderDto getProductOrderById(int id) {
        ProductOrder productOrder = productOrderRepositry.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductOrder","id", id));
        return mapToDto(productOrder);
    }

    @Override
    public ProductOrderDto updateProductOrder(ProductOrderDto productOrderDto, int id) {
        ProductOrder productOrder = productOrderRepositry.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductOrder","id", id));
        productOrder.setId(productOrderDto.getId());
        productOrder.setProduct(productOrderDto.getProduct());
        productOrder.setQuantity(productOrderDto.getQuantity());
        productOrder.setVat(productOrderDto.getVat());
        productOrder.setOrder(productOrderDto.getOrder());
        productOrder.setPrice(productOrderDto.getPrice());
        ProductOrder savedProductOrder = productOrderRepositry.save(productOrder);
        return mapToDto(savedProductOrder);
    }

    @Override
    public void deleteProductOrder(int id) {
        ProductOrder productOrder = productOrderRepositry.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductOrder","id", id));
        productOrderRepositry.delete(productOrder);
    }

    private ProductOrderDto mapToDto(ProductOrder productOrder) {
        ProductOrderDto productOrderDto = new ProductOrderDto();
        productOrderDto.setId(productOrderDto.getId());
        productOrderDto.setProduct(productOrderDto.getProduct());
        productOrderDto.setQuantity(productOrderDto.getQuantity());
        productOrderDto.setVat(productOrderDto.getVat());
        productOrderDto.setOrder(productOrderDto.getOrder());
        productOrderDto.setPrice(productOrderDto.getPrice());
        return productOrderDto;
    }

    private ProductOrder mapToEntity(ProductOrderDto productOrderDto) {
        ProductOrder productOrder = new ProductOrder();
        productOrder.setId(productOrderDto.getId());
        productOrder.setProduct(productOrderDto.getProduct());
        productOrder.setQuantity(productOrderDto.getQuantity());
        productOrder.setVat(productOrderDto.getVat());
        productOrder.setOrder(productOrderDto.getOrder());
        productOrder.setPrice(productOrderDto.getPrice());
        return productOrder;
    }
}
