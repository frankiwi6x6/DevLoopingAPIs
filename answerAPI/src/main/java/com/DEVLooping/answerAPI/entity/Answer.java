package com.DEVLooping.answerAPI.entity;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHALLENGE_USER")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_challenge_user")
    private int id;

    @Column(name = "USER_id_user")
    private int id_user;

    @Column(name = "CHALLENGE_id_challenge")
    private int id_challenge;

    @Column(name = "answer_code")
    private String answer_code;

    @Column(name = "ANSWER_STATUS_id")
    private int id_status;

    @Column(name = "start_date")
    private Date start_date;

    @Column(name = "segundos_dedicados")
    private int segundos_dedicados;

    @Column(name = "end_date")
    private Date end_date;

    @Column(name = "tries")
    private int tries;

    public Answer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_challenge() {
        return id_challenge;
    }

    public void setId_challenge(int id_challenge) {
        this.id_challenge = id_challenge;
    }

    public String getAnswer_code() {
        return answer_code;
    }

    public void setAnswer_code(String answer_code) {
        this.answer_code = answer_code;
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public int getSegundos_dedicados() {
        return segundos_dedicados;
    }

    public void setSegundos_dedicados(int segundos_dedicados) {
        this.segundos_dedicados = segundos_dedicados;
    }

    public Date getEndDate() {
        return end_date;
    }

    public void setEndDate(Date end_date) {
        this.end_date = end_date;
    }

    public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }

    public Answer(int id, int id_user, int id_challenge, String answer_code, int id_status, Date start_date,
            int segundos_dedicados, Date end_date, int tries) {
        this.id = id;
        this.id_user = id_user;
        this.id_challenge = id_challenge;
        this.answer_code = answer_code;
        this.id_status = id_status;
        this.start_date = start_date;
        this.segundos_dedicados = segundos_dedicados;
        this.end_date = end_date;
        this.tries = tries;
    }

    
    



}
