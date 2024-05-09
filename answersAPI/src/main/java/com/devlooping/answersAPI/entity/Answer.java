package main.java.com.devlooping.answersAPI.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "CHALLENGE_USER")
public class Answer {

    @Column(name = "USER_id_user")
    private int id_user;

    @Column(name = "CHALLENGE_id_challenge")
    private int id_challenge;

    @Column(name = "answer_code")
    private String answer_code;

    @Column(name = "ANSWER_STATUS_id")
    private int id_status;

    @Column(name = "answer_dedicated_time")
    private int answer_dedicated_time;

    @Column(name = "answer_date")
    private String answer_date;

    @Column(name = "tries")
    private int tries;

    public Answer() {
    }

    public Answer(int id_user, int id_challenge, String answer_code, int id_status, int answer_dedicated_time,
            String answer_date, int tries) {
        this.id_user = id_user;
        this.id_challenge = id_challenge;
        this.answer_code = answer_code;
        this.id_status = id_status;
        this.answer_dedicated_time = answer_dedicated_time;
        this.answer_date = answer_date;
        this.tries = tries;
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

    public int getAnswer_dedicated_time() {
        return answer_dedicated_time;
    }

    public void setAnswer_dedicated_time(int answer_dedicated_time) {
        this.answer_dedicated_time = answer_dedicated_time;
    }

    public String getAnswer_date() {
        return answer_date;
    }

    public void setAnswer_date(String answer_date) {
        this.answer_date = answer_date;
    }

    public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }

}
