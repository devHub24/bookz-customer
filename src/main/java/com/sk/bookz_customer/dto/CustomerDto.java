package com.sk.bookz_customer.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sk.bookz_customer.annotations.DateOfBirth;
import com.sk.bookz_customer.annotations.Password;
import com.sk.bookz_customer.annotations.PhoneNumber;
import com.sk.bookz_customer.annotations.markers.OnCreate;
import com.sk.bookz_customer.annotations.markers.OnUpdate;
import com.sk.bookz_customer.constants.CustomerConstants;
import com.sk.bookz_customer.constants.CustomerStatus;
import com.sk.bookz_customer.utils.DateFormatUtils;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "User name can't be empty", groups = {OnCreate.class})
    @JsonProperty(value = "name", required = true)
    private String name;

    @NotBlank(message = "Email can't be blank", groups = {OnCreate.class})
    @Email(message = "Invalid email id",groups = {OnCreate.class,OnUpdate.class})
    @JsonProperty(value="email",required = true)
    private String email;

    @NotBlank(message = "Password can't be blank", groups = {OnCreate.class})
    @Password(message = "Password Doesn't match our policy",  groups = {OnCreate.class,OnUpdate.class})
    @JsonProperty(value = "password", required = true, access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotBlank(message = "Password can't be blank", groups = {OnCreate.class})
    @PhoneNumber(groups = {OnCreate.class, OnUpdate.class})
    @JsonProperty(value = "number", required = true)
    private String number;

    @JsonProperty(value = "status", required = false)
    private CustomerStatus customerStatus;

    @DateOfBirth(groups = {OnCreate.class, OnUpdate.class})
    @JsonProperty(value="dateOfBirth", required = true,access = JsonProperty.Access.WRITE_ONLY)
    @JsonDeserialize(using = DateFormatUtils.class)
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = CustomerConstants.DOB_PATTERN)
    private LocalDate dateOfBirth;

    @JsonCreator
    public static CustomerDto createCustomerDto(Long id, String name, String email, String password, String number, CustomerStatus status, LocalDate dateOfBirth) {
        return CustomerDto.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .number(number)
                .customerStatus(status)
                .dateOfBirth(dateOfBirth)
                .build();
    }

}
