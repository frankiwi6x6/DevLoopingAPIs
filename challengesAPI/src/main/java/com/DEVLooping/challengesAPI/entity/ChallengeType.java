package com.DEVLooping.challengesAPI.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHALLENGE_TYPE")
public class ChallengeType {

    @Id
    @Column(name = "id_type")
    private int id_type;

    @Column(name = "desc_type")
    private String desctype;

    public ChallengeType() {
    }

    public ChallengeType(int id_type, String desctype) {
        this.id_type = id_type;
        this.desctype = desctype;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public String getDesctype() {
        return desctype;
    }

    public void setDesctype(String desctype) {
        this.desctype = desctype;
    }

    @Override
    public String toString() {
        return "ChallengeType [id_type=" + id_type + ", desctype=" + desctype + "]";
    }

    

}
