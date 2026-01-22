package com.example.shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String telephone;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ElementCollection
    private List<String> permissions;

    public enum Role {
        SUP_ADMIN,
        ADMIN,
        VENDEUR,
        TECHNICIEN
    }
}
