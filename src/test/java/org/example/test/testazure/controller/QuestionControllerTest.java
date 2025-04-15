package org.example.test.testazure.controller;

import jakarta.servlet.http.HttpSession;
import org.example.test.testazure.exceptionhandler.exception.NoDataException;
import org.example.test.testazure.request.SubmitRequest;
import org.example.test.testazure.response.AnswerResponse;
import org.example.test.testazure.response.QuestionResponse;
import org.example.test.testazure.response.ResultQuiz;
import org.example.test.testazure.service.QuestionService;
import org.example.test.testazure.service.ResultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestionControllerTest {
    @Mock
    private QuestionService questionService;
    @Mock
    private ResultService resultService;
    @Mock
    private HttpSession session;
    @InjectMocks
    private QuestionController questionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Khởi tạo mock objects
    }

    @Test
    void getQuestion_Session_HasQuestionsAttribute() {
        //Tạo dữ liệu để mock
        List<QuestionResponse> questionResponses = Arrays.asList(
                new QuestionResponse(1, "Question A", Arrays.asList(
                        new AnswerResponse(1, "Answer A")
                        , new AnswerResponse(2, "Answer B")), true),
                new QuestionResponse(2, "Question B", Arrays.asList(
                        new AnswerResponse(1, "Answer A")
                        , new AnswerResponse(2, "Answer B")), true));
        when(session.getAttribute("quizQuestions")).thenReturn(questionResponses);

        ResponseEntity<?> response = questionController.getQuestion(session);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(questionResponses, response.getBody());

        verify(questionService, never()).getQuestion();
    }

    @Test
    void getQuestion_Session_NoQuestionsAttribute() {
        when(session.getAttribute("quizQuestions")).thenReturn(null);

        List<QuestionResponse> questionResponses = Arrays.asList(
                new QuestionResponse(1, "Question A", Arrays.asList(
                        new AnswerResponse(1, "Answer A")
                        , new AnswerResponse(2, "Answer B")), true),
                new QuestionResponse(2, "Question B", Arrays.asList(
                        new AnswerResponse(1, "Answer A")
                        , new AnswerResponse(2, "Answer B")), true));
        when(questionService.getQuestion()).thenReturn(questionResponses);

        ResponseEntity<?> response = questionController.getQuestion(session);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(questionResponses, response.getBody());

        verify(questionService, times(1)).getQuestion();
    }

    @Test
    void submitQuiz_Success() {
        List<SubmitRequest> submitRequests = Arrays.asList(
                new SubmitRequest(1, Arrays.asList(1, 2)),
                new SubmitRequest(2, Arrays.asList(3, 4)));
        when(resultService.calculatePoint(submitRequests)).thenReturn(3.0);
        when(resultService.correctAnswer(submitRequests)).thenReturn(Arrays.asList(1, 3));
        ResultQuiz resultQuiz = new ResultQuiz(3.0, Arrays.asList(1, 3));

        ResponseEntity<?> response = questionController.submitQuiz(submitRequests);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(resultQuiz, response.getBody());

        verify(resultService, times(1)).calculatePoint(submitRequests);
        verify(resultService, times(1)).correctAnswer(submitRequests);
    }

    @Test
    void submitQuiz_NoData() {
        Exception exception = assertThrows(NoDataException.class, () -> {
            questionController.submitQuiz(null);
        });
        assertEquals(NoDataException.class, exception.getClass());
    }


}