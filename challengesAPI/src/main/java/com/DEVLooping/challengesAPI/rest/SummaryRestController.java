package com.DEVLooping.challengesAPI.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DEVLooping.challengesAPI.entity.ChallengeSummary;
import com.DEVLooping.challengesAPI.entity.ErrorResponse;
import com.DEVLooping.challengesAPI.service.ChallengeSummaryService;

@RestController
@RequestMapping("/api")
public class SummaryRestController {

    ErrorResponse errorResponse;
    private ChallengeSummaryService challengeSummaryService;

    public SummaryRestController(ChallengeSummaryService theChallengeSummaryService) {
        challengeSummaryService = theChallengeSummaryService;
    }

    @GetMapping("/challenges/detail/")
    public ResponseEntity<?> findAll() {
        List<ChallengeSummary> theChallenges = challengeSummaryService.findAll();
        if (theChallenges.size() < 1) {
            return ResponseEntity.status(
                    HttpStatus.NOT_FOUND).body(
                            new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                                    "No challenges found"));
        }
        return ResponseEntity.ok(theChallenges);
    }

    @GetMapping("/challenges/detail/{challengeId}")
    public ResponseEntity<?> findById(@PathVariable int challengeId) {
        ChallengeSummary theChallenge = challengeSummaryService.findById(challengeId);
        if (theChallenge == null) {
            return ResponseEntity.status(
                    HttpStatus.NOT_FOUND).body(
                            new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                                    "Challenge with id " + challengeId + " not found"));
        }
        return ResponseEntity.ok(theChallenge);
    }

    

}
