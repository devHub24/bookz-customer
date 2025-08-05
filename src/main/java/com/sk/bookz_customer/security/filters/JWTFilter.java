package com.sk.bookz_customer.security.filters;

import com.sk.bookz_customer.service.IAuthenticateService;
import com.sk.bookz_customer.service.IJwtServices;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component("jwtFilter")
@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    private final IAuthenticateService authenticateService;
    private final IJwtServices jwtServices;

    public JWTFilter(IAuthenticateService authenticateService, IJwtServices jwtServices) {
        this.authenticateService = authenticateService;
        this.jwtServices = jwtServices;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try{
            if(request.getRequestURI().endsWith("/login")){
                filterChain.doFilter(request,response);
                return;
            }
            else {
                String token = extractToken(request);
                if (token == null) {
                    filterChain.doFilter(request, response);
                    return;
                }
                if (jwtServices.validateToken(token)) {
                    Claims claims = jwtServices.getClaims(token);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(claims.getSubject(),
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_USER")));
                    // authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(request, response);
                }
            }
        }catch(Exception ex){
            log.error("JWT Filter error: {}", ex.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

    }

    private String extractToken(HttpServletRequest request) {
        String result = request.getHeader("Authorization");
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            result = token.substring(7).trim();
        }
        return result;
    }
}

