package org.example.test.testazure.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionResponse {
    int id;
    String question;
    List<AnswerResponse> answerReponseList;
    boolean isMultipleChoices;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<AnswerResponse> getAnswerReponseList() {
        return answerReponseList;
    }

    public void setAnswerReponseList(List<AnswerResponse> answerReponseList) {
        this.answerReponseList = answerReponseList;
    }

    public boolean isMultipleChoices() {
        return isMultipleChoices;
    }

    public void setMultipleChoices(boolean multipleChoices) {
        isMultipleChoices = multipleChoices;
    }
}
