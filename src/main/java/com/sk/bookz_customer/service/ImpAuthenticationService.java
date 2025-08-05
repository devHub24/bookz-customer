package com.sk.bookz_customer.service;

import com.sk.bookz_customer.dto.CustomerLoginDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ImpAuthenticationService implements IAuthenticateService{

    private final AuthenticationManager authenticationManager;
    private final IJwtServices jwtServices;

    public ImpAuthenticationService(AuthenticationManager authenticationManager, IJwtServices jwtServices) {
        this.authenticationManager = authenticationManager;
        this.jwtServices = jwtServices;
    }

    @Override
    public Map<String,String> authenticate(CustomerLoginDto customerLogin) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customerLogin.getEmail(), customerLogin.getPassword()));
        return Map.of("Authorization", "Bearer "+jwtServices.getToken(customerLogin.getEmail()));

    }


}
