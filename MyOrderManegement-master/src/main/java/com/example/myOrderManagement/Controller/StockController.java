package com.example.myOrderManagement.Controller;

import com.example.myOrderManagement.Dto.OrderDto;
import com.example.myOrderManagement.Dto.StockDto;
import com.example.myOrderManagement.Exception.BadRequestException;
import com.example.myOrderManagement.Service.OrderService;
import com.example.myOrderManagement.Service.StockService;
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
@RequestMapping("/stock")
public class StockController{
    private final Logger log = LoggerFactory.getLogger(StockController.class);

    private StockService stockService;

    StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get all stocks service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully Get all stocks!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully get all stocks!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<List<StockDto>> getAllStocks(){
        return ResponseEntity.ok().body(stockService.getAllProducts());
    }

    @GetMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "get stock by id service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully get stock by id!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully get stock by id!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<StockDto> getStockById(
            @PathVariable(name = "id") int id) {
        return ResponseEntity.ok(stockService.getProductById(id));
    }

    @PostMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "create stock service",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully create stock !",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 201, \"Status\" : \"Created!\", \"Message\" :\"Successfully create stock !\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<StockDto> createStock
            (@Valid @RequestBody StockDto StockDto) {
        if (StockDto.getId() == 0) {
            log.error("Cannot have an ID {}", StockDto);
            throw new BadRequestException(OrderController.class.getSimpleName(),"Id");
        }
        return new ResponseEntity(stockService.createProduct(StockDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "update stock by id service",
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Successfully update stock by id!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 202, \"Status\" : \"Accepted!\", \"Message\" :\"Successfully update stock by id!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<StockDto> updateOrder
            (@Valid @RequestBody StockDto StockDto
                    , @PathVariable(name = "id") int id) {
        return new ResponseEntity<>(stockService.updateProduct(StockDto, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "delete stock by id service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully delete stock by id!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully delete stock by id!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<String> deleteOrder(@PathVariable(name = "id") int id) {
        stockService.deleteProduct(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
