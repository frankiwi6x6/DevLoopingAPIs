package main.java.com.devlooping.answersAPI.rest;

import main.java.com.devlooping.answersAPI.entity.Answer;
import main.java.com.devlooping.answersAPI.entity.ErrorResponse;
import main.java.com.devlooping.answersAPI.exception.AnswersNotFoundException;
import main.java.com.devlooping.answersAPI.service.AnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnswersRestController {

    private ErrorResponse errorResponse;
    private AnswersService answersService;

    @Autowired
    public AnswersRestController(AnswersService answersService) {
        this.answersService = answersService;
    }

    @GetMapping("/answers")
    public List<Answer> findAll() {
        return answersService.findAll();
    }

    @GetMapping("/answers/{answersId}")
    public Answer getAnswers(@PathVariable int answersId) {
        Answer theAnswers = answersService.findById(answersId);

        if (theAnswers == null) {
            throw new AnswersNotFoundException("Answer id not found - " + answersId);
        }

        return theAnswers;
    }

    @PostMapping("/answers")
    public ResponseEntity<Answer> addAnswers(@RequestBody Answer theAnswers) {
        answersService.save(theAnswers);
        return new ResponseEntity<>(theAnswers, HttpStatus.CREATED);
    }

    @PutMapping("/answers/{answersId}")
    public ResponseEntity<Answer> updateAnswers(@PathVariable int answersId, @RequestBody Answer theAnswers) {
        Answer existingAnswers = answersService.findById(answersId);

        if (existingAnswers == null) {
            throw new AnswersNotFoundException("Answer id not found - " + answersId);
        }

        theAnswers.setId(answersId);
        answersService.save(theAnswers);

        return new ResponseEntity<>(theAnswers, HttpStatus.OK);
    }

    @DeleteMapping("/answers/{answersId}")
    public String deleteAnswers(@PathVariable int answersId) {

        Answer tempAnswers = answersService.findById(answersId);

        if (tempAnswers == null) {
            throw new AnswersNotFoundException("Answer id not found - " + answersId);
        }

        answersService.deleteById(answersId);

        return "Deleted answers id - " + answersId;
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleAnswersNotFoundException(AnswersNotFoundException exc) {

        errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exc) {

        errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
