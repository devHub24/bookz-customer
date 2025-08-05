package com.sk.bookz_customer.controller;

import com.sk.bookz_customer.dto.CustomerLoginDto;
import com.sk.bookz_customer.service.IAuthenticateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {

    private final IAuthenticateService authenticateService;

    public AuthenticationController(IAuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @GetMapping("/test")
    public String test(){
        log.debug("test");
        return "hi";
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody CustomerLoginDto customerLoginDto) {
        Map<String,String> headerMap = authenticateService.authenticate(customerLoginDto);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Authorization",headerMap.get("Authorization"))
                .build();
    }
}
