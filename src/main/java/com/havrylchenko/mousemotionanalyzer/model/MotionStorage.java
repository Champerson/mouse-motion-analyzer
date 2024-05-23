package com.havrylchenko.mousemotionanalyzer.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "motion_storage")
@NamedQuery(name = "MotionStorage.findByUser", query = "select m from MotionStorage m where m.user = :User")
public class MotionStorage {

    @Id
    @Column(name = "motions_storage_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "motion_storage_user_id")
    private User user;
    @Column(name = "motion_list_X")
    @ElementCollection(targetClass = Character.class, fetch = FetchType.EAGER)
    private List<Character> characterListByX;
    @Column(name = "motion_list_y")
    @ElementCollection(targetClass = Character.class, fetch = FetchType.EAGER)
    private List<Character> characterListByY;

    public MotionStorage(List<Character> characterListByX, List<Character> characterListByY) {
        this.characterListByX = characterListByX;
        this.characterListByY = characterListByY;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
