package com.sk.bookz_customer.entity;

import com.sk.bookz_customer.constants.CustomerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {@Index(name = "idx_email", columnList = "email"),
        @Index(name="idx_number", columnList = "number")}
)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String number;
    private LocalDate dateOfBirth;
    private CustomerStatus customerStatus;
}
