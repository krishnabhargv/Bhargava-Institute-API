package com.bhargava.institute.service;

import com.bhargava.institute.domain.QuizOption;
import com.bhargava.institute.domain.QuizQuestion;
import com.bhargava.institute.dto.Dtos.*;
import com.bhargava.institute.repository.QuizQuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class QuizService {

    private final QuizQuestionRepository repository;

    public QuizService(QuizQuestionRepository repository) {
        this.repository = repository;
    }

    public List<QuizQuestionDto> questions() {
        return repository.findAllByOrderByDisplayOrderAsc().stream()
                .map(q -> new QuizQuestionDto(q.getId(), q.getText(),
                        q.getOptions().stream().map(QuizOption::getText).toList()))
                .toList();
    }

    /** Scores against every question; unanswered questions count as wrong. */
    public QuizResult grade(QuizSubmission submission) {
        Map<Long, String> given = new HashMap<>();
        submission.answers().forEach(a -> given.put(a.questionId(), a.answer()));

        List<QuizQuestion> all = repository.findAllByOrderByDisplayOrderAsc();
        Map<Long, String> correct = new LinkedHashMap<>();
        int score = 0;
        for (QuizQuestion q : all) {
            correct.put(q.getId(), q.getCorrectAnswer());
            if (q.getCorrectAnswer().equals(given.get(q.getId()))) {
                score++;
            }
        }
        return new QuizResult(score, all.size(), correct);
    }
}
