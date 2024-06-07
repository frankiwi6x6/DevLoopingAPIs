package com.DEVLooping.challengesAPI.dto;

import java.util.Date;
import java.util.List;

public class ChallengeRequest {

    private String title;
    private String descChallenge;
    private String content;
    private Integer categoryId;
    private Integer difficultyId;
    private Integer typeId;
    private Date startAt;
    private Date endAt;
    private Integer creator_id;
    private String defaultValue;
    private List<String> tipTitles;
    private List<String> tipDescs;
    private String testDescription;
    private List<String> inputValues;
    private List<String> outputValues;

    public ChallengeRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescChallenge() {
        return descChallenge;
    }

    public void setDescChallenge(String descChallenge) {
        this.descChallenge = descChallenge;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getDifficultyId() {
        return difficultyId;
    }

    public void setDifficultyId(Integer difficultyId) {
        this.difficultyId = difficultyId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public List<String> getTipTitles() {
        return tipTitles;
    }

    public void setTipTitles(List<String> tipTitles) {
        this.tipTitles = tipTitles;
    }

    public List<String> getTipDescs() {
        return tipDescs;
    }

    public void setTipDescs(List<String> tipDescs) {
        this.tipDescs = tipDescs;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    public List<String> getInputValues() {
        return inputValues;
    }

    public void setInputValues(List<String> inputValues) {
        this.inputValues = inputValues;
    }

    public List<String> getOutputValues() {
        return outputValues;
    }

    public void setOutputValues(List<String> outputValues) {
        this.outputValues = outputValues;
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
