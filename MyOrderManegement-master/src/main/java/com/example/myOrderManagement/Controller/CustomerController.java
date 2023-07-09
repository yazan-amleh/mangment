package com.example.myOrderManagement.Controller;


import com.example.myOrderManagement.Dto.CustomerDto;
import com.example.myOrderManagement.Entity.Customer;
import com.example.myOrderManagement.Exception.BadRequestException;
import com.example.myOrderManagement.Service.CustomerService;
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
@RequestMapping("/customer")
public class CustomerController {
    private final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get all customers service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully get all customers!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully get all customers!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return ResponseEntity.ok().body(customerService.getAllCustomer());
    }

    @GetMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get customer by id ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully get customer by id!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully get customer by id!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<CustomerDto> getCustomerById(
            @PathVariable(name = "id") int id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Create customer service",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully create customers!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 201, \"Status\" : \"Created!\", \"Message\" :\"Successfully create customer!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<CustomerDto> createCustomer
            (@Valid @RequestBody CustomerDto customerDto) {
        if (customerDto.getId() == 0) {
            log.error("Cannot have an ID {}", customerDto);
            throw new BadRequestException(CustomerController.class.getSimpleName(),"Id");
        }
        return new ResponseEntity(customerService.createCustomer(customerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Update customer info service",
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Successfully Update customer info!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 202, \"Status\" : \"Accepted!\", \"Message\" :\"Successfully Update customer info!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<CustomerDto> updateCustomer
            (@Valid @RequestBody CustomerDto customerDto
                    , @PathVariable(name = "id") int id) {
        return new ResponseEntity<>(customerService.updateCustomer(customerDto, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Delete customer service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully delete customer!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Accepted!\", \"Message\" :\"Successfully delete customer!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<String> deleteCustomer(@PathVariable(name = "id") int id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
