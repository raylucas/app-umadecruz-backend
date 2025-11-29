package com.umadecruz.app.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="app_users")
@Builder
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthDate;
    private Integer age;
    private String phone;
    private String address;
    private String neighborhood;
    private String city;
    private String zip;
    private String congregation;
    private String email;
    private LocalDate baptismDate;
    private String role = "USER";
    private String passwordHash;
    private LocalDateTime createdAt = LocalDateTime.now();
}

