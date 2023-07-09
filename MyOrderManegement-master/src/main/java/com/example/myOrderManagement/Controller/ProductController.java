package com.example.myOrderManagement.Controller;

import com.example.myOrderManagement.Dto.OrderDto;
import com.example.myOrderManagement.Dto.ProductDto;
import com.example.myOrderManagement.Entity.Product;
import com.example.myOrderManagement.Exception.BadRequestException;
import com.example.myOrderManagement.Service.OrderService;
import com.example.myOrderManagement.Service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/product")
public class ProductController {
    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get all products service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully get all products!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully get all products!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get product by id service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully get product by id!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully get product by id!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<ProductDto> getProductById(
            @PathVariable(name = "id") int id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "create product service",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully create product !",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 201, \"Status\" : \"Created!\", \"Message\" :\"Successfully create product !\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<ProductDto> createProduct
            (@Valid @RequestBody ProductDto productDto) {
        if (productDto.getId() == 0) {
            log.error("Cannot have an ID {}", productDto);
            throw new BadRequestException(OrderController.class.getSimpleName(),"Id");
        }
        return new ResponseEntity(productService.createProduct(productDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "update product service",
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Successfully update product !",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 202, \"Status\" : \"Accepted!\", \"Message\" :\"Successfully update product !\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<ProductDto> updateProduct
            (@Valid @RequestBody ProductDto productDto
                    , @PathVariable(name = "id") int id) {
        return new ResponseEntity<>(productService.updateProduct(productDto, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "delete product service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully delete product !",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully delete product !\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") int id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
