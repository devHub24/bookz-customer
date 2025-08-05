package com.sk.bookz_customer.service;

import com.sk.bookz_customer.dto.CustomerLoginDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Map;

public interface IAuthenticateService {

    public Map<String,String> authenticate(CustomerLoginDto customerLogin);

}
