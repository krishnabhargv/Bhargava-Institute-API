package com.bhargava.institute.repository;

import com.bhargava.institute.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByOrderByDisplayOrderAsc();

    @Query("select c from Course c left join fetch c.lessons where c.id = :id")
    Optional<Course> findByIdWithLessons(@Param("id") Long id);
}
