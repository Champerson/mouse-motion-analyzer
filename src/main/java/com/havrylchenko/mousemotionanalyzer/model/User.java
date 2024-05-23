package com.havrylchenko.mousemotionanalyzer.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAllByRole", query = "select u from User u where u.login = :ROLE_USER")
@NamedQuery(name = "User.findByUsername", query = "select u from User u where u.login = :login")
public class User {

    @Id
    @Column(name = "user_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name", nullable = false, unique = true)
    private String username;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MotionStorage> motionStorage;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
