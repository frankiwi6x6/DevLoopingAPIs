package com.DEVLooping.challengesAPI.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "expected_output")
public class ExpectedOutput {
    
    @Id
    @Column(name = "id_expected_output")
    private int id;

    @ManyToOne
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;


    @Column(name = "input1")
    private String input1;

    @Column(name = "input2")
    private String input2;

    @Column(name = "input3")
    private String input3;

    @Column(name = "input4")
    private String input4;

    @Column(name = "output")
    private String output;

    public ExpectedOutput() {
    }

    public ExpectedOutput(int id, String input1, String input2, String input3, String input4, String output) {
        this.id = id;
        this.input1 = input1;
        this.input2 = input2;
        this.input3 = input3;
        this.input4 = input4;
        this.output = output;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInput1() {
        return input1;
    }

    public void setInput1(String input1) {
        this.input1 = input1;
    }

    public String getInput2() {
        return input2;
    }

    public void setInput2(String input2) {
        this.input2 = input2;
    }

    public String getInput3() {
        return input3;
    }

    public void setInput3(String input3) {
        this.input3 = input3;
    }

    public String getInput4() {
        return input4;
    }

    public void setInput4(String input4) {
        this.input4 = input4;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }



}
