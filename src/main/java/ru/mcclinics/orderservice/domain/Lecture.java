package ru.mcclinics.orderservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private String lectureName;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;
    @Column(name = "video_reference")
    private String videoReference;
    @Column(name = "lecture_annotation")
    private String annotation;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "lecture_status")
    @Enumerated
    private LectureStatus lectureStatus;
    @Column(name = "content")
    private String content;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id")
    @JsonBackReference(value="track-lecture")
    private Track track;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "series_id")
    @JsonBackReference(value="series-lecture")
    private Series series;

    public String getAuthorName(){
        return author!=null ? author.getUsername() : "<none>";
    }
    public String getAnnotation(){
        return annotation!=null ? annotation : "<none>";
    }
    public String getVideoReference(){
        return videoReference!=null ? videoReference : "<none>";
    }

    public Lecture() {
    }

    public Lecture(String lectureName, User author,String annotation, String videoReference, Track track) {
        this.lectureName = lectureName;
        this.author = author;
        this.track = track;
        this.annotation = annotation;
        this.videoReference = videoReference;
    }
}
