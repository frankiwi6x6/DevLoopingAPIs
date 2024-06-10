package com.DEVLooping.challengesAPI.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "challenge_detail_view")
public class ChallengeSummary {

    @Id
    @Column(name = "challenge_id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "desc_challenge")
    private String description;
    @Column(name = "content")
    private String content;
    @Column(name = "default_value")
    private String default_value;
    @Column(name = "start_at")
    private String start_at;
    @Column(name = "end_at")
    private String end_at;
    @Column(name = "creator_id")
    private Integer creator_id;
    @Column(name = "tips")
    private String tips;
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToOne
    @JoinColumn(name = "DIFFICULTY_id_difficulty")
    private Difficulty difficulty;
    @OneToOne
    @JoinColumn(name = "CHALLENGE_TYPE_id_type")
    private ChallengeType challengeType;
    @OneToMany
    @JoinColumn(name = "test_id")
    private List<Inputs> inputs;
    @OneToMany
    @JoinColumn(name = "test_id")
    private List<Outputs> outputs;

    public ChallengeSummary() {
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

    public String getDefault_value() {
        return default_value;
    }

    public void setDefault_value(String default_value) {
        this.default_value = default_value;
    }

    public Integer getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(Integer creator_id) {
        this.creator_id = creator_id;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
