package com.bhargava.institute.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "study_material")
@Getter @Setter @NoArgsConstructor
public class StudyMaterial {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String icon;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "display_order", nullable = false)
    private int displayOrder;
}
