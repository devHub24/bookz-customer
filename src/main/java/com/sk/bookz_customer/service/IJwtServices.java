package com.sk.bookz_customer.service;

import io.jsonwebtoken.Claims;

public interface IJwtServices {

    public String getToken(String principal);
    public boolean validateToken(String token);
    public String extractDetails(String token);
    public Claims getClaims(String token);

}
