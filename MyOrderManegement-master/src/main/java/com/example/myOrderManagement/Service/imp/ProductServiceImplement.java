package com.example.myOrderManagement.Service.imp;

import com.example.myOrderManagement.Dto.CustomerDto;
import com.example.myOrderManagement.Dto.ProductDto;
import com.example.myOrderManagement.Entity.Customer;
import com.example.myOrderManagement.Entity.Product;
import com.example.myOrderManagement.Entity.Stock;
import com.example.myOrderManagement.Repositry.CustomerRepositry;
import com.example.myOrderManagement.Repositry.ProductRepositry;
import com.example.myOrderManagement.Service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplement implements ProductService {
    private ProductRepositry productRepositry;

    ProductServiceImplement(ProductRepositry productRepositry){
        this.productRepositry = productRepositry;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = mapToEntity(productDto);
        Product savedProduct = productRepositry.save(product);

        ProductDto productResponse = mapToDto(savedProduct);
        return productResponse;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepositry.findAll();
        return products.stream().map(product -> mapToDto(product)).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public ProductDto getProductById(int id) {
        Product product = productRepositry.findById(id).orElseThrow(() -> new com.example.myOrderManagement.Exception.ResourceNotFoundException("Product","id", id));
        return mapToDto(product);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, int id) {
        Product product = productRepositry.findById(id).orElseThrow(() -> new com.example.myOrderManagement.Exception.ResourceNotFoundException("Product","id", id));
        product.setId(productDto.getId());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        product.setSlug(productDto.getSlug());
        product.setReference(productDto.getReference());
        product.setStockable(productDto.getStockable());
        product.setVat(productDto.getVat());
        Product savedProduct = productRepositry.save(product);
        return mapToDto(savedProduct);
    }

    @Override
    public void deleteProduct(int id) {
        Product product = productRepositry.findById(id).orElseThrow(() -> new com.example.myOrderManagement.Exception.ResourceNotFoundException("Product","id", id));
        productRepositry.delete(product);
    }

    private ProductDto mapToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setPrice(product.getPrice());
        productDto.setName(product.getName());
        productDto.setSlug(product.getSlug());
        productDto.setReference(product.getReference());
        productDto.setStockable(product.getStockable());
        productDto.setVat(product.getVat());
        return productDto;
    }

    private Product mapToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        product.setSlug(productDto.getSlug());
        product.setReference(productDto.getReference());
        product.setStockable(productDto.getStockable());
        product.setVat(productDto.getVat());
        return product;
    }
}
