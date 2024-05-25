package com.DEVLooping.challengesAPI.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.DEVLooping.challengesAPI.dao.ChallengeSummaryDAO;
import com.DEVLooping.challengesAPI.entity.ChallengeSummary;

@Service
public class  ChallengeSummaryService {

    private ChallengeSummaryDAO challengeSummaryDAO;

    public ChallengeSummaryService(ChallengeSummaryDAO theChallengeSummaryDAO) {
        challengeSummaryDAO = theChallengeSummaryDAO;
    }

    public List<ChallengeSummary> findAll() {
        return challengeSummaryDAO.findAll();
    }

    public ChallengeSummary findById(int id) {
        return challengeSummaryDAO.findById(id);
    }

    public void save(ChallengeSummary challengeSummary) {
        challengeSummaryDAO.save(challengeSummary);
    }

}
