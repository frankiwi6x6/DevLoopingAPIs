package com.DEVLooping.challengesAPI.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/*
 CREATE VIEW challenge_detail_view as
SELECT 
    c.id_challenge as id,
    c.title as title,
    c.desc_challenge as description,
    c.content as content,
    c.start_at,
    c.end_at,
    c.CATEGORY_ID as category_id,
    d.id_difficulty,
    CTY.id_type,
    CT.TEST_ID ,
    TIP.CHALLENGE_TIP_ID as id_tip
FROM CHALLENGE C
JOIN CHALLENGE_TIP TIP ON TIP.CHALLENGE_ID = C.ID_CHALLENGE
JOIN CHALLENGE_TESTS CT ON CT.CHALLENGE_ID = C.ID_CHALLENGE
JOIN DIFFICULTY D ON C.DIFFICULTY_id_difficulty = D.id_difficulty
JOIN CHALLENGE_TYPE CTY ON C.CHALLENGE_TYPE_id_type = CTY.id_type
JOIN TEST T ON CT.TEST_ID = T.ID_TEST;


 */

@Entity
@Table(name = "challenge_detail_view")
public class ChallengeSummary {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "content")
    private String content;
    @Column(name = "start_at")
    private String start_at;
    @Column(name = "end_at")
    private String end_at;
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToOne
    @JoinColumn(name = "id_difficulty")
    private Difficulty difficulty;
    @OneToOne
    @JoinColumn(name = "id_type")
    private ChallengeType challengeType;
    @OneToMany
    @JoinColumn(name = "test_id")
    private List<Inputs> inputs;
    @OneToMany
    @JoinColumn(name = "test_id")
    private List<Outputs> outputs;
    public ChallengeSummary() {
    }

    public ChallengeSummary(int id, String title, String description, String content, String start_at, String end_at,
            Category category, Difficulty difficulty, ChallengeType challengeType, List<Inputs> inputs,
            List<Outputs> outputs) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.start_at = start_at;
        this.end_at = end_at;
        this.category = category;
        this.difficulty = difficulty;
        this.challengeType = challengeType;
        this.inputs = inputs;
        this.outputs = outputs;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public ChallengeType getChallengeType() {
        return challengeType;
    }

    public void setChallengeType(ChallengeType challengeType) {
        this.challengeType = challengeType;
    }

    public List<Inputs> getInputs() {
        return inputs;
    }

    public void setInputs(List<Inputs> inputs) {
        this.inputs = inputs;
    }

    public List<Outputs> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<Outputs> outputs) {
        this.outputs = outputs;
    }

    

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ChallengeSummary [category=" + category + ", challengeType=" + challengeType + ", content=" + content
                + ", description=" + description + ", difficulty=" + difficulty + ", end_at=" + end_at + ", id=" + id
                + ", inputs=" + inputs + ", outputs=" + outputs + ", start_at=" + start_at + ", title=" + title
                + "]";
    }
}
