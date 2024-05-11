package com.havrylchenko.mousemotionanalyzer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;
}
