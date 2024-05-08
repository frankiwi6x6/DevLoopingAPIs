package main.java.com.devlooping.answersAPI.dao;

import main.java.com.devlooping.answersAPI.entity.Answer;
import java.util.List;

public interface AnswerDAO {

    List<Answer> findAll();

    Answer findById(int theId);

    Answer save(Answer theUser);

    void deleteById(int theId);
}
