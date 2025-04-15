package org.example.test.testazure.service;

import org.example.test.testazure.response.QuestionResponse;

import java.util.List;

public interface QuestionService {
    List<QuestionResponse> getQuestion();
    boolean isMultipleChoices(int questionId);
}
