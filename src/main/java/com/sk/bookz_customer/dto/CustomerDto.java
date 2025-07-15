package com.sk.bookz_customer.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sk.bookz_customer.constants.CustomerConstants;
import com.sk.bookz_customer.constants.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {

    @JsonProperty(value = "id", required = false)
    private Long id;
    @JsonProperty(value = "name", required = true)
    private String name;
    @JsonProperty(value="email",required = true)
    private String email;
    @JsonProperty(value = "password", required = true, access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @JsonProperty(value = "number", required = true)
    private String number;
    @JsonProperty(value = "status", required = false)
    private CustomerStatus customerStatus;
    @JsonProperty(value="dateOfBirth", required = true,access = JsonProperty.Access.WRITE_ONLY)
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = CustomerConstants.DOB_PATTERN)
    private LocalDate dateOfBirth;

    @JsonCreator
    public static CustomerDto createCustomerDto(Long id, String name, String email, String password, String number, CustomerStatus status) {
        return CustomerDto.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .number(number)
                .customerStatus(status)
                .build();
    }

}
