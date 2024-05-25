package com.DEVLooping.challengesAPI.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "output")
public class Outputs {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "ID_output")
    private int id;

    @Column(name = "output_VALUE")
    private String value;

    @Column(name = "TEST_ID")
    private int test_id;

    public Outputs() {
    }

    public Outputs(int id, String value, int test_id) {
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

    public void setValue(String value) {
        this.value = value;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

}
