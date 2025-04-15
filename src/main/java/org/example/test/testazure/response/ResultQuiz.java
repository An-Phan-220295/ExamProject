package org.example.test.testazure.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResultQuiz {
    double point;
    List<Integer> correctAnswer;

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public List<Integer> getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(List<Integer> correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
