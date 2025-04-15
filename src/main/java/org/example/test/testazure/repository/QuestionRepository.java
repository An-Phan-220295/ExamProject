package org.example.test.testazure.repository;

import org.example.test.testazure.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Questions, Integer> {
    @Query(value = "SELECT * FROM (SELECT * FROM questions ORDER BY random() LIMIT 10) AS q ORDER BY id ASC", nativeQuery = true)
    List<Questions> getRandom100Question();

//    @Query(value = "SELECT * FROM (SELECT * FROM questions q where q.multiple_choices = true LIMIT 1) AS q ORDER BY id ASC", nativeQuery = true)
//    List<Questions> getRandom100Question();

    @Query("SELECT q.isMultipleChoices FROM Questions q WHERE q.id = :questionId")
    boolean checkMultipleChoicesByQuestionId(@Param("questionId") int questionId);

}
