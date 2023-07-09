package com.example.myOrderManagement.Controller;

import com.example.myOrderManagement.Request.LoginRequest;
import com.example.myOrderManagement.Response.WebResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/auth")
    public class AuthenticationController {

        @PostMapping("/signIn")
        @Operation(
                description = "SignIn service",
                responses = {
                        @ApiResponse(
                                responseCode = "200",
                                description = "Successfully Sign In !",
                                content = @Content(
                                        mediaType ="application/json",
                                        examples = {
                                                @ExampleObject(
                                                        value = "{\"code\" : 200, \"Status\" : \"OK!\", \"Message\" :\"Successfully SignIn!\"}"
                                                ),
                                        }
                                )
                        )
                }
        )
        public ResponseEntity<?> signIn(@io.swagger.v3.oas.annotations.parameters.RequestBody(
                content = @Content(
                        mediaType = "application/json",
                        examples = {
                                @ExampleObject(
                                        value = "{\"username\" : \"yazan\", \"password\" : \"yazan123456\"}"
                                ),
                        }
                )) @RequestBody LoginRequest request) {
            return new ResponseEntity<>(new WebResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "Successfully signed in!"), HttpStatus.OK);
        }
    }
