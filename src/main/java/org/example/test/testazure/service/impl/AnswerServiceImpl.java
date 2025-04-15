package org.example.test.testazure.service.impl;

import org.example.test.testazure.entity.Answers;
import org.example.test.testazure.repository.AnswerRepository;
import org.example.test.testazure.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public boolean getAnswerResult(int id) {
        Answers result = answerRepository.findById(id).orElse(null);
        return result != null ? result.getResult() : false;
    }

    @Override
    public List<Integer> findCorrectAnswer(int questionId) {
        return answerRepository.findCorrectAnswerByQuestionId(questionId);
    }
}
