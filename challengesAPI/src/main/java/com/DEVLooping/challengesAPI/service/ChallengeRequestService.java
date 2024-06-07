package com.DEVLooping.challengesAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DEVLooping.challengesAPI.dto.ChallengeRequest;
import com.DEVLooping.challengesAPI.repo.ChallengeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ChallengeRequestService {

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public void saveChallenge(ChallengeRequest request) throws JsonProcessingException {
        String tipTitlesJson = objectMapper.writeValueAsString(request.getTipTitles());
        String tipDescsJson = objectMapper.writeValueAsString(request.getTipDescs());
        String inputValuesJson = objectMapper.writeValueAsString(request.getInputValues());
        String outputValuesJson = objectMapper.writeValueAsString(request.getOutputValues());

        challengeRepository.SP_saveChallenge(
                request.getTitle(),
                request.getDescChallenge(),
                request.getContent(),
                request.getDefaultValue(),
                request.getCategoryId(),
                request.getDifficultyId(),
                request.getTypeId(),
                request.getStartAt(),
                request.getEndAt(),
                request.getCreator_id(),
                tipTitlesJson,
                tipDescsJson,
                request.getTestDescription(),
                inputValuesJson,
                outputValuesJson
        );
    }

    public void updateChallenge(ChallengeRequest request, int challengeId) throws JsonProcessingException {
        String tipTitlesJson = objectMapper.writeValueAsString(request.getTipTitles());
        String tipDescsJson = objectMapper.writeValueAsString(request.getTipDescs());
        String inputValuesJson = objectMapper.writeValueAsString(request.getInputValues());
        String outputValuesJson = objectMapper.writeValueAsString(request.getOutputValues());

        challengeRepository.SP_UpdateChallenge(
                challengeId,
                request.getTitle(),
                request.getDescChallenge(),
                request.getContent(),
                request.getDefaultValue(),
                request.getCategoryId(),
                request.getDifficultyId(),
                request.getTypeId(),
                request.getStartAt(),
                request.getEndAt(),
                request.getCreator_id(),
                tipTitlesJson,
                tipDescsJson,
                request.getTestDescription(),
                inputValuesJson,
                outputValuesJson
        );
    }
}
