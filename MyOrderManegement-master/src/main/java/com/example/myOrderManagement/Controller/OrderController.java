package com.example.myOrderManagement.Controller;

import com.example.myOrderManagement.Dto.CustomerDto;
import com.example.myOrderManagement.Dto.OrderDto;
import com.example.myOrderManagement.Entity.Customer;
import com.example.myOrderManagement.Entity.Order;
import com.example.myOrderManagement.Exception.BadRequestException;
import com.example.myOrderManagement.Service.CustomerService;
import com.example.myOrderManagement.Service.OrderService;
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
@RequestMapping("/order")
public class OrderController {
    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    private OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get all orders service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully get all orders!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully get all orders!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get order by id service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully order by id !",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully get order by id!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<OrderDto> getOrderById(
            @PathVariable(name = "id") int id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Create order service",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully create order!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 201, \"Status\" : \"Created!\", \"Message\" :\"Successfully create order!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<OrderDto> createOrder
            (@Valid @RequestBody OrderDto orderDto) {
        if (orderDto.getId() == 0) {
            log.error("Cannot have an ID {}", orderDto);
            throw new BadRequestException(OrderController.class.getSimpleName(),"Id");
        }
        return new ResponseEntity(orderService.createOrder(orderDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "update order service",
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Successfully update order!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 202, \"Status\" : \"Accepted!\", \"Message\" :\"Successfully update order!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<OrderDto> updateOrder
            (@Valid @RequestBody OrderDto orderDto
                    , @PathVariable(name = "id") int id) {
        return new ResponseEntity<>(orderService.updateOrder(orderDto, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "delete order service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully delete order!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully delete order!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<String> deleteOrder(@PathVariable(name = "id") int id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}

