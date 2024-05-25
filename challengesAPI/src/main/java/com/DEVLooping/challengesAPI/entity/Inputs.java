package com.DEVLooping.challengesAPI.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "input")
public class Inputs {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "ID_INPUT")
    private int id;

    @Column(name = "INPUT_VALUE")
    private String value;

    @Column(name = "TEST_ID")
    private int test_id;

    public Inputs() {
    }

    public Inputs(int id, String value, int test_id) {
        this.id = id;
        this.value = value;
        this.test_id = test_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setvalue(String value) {
        this.value = value;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }




}
