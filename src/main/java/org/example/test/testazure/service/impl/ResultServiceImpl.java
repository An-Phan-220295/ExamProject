package org.example.test.testazure.service.impl;

import org.example.test.testazure.repository.QuestionRepository;
import org.example.test.testazure.request.SubmitRequest;
import org.example.test.testazure.service.AnswerService;
import org.example.test.testazure.service.QuestionService;
import org.example.test.testazure.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionService questionService;

    @Override
    public double calculatePoint(List<SubmitRequest> data) {
        double result = 0;
        for (SubmitRequest entry : data) {
            if (questionService.isMultipleChoices(entry.getQuestionId())) {
                for (Integer answer : entry.getAnswerIds()) {
                    if (answerService.getAnswerResult(answer)) {
                        System.out.println(entry.getAnswerIds().size());
                        result = result + (double) 1 / (answerService.findCorrectAnswer(entry.getQuestionId()).size());
                    }
                }
            } else if (answerService.getAnswerResult(entry.getAnswerIds().get(0))) {
                result++;
            }
        }
        return result;
    }

    @Override
    public List<Integer> correctAnswer(List<SubmitRequest> data) {
        List<Integer> resultAnswer = new ArrayList<>();
        for (SubmitRequest entry : data) {
            List<Integer> temp = answerService.findCorrectAnswer(entry.getQuestionId());
            resultAnswer.addAll(temp);
        }
        return resultAnswer;
    }
}
