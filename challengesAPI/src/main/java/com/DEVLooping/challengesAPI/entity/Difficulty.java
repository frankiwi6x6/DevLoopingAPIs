package com.DEVLooping.challengesAPI.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "difficulty")
public class Difficulty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_difficulty")
    private int id_difficulty;

    @Column(name = "desc_difficulty")
    private String desc_difficulty;

    public Difficulty() {
    }

    public Difficulty(String desc_difficulty) {
        this.desc_difficulty = desc_difficulty;
    }

    public int getId_difficulty() {
        return id_difficulty;
    }

    public void setId_difficulty(int id_difficulty) {
        this.id_difficulty = id_difficulty;
    }

    public String getDesc_difficulty() {
        return desc_difficulty;
    }

    public void setDesc_difficulty(String desc_difficulty) {
        this.desc_difficulty = desc_difficulty;
    }

    @Override
    public String toString() {
        return "Difficulty{" +
                "id_difficulty=" + id_difficulty +
                ", desc_difficulty='" + desc_difficulty + '\'' +
                '}';
    }

}
