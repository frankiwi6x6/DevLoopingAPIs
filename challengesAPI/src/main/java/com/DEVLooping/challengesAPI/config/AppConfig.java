package com.DEVLooping.challengesAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.DEVLooping.challengesAPI.dao.GenericDAO;
import com.DEVLooping.challengesAPI.dao.GenericDAOImpl;
import com.DEVLooping.challengesAPI.entity.Category;
import com.DEVLooping.challengesAPI.entity.Challenge;
import com.DEVLooping.challengesAPI.entity.Inputs;
import com.DEVLooping.challengesAPI.entity.Outputs;
import com.DEVLooping.challengesAPI.entity.Test;

@Configuration
public class AppConfig {

    @Bean
    public GenericDAO<Category> categoryDAO() {
        return new GenericDAOImpl<>();
    }

    @Bean
    public GenericDAO<Challenge> challengeDAO() {
        return new GenericDAOImpl<>();
    }

    @Bean
    public GenericDAO<Inputs> inputsDAO() {
        return new GenericDAOImpl<>();
    }

    @Bean
    public GenericDAO<Outputs> outputsDAO() {
        return new GenericDAOImpl<>();
    }

    @Bean
    public GenericDAO<Test> testDAO() {
        return new GenericDAOImpl<>();
    }



}
