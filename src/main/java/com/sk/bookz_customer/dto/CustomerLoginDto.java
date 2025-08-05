package com.sk.bookz_customer.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerLoginDto {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;


    @JsonCreator
    public static  CustomerLoginDto customerFactory(@JsonProperty("email") String email, @JsonProperty("password") String password) {
        return new CustomerLoginDto(email, password);
    }
}
