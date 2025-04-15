package org.example.test.testazure.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(columnDefinition = "TEXT")
    String question;

    @OneToMany(mappedBy = "questions")
    List<Answers> answers;

    @Column(name ="multiple_choices")
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

    public List<Answers> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answers> answers) {
        this.answers = answers;
    }

    public boolean isMultipleChoices() {
        return isMultipleChoices;
    }

    public void setMultipleChoices(boolean multipleChoices) {
        isMultipleChoices = multipleChoices;
    }
}
