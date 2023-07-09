package com.example.myOrderManagement.Controller;

import com.example.myOrderManagement.Dto.ProductDto;
import com.example.myOrderManagement.Dto.ProductOrderDto;
import com.example.myOrderManagement.Entity.ProductOrder;
import com.example.myOrderManagement.Exception.BadRequestException;
import com.example.myOrderManagement.Service.ProductOrderService;
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
@RequestMapping("/productOrder")
public class ProductOrderController {
    private final Logger log = LoggerFactory.getLogger(ProductOrderController.class);

    private ProductOrderService productOrderService;

    ProductOrderController(ProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }

    @GetMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get all orders service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully Get all orders !",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully Get all orders !\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<List<ProductOrderDto>> getAllProductOrders(){
        return ResponseEntity.ok().body(productOrderService.getAllProductOrder());
    }

    @GetMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get order by id service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully Get order by id !",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully Get order by id!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<ProductOrderDto> getProductOrderById(
            @PathVariable(name = "id") int id) {
        return ResponseEntity.ok(productOrderService.getProductOrderById(id));
    }

    @PostMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "create order service",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully create order!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 201, \"Status\" : \"Created!\", \"Message\" :\"Successfully create order\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<ProductDto> createProductOrder
            (@Valid @RequestBody ProductOrderDto productOrderDto) {
        if (productOrderDto.getId() == 0) {
            log.error("Cannot have an ID {}", productOrderDto);
            throw new BadRequestException(OrderController.class.getSimpleName(),"Id");
        }
        return new ResponseEntity(productOrderService.createProductOrder(productOrderDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "update order by id service",
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Successfully update order by id!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 202, \"Status\" : \"Accepted!\", \"Message\" :\"Successfully update order by id!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<ProductOrderDto> updateProductOrder
            (@Valid @RequestBody ProductOrderDto productOrderDto
                    , @PathVariable(name = "id") int id) {
        return new ResponseEntity<>(productOrderService.updateProductOrder(productOrderDto, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "delete order by id service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully delete order by id!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully delete order by id!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<String> deleteProductOrder(@PathVariable(name = "id") int id) {
        productOrderService.deleteProductOrder(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
