package ru.mcclinics.orderservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@Table(name = "lecture")
@Entity

public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long id;
    @Column(name = "lacture_name")
    private String lecture_name;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;
    @Column(name = "create_date")
    private LocalDate createDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "lecture_status")
    @Enumerated
    private LectureStatus lectureStatus;
    @Column(name = "content")
    private String content;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id")
    @JsonBackReference
    private Track track;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "series_id")
    @JsonBackReference
    private Series series;
}
