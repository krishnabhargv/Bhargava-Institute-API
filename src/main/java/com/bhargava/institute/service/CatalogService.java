package com.bhargava.institute.service;

import com.bhargava.institute.domain.Course;
import com.bhargava.institute.dto.Dtos.*;
import com.bhargava.institute.repository.*;
import com.bhargava.institute.web.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** Read-only content shown on the public site. */
@Service
@Transactional(readOnly = true)
public class CatalogService {

    private final BannerRepository banners;
    private final CourseRepository courses;
    private final SubjectRepository subjects;
    private final StudyMaterialRepository materials;
    private final QuizQuestionRepository questions;

    public CatalogService(BannerRepository banners, CourseRepository courses, SubjectRepository subjects,
                          StudyMaterialRepository materials, QuizQuestionRepository questions) {
        this.banners = banners;
        this.courses = courses;
        this.subjects = subjects;
        this.materials = materials;
        this.questions = questions;
    }

    public List<BannerDto> banners() {
        return banners.findByActiveTrueOrderByDisplayOrderAsc().stream()
                .map(b -> new BannerDto(b.getId(), b.getImageUrl(), b.getTitle(), b.getText(), b.getLinkTo(), b.getCta()))
                .toList();
    }

    public List<CourseDto> courses() {
        return courses.findAllByOrderByDisplayOrderAsc().stream()
                .map(c -> new CourseDto(c.getId(), c.getTitle(), c.getIcon(), c.getDescription(), c.getLevel()))
                .toList();
    }

    public CourseDetailDto course(Long id) {
        Course c = courses.findByIdWithLessons(id)
                .orElseThrow(() -> new NotFoundException("Course " + id + " not found"));
        List<LessonDto> lessons = c.getLessons().stream()
                .map(l -> new LessonDto(l.getId(), l.getTitle(), l.getDescription(), l.getPosition()))
                .toList();
        return new CourseDetailDto(c.getId(), c.getTitle(), c.getIcon(), c.getDescription(), c.getLevel(), lessons);
    }

    public List<SubjectDto> subjects() {
        return subjects.findAllByOrderByDisplayOrderAsc().stream()
                .map(s -> new SubjectDto(s.getId(), s.getName(), s.getIcon(), s.getDescription()))
                .toList();
    }

    public List<MaterialDto> materials() {
        return materials.findAllByOrderByDisplayOrderAsc().stream()
                .map(m -> new MaterialDto(m.getId(), m.getTitle(), m.getType(), m.getIcon(), m.getFileUrl()))
                .toList();
    }

    public StatsDto stats() {
        return new StatsDto(courses.count(), questions.count());
    }
}
