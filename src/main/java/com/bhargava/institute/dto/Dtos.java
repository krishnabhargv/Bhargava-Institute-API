package com.bhargava.institute.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/** All request/response shapes of the public API. */
public final class Dtos {
    private Dtos() {}

    public record BannerDto(Long id, String imageUrl, String title, String text, String linkTo, String cta) {}

    public record CourseDto(Long id, String title, String icon, String description, String level) {}

    public record LessonDto(Long id, String title, String description, int position) {}

    public record CourseDetailDto(Long id, String title, String icon, String description, String level,
                                  List<LessonDto> lessons) {}

    public record SubjectDto(Long id, String name, String icon, String description) {}

    public record MaterialDto(Long id, String title, String type, String icon, String fileUrl) {}

    public record StatsDto(long courses, long questions) {}

    /** Question as shown to a student: no correct answer included. */
    public record QuizQuestionDto(Long id, String text, List<String> options) {}

    public record QuizAnswer(@NotNull Long questionId, String answer) {}

    public record QuizSubmission(@NotEmpty @Valid List<QuizAnswer> answers) {}

    public record QuizResult(int score, int total, Map<Long, String> correctAnswers) {}

    public record ContactRequest(
            @NotBlank @Size(max = 100) String name,
            @NotBlank @Email @Size(max = 150) String email,
            @NotBlank @Size(max = 2000) String message) {}

    public record ContactResponse(Long id, Instant createdAt) {}

    public record ErrorResponse(Instant timestamp, int status, String error, String message,
                                Map<String, String> fieldErrors) {}
}
