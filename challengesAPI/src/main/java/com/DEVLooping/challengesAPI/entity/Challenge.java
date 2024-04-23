package com.DEVLooping.challengesAPI.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "challenge")
public class Challenge {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_challenge")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "desc_challenge")
    private String desc_challenge;

    @Column(name = "start_at")
    private String start_at;

    @Column(name = "end_at")
    private String end_at;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DIFFICULTY_id_difficulty")
    private Difficulty difficulty;

    public Challenge() {
    }

    public Challenge(String title, String desc_challenge, String start_at, String end_at, Difficulty difficulty) {
        this.title = title;
        this.desc_challenge = desc_challenge;
        this.start_at = start_at;
        this.end_at = end_at;
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc_challenge() {
        return desc_challenge;
    }

    public void setDesc_challenge(String desc_challenge) {
        this.desc_challenge = desc_challenge;
    }

    public String getStart_at() {
        return start_at;
    }

    public void setStart_at(String start_at) {
        this.start_at = start_at;
    }

    public String getEnd_at() {
        return end_at;
    }

    public void setEnd_at(String end_at) {
        this.end_at = end_at;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    



}
