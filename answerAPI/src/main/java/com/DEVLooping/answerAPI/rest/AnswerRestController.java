package com.DEVLooping.answerAPI.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.DEVLooping.answerAPI.entity.Answer;
import com.DEVLooping.answerAPI.entity.ErrorResponse;
import com.DEVLooping.answerAPI.service.AnswerService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping("/api")
public class AnswerRestController {

    ErrorResponse errorResponse;

    private AnswerService answerService;

    public AnswerRestController(AnswerService theAnswerService) {
        answerService = theAnswerService;
    }

    @GetMapping("/answers")
    public ResponseEntity<?> findAll() {
        List<Answer> theAnswers = answerService.findAll();
        if (theAnswers.size() < 1) {
            return ResponseEntity.status(
                    HttpStatus.NOT_FOUND).body(
                            new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                                    "No challenges found"));
        }
        return ResponseEntity.ok(theAnswers);
    }

    @GetMapping("/answers/{answerId}")
    public ResponseEntity<?> getAnswer(@PathVariable int answerId) {
        Answer theAnswer = answerService.findById(answerId);
        if (theAnswer == null) {
            return ResponseEntity.status(
                    HttpStatus.NOT_FOUND).body(
                            new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                                    "No challenges found with id " + answerId));

        }
        return ResponseEntity.ok(theAnswer);
    }

    @GetMapping("/answers/challenge/{challengeId}")
    public ResponseEntity<?> getAnswersByChallenge(@PathVariable int challengeId) {
        List<Answer> theAnswers = answerService.findByChallengeId(challengeId);
        if (theAnswers.size() < 1) {
            return ResponseEntity.status(
                    HttpStatus.NOT_FOUND).body(
                            new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                                    "No challenges found with challenge id " + challengeId));
        }
        return ResponseEntity.ok(theAnswers);
    }

    @GetMapping("/answers/user/{userId}")
    public ResponseEntity<?> getUserAnswers(@PathVariable int userId) {
        List<Answer> theAnswers = answerService.findAllUserAnswers(userId);
        if (theAnswers.size() < 1) {
            return ResponseEntity.status(
                    HttpStatus.NOT_FOUND).body(
                            new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                                    "No challenges found for user with id " + userId));
        }
        return ResponseEntity.ok(theAnswers);
    }

    @GetMapping("/answers/{answerId}/{userId}")
    public ResponseEntity<?> getAnswerByUser(@PathVariable int answerId, @PathVariable int userId) {
        Answer theAnswer = answerService.findByChallengeAndUserId(answerId, userId);
        if (theAnswer == null) {
            return ResponseEntity.status(
                    HttpStatus.NOT_FOUND).body(
                            new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                                    "No challenges found with answer id " + answerId + " and user id " + userId));
        }
        return ResponseEntity.ok(theAnswer);
    }

    @PostMapping("/answers/start")
    public ResponseEntity<Answer> startAnswer(@RequestBody Answer theAnswer) {
        theAnswer.setId(0);
        theAnswer.setStart_date(new Date());
        theAnswer.setEndDate(null);
        theAnswer.setTries(0);
        theAnswer.setId_status(1);

        answerService.save(theAnswer);
        return new ResponseEntity<>(theAnswer, HttpStatus.CREATED);
    }

    @PutMapping("/answers/draft/{userId}/{challengeId}")
    public ResponseEntity<?> saveDraft(@RequestBody Answer theAnswer, @PathVariable int userId, @PathVariable int challengeId) {
        Answer answer = answerService.findByChallengeAndUserId(challengeId, userId);
        if (answer == null) {
            return ResponseEntity.status(
                    HttpStatus.NOT_FOUND).body(
                            new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                                    "No challenges found with challenge id " + challengeId + " and user id " + userId));
        }
        answer.setAnswer_code(theAnswer.getAnswer_code());
        answer.setSegundos_dedicados(theAnswer.getSegundos_dedicados());

        answerService.save(answer);
        return ResponseEntity.ok(answer);
    }
}
