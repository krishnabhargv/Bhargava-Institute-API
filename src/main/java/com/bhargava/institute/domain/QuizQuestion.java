package com.bhargava.institute.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz_question")
@Getter @Setter @NoArgsConstructor
public class QuizQuestion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String text;

    @Column(name = "correct_answer", nullable = false)
    private String correctAnswer;

    @Column(name = "display_order", nullable = false)
    private int displayOrder;

    @OneToMany(mappedBy = "question")
    @OrderBy("position ASC")
    private List<QuizOption> options = new ArrayList<>();
}
