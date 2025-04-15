package org.example.test.testazure.service.impl;

import org.example.test.testazure.entity.Questions;
import org.example.test.testazure.repository.QuestionRepository;
import org.example.test.testazure.response.AnswerResponse;
import org.example.test.testazure.response.QuestionResponse;
import org.example.test.testazure.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<QuestionResponse> getQuestion() {
        List<Questions> questions = questionRepository.getRandom100Question();
        return questions.stream().map(this::convertToQuestionResponse).collect(Collectors.toList());
    }

    @Override
    public boolean isMultipleChoices(int questionId) {
        return questionRepository.checkMultipleChoicesByQuestionId(questionId);
    }

    private QuestionResponse convertToQuestionResponse(Questions question) {
        QuestionResponse response = modelMapper.map(question, QuestionResponse.class);

        // Chuyển đổi danh sách Answers thành AnswerResponse
        List<AnswerResponse> answerResponses = question.getAnswers().stream()
                .map(answer -> modelMapper.map(answer, AnswerResponse.class))
                .collect(Collectors.toList());

        response.setAnswerReponseList(answerResponses);
        return response;
    }
}
