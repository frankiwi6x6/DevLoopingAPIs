package com.DEVLooping.challengesAPI.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.DEVLooping.challengesAPI.entity.Challenge;
import com.DEVLooping.challengesAPI.entity.ErrorResponse;
import com.DEVLooping.challengesAPI.service.ChallengeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChallengeRestController {

    ErrorResponse errorResponse;

    private ChallengeService challengeService;

    public ChallengeRestController(ChallengeService theChallengeService) {
        challengeService = theChallengeService;
    }

    @GetMapping("/challenges")
    public ResponseEntity<?> findAll() {
        List<Challenge> theChallenges = challengeService.findAll();
        if (theChallenges.size() < 1) {
            return ResponseEntity.status(
                    HttpStatus.NOT_FOUND).body(
                            new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                                    "No challenges found"));
        }
        return ResponseEntity.ok(theChallenges);
    }

    @GetMapping("/challenges/{challengeId}")
    public ResponseEntity<?> getChallenge(@PathVariable int challengeId) {
        Challenge theChallenge = challengeService.findById(challengeId);
        if (theChallenge == null) {
            return ResponseEntity.status(
                    HttpStatus.NOT_FOUND).body(
                            new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                                    "No challenges found with id " + challengeId));

        }
        return ResponseEntity.ok(theChallenge);
    }

    @GetMapping("/challenges/difficulty/{difficulty}")
    public ResponseEntity<?> getChallengesByDifficulty(@PathVariable int difficulty) {
        List<Challenge> theChallenges = challengeService.findByDifficulty(difficulty);
        if (theChallenges.size() < 1) {
            return ResponseEntity.status(
                    HttpStatus.NOT_FOUND).body(
                            new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                                    "No challenges found with difficulty level " + difficulty));

        }
        return ResponseEntity.ok(theChallenges);
    }

    @PostMapping("/challenges")
    public ResponseEntity<Challenge> addChallenge(@RequestBody Challenge theChallenge) {
        theChallenge.setId(0);
        challengeService.save(theChallenge);
        return ResponseEntity.status(HttpStatus.CREATED).body(theChallenge);
    }

    @PutMapping("/challenges/{challengeId}")
    public ResponseEntity<?> updateChallenge(@RequestBody Challenge theChallenge, @PathVariable int challengeId) {
        Challenge tempChallenge = challengeService.findById(challengeId);
        if (tempChallenge == null) {
            return ResponseEntity.status(
                    HttpStatus.NOT_FOUND).body(
                            new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                                    "No challenges found with id " + challengeId));
        }
        theChallenge.setId(challengeId);
        challengeService.save(theChallenge);
        return ResponseEntity.ok(theChallenge);
    }

    @DeleteMapping("/challenges/{challengeId}")
    public ResponseEntity<?> deleteChallenge(@PathVariable int challengeId) {
        Challenge tempChallenge = challengeService.findById(challengeId);
        if (tempChallenge == null) {
            return ResponseEntity.status(
                    HttpStatus.NOT_FOUND).body(
                            new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                                    "No challenges found with id " + challengeId));
        }
        challengeService.deleteById(challengeId);
        return ResponseEntity.ok("Deleted challenge id - " + challengeId);
    }

    @RestControllerAdvice
    class ChallengeRestControllerAdvice {
        @ExceptionHandler
        public ResponseEntity<String> handleNotFoundException(ChallengeNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{ status : " + HttpStatus.NOT_FOUND.value() + ", message:" + ex.getMessage() + "}");
        }

        @ExceptionHandler
        public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    "{ status : " + HttpStatus.INTERNAL_SERVER_ERROR.value() + ", message:" + ex.getMessage() + "}");
        }

    }

    class ChallengeNotFoundException extends RuntimeException {
        public ChallengeNotFoundException(String message) {
            super(message);
        }
    }
}
