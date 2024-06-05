package com.DEVLooping.challengesAPI.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHALLENGE")
public class ChallengeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_challenge;
    private String title;
    private String desc_challenge;
    private String content;
    private Integer category_id;
    private Integer difficulty_id;
    private Integer type_id;
    private Date start_at;
    private Date end_at;

    public ChallengeRequest() {
    }

    public ChallengeRequest(Integer id_challenge, String title, String desc_challenge, String content, Integer category_id, Integer difficulty_id, Integer type_id, Date start_at, Date end_at) {
        this.id_challenge = id_challenge;
        this.title = title;
        this.desc_challenge = desc_challenge;
        this.content = content;
        this.category_id = category_id;
        this.difficulty_id = difficulty_id;
        this.type_id = type_id;
        this.start_at = start_at;
        this.end_at = end_at;
    }

    public Integer getId_challenge() {
        return id_challenge;
    }

    public void setId_challenge(Integer id_challenge) {
        this.id_challenge = id_challenge;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getDifficulty_id() {
        return difficulty_id;
    }

    public void setDifficulty_id(Integer difficulty_id) {
        this.difficulty_id = difficulty_id;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public Date getStart_at() {
        return start_at;
    }

    public void setStart_at(Date start_at) {
        this.start_at = start_at;
    }

    public Date getEnd_at() {
        return end_at;
    }

    public void setEnd_at(Date end_at) {
        this.end_at = end_at;
    }

}
