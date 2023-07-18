package ru.mcclinics.orderservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.mcclinics.orderservice.dto.LectureDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private Author author;
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
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "lecture")
    private List<KeyWord> keyWords;
    @Transient
    private Long frontEndModule;

    public String getAuthorName(){
        return author!=null ? author.getFirstName() : "<none>";
    }
    public String getAnnotation(){
        return annotation!=null ? annotation : "<none>";
    }
    public String getVideoReference(){
        return videoReference!=null ? videoReference : "<none>";
    }

    public Lecture() {
    }

    public Lecture(String lectureName, Author author,String annotation, String videoReference, Track track) {
        this.lectureName = lectureName;
        this.author = author;
        this.track = track;
        this.annotation = annotation;
        this.videoReference = videoReference;
    }
    public Lecture(String lectureName, Author author, String annotation, Track track, Series series) {
        this.lectureName = lectureName;
        this.author = author;
        this.track = track;
        this.annotation = annotation;
        this.series = series;
    }
    public Lecture(LectureDto lectureDto) {
        this.lectureName= lectureDto.getLectureModuleName();
        this.annotation = lectureDto.getLectureModuleAnnotation();

        String[] strMain = lectureDto.getLectureModuleKeyWords().split(";");
        List<KeyWord> keyWords = new ArrayList<>(strMain.length);
        for (int i = 0; i < strMain.length; i++){
            KeyWord keyWord = new KeyWord();
            keyWord.setValue(strMain[i]);
            keyWords.add(keyWord);
        }
        this.keyWords = keyWords;
        System.out.println(lectureDto.getModuleId());
        if (lectureDto.getModuleId() != null) {
            this.frontEndModule = Long.valueOf(lectureDto.getModuleId());
        }
    }
}
