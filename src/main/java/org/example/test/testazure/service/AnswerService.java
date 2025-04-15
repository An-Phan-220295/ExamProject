package org.example.test.testazure.service;

import java.util.List;

public interface AnswerService {
    boolean getAnswerResult(int answerId);
    List<Integer> findCorrectAnswer(int questionId);
}
