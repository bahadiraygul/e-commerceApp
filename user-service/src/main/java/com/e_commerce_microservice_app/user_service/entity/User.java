package com.e_commerce_microservice_app.user_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tcNo;

    @Column(unique = true, nullable = false)
    private String userName;

    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Column(unique = true, nullable = false)
    private String phone;

    private String gender;

    private int age;

    private String address;

    private String role;

    private boolean isActive;
}
