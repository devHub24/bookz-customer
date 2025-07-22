package com.sk.bookz_customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorDto {

    private String message;
    private String path;
    private int code;
    private LocalDateTime timestamp;
}
