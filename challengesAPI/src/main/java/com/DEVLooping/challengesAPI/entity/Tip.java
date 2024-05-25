package com.DEVLooping.challengesAPI.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 CREATE TABLE TIP (
    ID_TIP INTEGER PRIMARY KEY AUTO_INCREMENT,
    TIP_TITLE VARCHAR(255) NOT NULL,
    TIP_DESC TEXT NOT NULL,
    CHALLENGE_TIP_id INTEGER NOT NULL,
    CONSTRAINT CHALLENGE_TIP_FK FOREIGN KEY (CHALLENGE_TIP_id) REFERENCES CHALLENGE_TIP(CHALLENGE_TIP_id) ON DELETE CASCADE ON UPDATE CASCADE
);

 */

@Entity
@Table(name = "TIP")
public class Tip {
    @Id
    @Column(name = "ID_TIP")
    private int id_tip;
    @Column(name = "TIP_TITLE")
    private String tip_title;
    @Column(name = "TIP_DESC")
    private String tip_desc;
    @Column(name = "CHALLENGE_TIP_id")
    private int challenge_tip_id;

    public Tip() {
    }

    public Tip(int id_tip, String tip_title, String tip_desc, int challenge_tip_id) {
        this.id_tip = id_tip;
        this.tip_title = tip_title;
        this.tip_desc = tip_desc;
        this.challenge_tip_id = challenge_tip_id;
    }

    public int getId_tip() {
        return id_tip;
    }

    public void setId_tip(int id_tip) {
        this.id_tip = id_tip;
    }

    public String getTip_title() {
        return tip_title;
    }

    public void setTip_title(String tip_title) {
        this.tip_title = tip_title;
    }

    public String getTip_desc() {
        return tip_desc;
    }

    public void setTip_desc(String tip_desc) {
        this.tip_desc = tip_desc;
    }

    public int getChallenge_tip_id() {
        return challenge_tip_id;
    }

    public void setChallenge_tip_id(int challenge_tip_id) {
        this.challenge_tip_id = challenge_tip_id;
    }

    @Override
    public String toString() {
        return "Tip [challenge_tip_id=" + challenge_tip_id + ", id_tip=" + id_tip + ", tip_desc=" + tip_desc
                + ", tip_title=" + tip_title + "]";
    }

}