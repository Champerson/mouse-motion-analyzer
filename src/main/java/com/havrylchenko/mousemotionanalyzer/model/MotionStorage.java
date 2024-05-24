package com.havrylchenko.mousemotionanalyzer.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "motion_storage")
@NamedQuery(name = "MotionStorage.findByUser", query = "select m from MotionStorage m where m.user.id = :user_id")
public class MotionStorage {

    @Id
    @Column(name = "motions_storage_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @Column()
    @ElementCollection(targetClass = Character.class, fetch = FetchType.EAGER)
    private List<Character> characterListByX;
    @Column()
    @ElementCollection(targetClass = Character.class, fetch = FetchType.EAGER)
    private List<Character> characterListByY;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Character> getCharacterListByX() {
        return characterListByX;
    }

    public void setCharacterListByX(List<Character> characterListByX) {
        this.characterListByX = characterListByX;
    }

    public List<Character> getCharacterListByY() {
        return characterListByY;
    }

    public void setCharacterListByY(List<Character> characterListByY) {
        this.characterListByY = characterListByY;
    }
}
