package org.example.test.testazure.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubmitRequest {
    @Min(value = 1, message = "Min value of questionId is 1")
    int questionId;
    @NotNull(message = "Answer ID cannot be null")
    @NotEmpty(message = "AnswerIds cannot be empty")
    List<@Min(value = 1, message = "Min value of answerId is 1") Integer> answerIds;

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setAnswerIds(List<Integer> answerIds) {
        this.answerIds = answerIds;
    }

    @Override
    public String toString() {
        return "SubmitRequest{" +
                "questionId=" + questionId +
                ", answerIds=" + answerIds +
                '}';
    }
}
