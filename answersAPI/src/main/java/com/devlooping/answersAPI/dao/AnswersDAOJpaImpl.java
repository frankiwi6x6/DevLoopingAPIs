package main.java.com.devlooping.answersAPI.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import main.java.com.devlooping.answersAPI.entity.Answer;

public class AnswersDAOJpaImpl implements AnswersDAO {

    private EntityManager entityManager;

    @Override
    public List<Answer> findAll() {
        TypedQuery<Answer> theQuery = entityManager.createQuery("from Answers", Answer.class);
        List<Answer> answers = theQuery.getResultList();
        return answers;
    }

    @Override
    public Answer findById(int theId) {
        Answer answers = entityManager.find(Answer.class, theId);
        return answers;

    }

    @Override
    public Answer save(Answer theUser) {
        Answer dbAnswers = entityManager.merge(theUser);
        return dbAnswers;

    }

    @Override
    public void deleteById(int theId) {
        Answer dbAnswers = entityManager.find(Answer.class, theId);
        entityManager.remove(dbAnswers);

    }
}