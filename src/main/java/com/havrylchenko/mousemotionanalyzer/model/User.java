package com.havrylchenko.mousemotionanalyzer.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAllByRole", query = "select u from User u where u.username = :ROLE_USER")
@NamedQuery(name = "User.findByUsername", query = "select u from User u where u.username = :username")
public class User {

    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column()
    private String username;
    @Column()
    private String password;
    @Column()
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

    public List<MotionStorage> getMotionStorage() {
        return motionStorage;
    }

    public void setMotionStorage(List<MotionStorage> motionStorage) {
        this.motionStorage = motionStorage;
    }
}
