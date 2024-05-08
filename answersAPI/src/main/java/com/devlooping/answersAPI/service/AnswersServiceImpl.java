package main.java.com.devlooping.answersAPI.service;

import main.java.com.devlooping.answersAPI.dao.AnswersDAO;
import main.java.com.devlooping.answersAPI.entity.Answer;
import java.util.List;


public class AnswersServiceImpl implements AnswersService {

    private AnswerDAO answerDAO;

    public AnswersServiceImpl(AnswersDAO theAnswersDAO) {
        answerDAO = theAnswersDAO;
    }

    @Override
    public List<Answer> findAll() {
        return answerDAO.findAll();
    }

    @Override
    public Answer findById(int theId) {
        return answerDAO.findById(theId);
    }
    
    @Override
    public Answer save(Answer theUser) {
        return answerDAO.save(theUser);
    }

    @Override
    public void deleteById(int theId) {
        answersDAO.deleteById(theId);
    }

}
