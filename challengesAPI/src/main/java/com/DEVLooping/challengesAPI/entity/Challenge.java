package com.DEVLooping.challengesAPI.entity;

import java.util.List;

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

    @Column(name = "content")
    private String content;

    @Column(name = "start_at")
    private String start_at;

    @Column(name = "end_at")
    private String end_at;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHALLENGE_TYPE_id_type")
    private ChallengeType challengeType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DIFFICULTY_id_difficulty")
    private Difficulty difficulty;
    
    @OneToMany(mappedBy = "challenge", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ExpectedOutput> expectedOutputs;


    public Challenge() {
    }

    


    public Challenge(int id, String title, String desc_challenge, String content, String start_at, String end_at,
            ChallengeType challengeType, Difficulty difficulty, List<ExpectedOutput> expectedOutputs) {
        this.id = id;
        this.title = title;
        this.desc_challenge = desc_challenge;
        this.content = content;
        this.start_at = start_at;
        this.end_at = end_at;
        this.challengeType = challengeType;
        this.difficulty = difficulty;
        this.expectedOutputs = expectedOutputs;
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




    public List<ExpectedOutput> getExpectedOutputs() {
        return expectedOutputs;
    }




    public void setExpectedOutputs(List<ExpectedOutput> expectedOutputs) {
        this.expectedOutputs = expectedOutputs;
    }

}
