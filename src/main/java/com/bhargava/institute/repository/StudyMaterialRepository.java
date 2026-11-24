package com.bhargava.institute.repository;

import com.bhargava.institute.domain.StudyMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyMaterialRepository extends JpaRepository<StudyMaterial, Long> {
    List<StudyMaterial> findAllByOrderByDisplayOrderAsc();
}
