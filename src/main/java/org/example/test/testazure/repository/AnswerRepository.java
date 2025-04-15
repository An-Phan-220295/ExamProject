package org.example.test.testazure.repository;

import org.example.test.testazure.entity.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answers, Integer> {
    @Query("SELECT a.id FROM Questions q JOIN q.answers a WHERE q.id = :id AND a.result = true")
    List<Integer> findCorrectAnswerByQuestionId(@Param("id") int id);
}
