package main.java.com.devlooping.answersAPI.service;

import main.java.com.devlooping.answersAPI.entity.Answer;
import java.util.List;


public interface AnswersService {

    List<Answer> findAll();

    Answer findById(int theId);

    List<Answer> findByDifficulty(int theDifficulty);

    Answer save(Answer theUser);

    void deleteById(int theId);
}
