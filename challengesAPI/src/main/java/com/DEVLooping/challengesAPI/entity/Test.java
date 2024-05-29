package com.DEVLooping.challengesAPI.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
CREATE TABLE TEST(
	ID_TEST INTEGER PRIMARY KEY AUTO_INCREMENT,
    test_description TEXT not null);
 */
@Entity
@Table(name = "test")
public class Test {
    @Id
    @Column(name = "id_test")
    private int idTest;
    
    @Column(name = "test_description")
    private String testDescription;
    
    
    public Test() {
        super();
    }

    public Test(int idTest, String testDescription) {
        super();
        this.idTest = idTest;
        this.testDescription = testDescription;
    }

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    @Override
    public String toString() {
        return "Test [idTest=" + idTest + ", testDescription=" + testDescription + "]";
    }

}
