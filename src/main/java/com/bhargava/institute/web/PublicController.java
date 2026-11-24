package com.bhargava.institute.web;

import com.bhargava.institute.dto.Dtos.*;
import com.bhargava.institute.service.CatalogService;
import com.bhargava.institute.service.ContactService;
import com.bhargava.institute.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PublicController {

    private final CatalogService catalog;
    private final QuizService quiz;
    private final ContactService contact;

    public PublicController(CatalogService catalog, QuizService quiz, ContactService contact) {
        this.catalog = catalog;
        this.quiz = quiz;
        this.contact = contact;
    }

    @GetMapping("/banners")
    public List<BannerDto> banners() {
        return catalog.banners();
    }

    @GetMapping("/courses")
    public List<CourseDto> courses() {
        return catalog.courses();
    }

    @GetMapping("/courses/{id}")
    public CourseDetailDto course(@PathVariable Long id) {
        return catalog.course(id);
    }

    @GetMapping("/subjects")
    public List<SubjectDto> subjects() {
        return catalog.subjects();
    }

    @GetMapping("/materials")
    public List<MaterialDto> materials() {
        return catalog.materials();
    }

    @GetMapping("/stats")
    public StatsDto stats() {
        return catalog.stats();
    }

    @GetMapping("/quiz/questions")
    public List<QuizQuestionDto> quizQuestions() {
        return quiz.questions();
    }

    @PostMapping("/quiz/submit")
    public QuizResult submitQuiz(@Valid @RequestBody QuizSubmission submission) {
        return quiz.grade(submission);
    }

    @PostMapping("/contact")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactResponse contact(@Valid @RequestBody ContactRequest request) {
        return contact.submit(request);
    }
}
