package com.DEVLooping.challengesAPI.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

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

    @Column(name = "content")
    private String content;

    @Column(name = "default_value")
    private String defaultValue;

    @Column(name = "start_at")
    private String start_at;

    @Column(name = "end_at")
    private String end_at;

    @Column(name = "creator_id")
    private Integer creator_id;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHALLENGE_TYPE_id_type")
    private ChallengeType challengeType;

    @OneToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DIFFICULTY_id_difficulty")
    private Difficulty difficulty;
    

    public Challenge() {
    }
    public Challenge(int id, String title, String desc_challenge, String content, String defaultValue, String start_at,
            String end_at, Integer creator_id, ChallengeType challengeType, Category category, Difficulty difficulty) {
        this.id = id;
        this.title = title;
        this.desc_challenge = desc_challenge;
        this.content = content;
        this.defaultValue = defaultValue;
        this.start_at = start_at;
        this.end_at = end_at;
        this.creator_id = creator_id;
        this.challengeType = challengeType;
        this.category = category;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ChallengeType getChallengeType() {
        return challengeType;
    }

    public void setChallengeType(ChallengeType challengeType) {
        this.challengeType = challengeType;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public String getDefaultValue() {
        return defaultValue;
    }




    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }




    public Integer getCreator_id() {
        return creator_id;
    }




    public void setCreator_id(Integer creator_id) {
        this.creator_id = creator_id;
    }

    
    

    
}
