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

    public MotionStorage(List<Character> characterListByX, List<Character> characterListByY) {
        this.characterListByX = characterListByX;
        this.characterListByY = characterListByY;
    }

}
