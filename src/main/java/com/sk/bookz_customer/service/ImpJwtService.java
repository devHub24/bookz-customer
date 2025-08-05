package com.sk.bookz_customer.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;


import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ImpJwtService implements IJwtServices{

    private final Environment env;
    private final ICustomerService customerService;

    public ImpJwtService(Environment env, ICustomerService customerService) {
        this.env = env;
        this.customerService = customerService;
    }

    @Override
    public String getToken(String principal) {
        return Jwts.builder()
                .claim("roles", List.of(new SimpleGrantedAuthority("ROLE_USER")))
                .subject(customerService.findByEmail(principal).getId().toString())
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .signWith(getSign())
                .compact();
    }

    @Override
    public boolean validateToken(String token) {

        return !getClaims(token).getExpiration().before(new Date());
    }

    @Override
    public String extractDetails(String token) {
        return getClaims(token).getSubject();
    }

    @Override
    public Claims getClaims(String token) {
        SecretKey key  = getSign();
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSign(){
        String authKey =env.getProperty("authorization.key");
        assert authKey != null;
        return Optional.of(Keys.hmacShaKeyFor(authKey.getBytes(StandardCharsets.UTF_8)))
                .orElseThrow(()-> new BadCredentialsException("Invalid auth key"));
    }
}
