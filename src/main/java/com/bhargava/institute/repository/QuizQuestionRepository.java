package com.bhargava.institute.repository;

import com.bhargava.institute.domain.QuizQuestion;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long> {
    @EntityGraph(attributePaths = "options")
    List<QuizQuestion> findAllByOrderByDisplayOrderAsc();
}
