package org.example.test.testazure.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.test.testazure.exceptionhandler.exception.NoDataException;
import org.example.test.testazure.request.SubmitRequest;
import org.example.test.testazure.response.QuestionResponse;
import org.example.test.testazure.response.ResultQuiz;
import org.example.test.testazure.service.QuestionService;
import org.example.test.testazure.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ResultService resultService;

    @GetMapping("/questions")
    public ResponseEntity<List<QuestionResponse>> getQuestion(HttpSession session) {
        // Lấy danh sách câu hỏi từ session
        List<QuestionResponse> quizQuestions = (List<QuestionResponse>) session.getAttribute("quizQuestions");

        if (quizQuestions == null) {
            quizQuestions = questionService.getQuestion();
            session.setAttribute("quizQuestions", quizQuestions);
        }

        return ResponseEntity.ok(quizQuestions);
    }

    @PostMapping("/submit-quiz")
    public ResponseEntity<?> submitQuiz(@RequestBody @Valid List<SubmitRequest> data) {
        if(data == null || data.isEmpty()){
            throw new NoDataException();
        }
        ResultQuiz resultQuiz = new ResultQuiz(resultService.calculatePoint(data)
                , resultService.correctAnswer(data));
        return new ResponseEntity<>(resultQuiz, HttpStatus.OK);
    }
}
