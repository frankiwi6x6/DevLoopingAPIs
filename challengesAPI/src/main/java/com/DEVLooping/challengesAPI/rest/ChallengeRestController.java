package com.DEVLooping.challengesAPI.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.DEVLooping.challengesAPI.dto.ChallengeRequest;
import com.DEVLooping.challengesAPI.entity.Challenge;
import com.DEVLooping.challengesAPI.entity.ErrorResponse;
import com.DEVLooping.challengesAPI.service.ChallengeRequestService;
import com.DEVLooping.challengesAPI.service.ChallengeService;

@RestController
@RequestMapping("/api")
public class ChallengeRestController {

    ErrorResponse errorResponse;
    private ChallengeRequestService crService;
    private ChallengeService challengeService;

    public ChallengeRestController(ChallengeService theChallengeService, ChallengeRequestService theCrService) {
        challengeService = theChallengeService;
        crService = theCrService;
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

    @PostMapping("/challenges")
    public void saveChallenge(@RequestBody ChallengeRequest request) {
        try {
            crService.saveChallenge(request);
        } catch (Exception e) {
            // Manejo de la excepción
            e.printStackTrace();
            throw new RuntimeException("Error al guardar el desafío");
        }
    }

    @PostMapping("/challenges/{challengeId}")
    public void updateChallenge(@PathVariable int challengeId, @RequestBody ChallengeRequest request) {
        try {
            crService.updateChallenge(request, challengeId);
        } catch (Exception e) {
            // Manejo de la excepción
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el desafío");
        }
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
