package ru.mcclinics.orderservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.mcclinics.orderservice.dto.AuthorDto;
import ru.mcclinics.orderservice.dto.LectureDto;
import ru.mcclinics.orderservice.service.AuthorService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "lecture_author",
            joinColumns = {@JoinColumn(name = "lecture_id", referencedColumnName = "lecture_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "author_id")})
    private Set<Author> authors = new HashSet<>();
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "supervisor")
    private Author supervisor;
    @Column(name = "video_reference")
    private String videoReference;
    @Column(name = "lecture_annotation", length = 1000)
    private String annotation;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "days_to_fill")
    private String daysToFill;
    @Column(name = "learn_competence_one")
    private String learnCompetenceOne;
    @Column(name = "learn_competence_two")
    private String learnCompetenceTwo;
    @Column(name = "learn_competence_three")
    private String learnCompetenceThree;
    @Column(name = "learn_competence_four")
    private String learnCompetenceFour;
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
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "shape_id")
    @JsonBackReference(value="shape-lectures")
    private Shape shape;
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "lecture")
    private List<KeyWord> keyWords;
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "lecture")
    private List<Mkb> mkbs;
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "lecture")
    private List<Discipline> disciplines;
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "lecture")
    private List<Localization> localizations;
    @Transient
    private Long frontEndModule;
    @Transient
    private Long frontEndLecture;

//    @CollectionTable(name = "lecture_status", joinColumns = @JoinColumn(name = "lecture_id"))
//    @Enumerated(EnumType.STRING)
//    @Column(name = "status")
//    @NotNull
//    @Convert(converter = TrackStatusConverter.class)
//    private TrackStatus status;

//    public String getAuthorName(){
//        return author!=null ? author.getFirstName() : "<none>";
//    }
    public String getAnnotation(){
        return annotation!=null ? annotation : "<none>";
    }
    public String getDaysToFill() { return daysToFill!=null ? daysToFill : ""; }
    public String getVideoReference(){
        return videoReference!=null ? videoReference : "<none>";
    }
    public String getTrackName() {
        return track!=null ? track.getTrackName() : "<Не относится к треку>";
    }
    public String getSeriesName() {
        return series!=null ? series.getSeriesName() : "<Не относится к серии>";
    }
    public String getShapeName() {
        return shape!=null ? shape.getShapeName() : "<Не относится к шаблону>";
    }
    public String getKeyWords(){
        return keyWords!=null ? keyWords.stream()
                .map(KeyWord::getValue)
                .collect(Collectors.joining(";")) : "<none>";
    }


    public Series getSeries(){return series!=null ? series : null; }

    public Lecture() {
    }

    public Lecture(String lectureName, String annotation, List<KeyWord> keyWords, String videoReference, Track track) {
        this.lectureName = lectureName;
//        this.author = author;
        this.track = track;
        this.annotation = annotation;
        this.videoReference = videoReference;
        this.keyWords = keyWords;

    }
    public Lecture(String lectureName, Author author, String annotation, Track track, Series series) {
        this.lectureName = lectureName;
//        this.author = author;
        this.track = track;
        this.annotation = annotation;
        this.series = series;
    }
    public Lecture(LectureDto lectureDto) {

        if (lectureDto.getModuleId() != null){
            this.id = lectureDto.getId();
        }

        System.out.println("IDDB: " + lectureDto.getIdDb());
        System.out.println("keyWords: " + lectureDto.getLectureModuleKeyWords());

        if (lectureDto.getIdDb()){
            this.id = lectureDto.getId();
        }

        this.lectureName= lectureDto.getLectureModuleName();
        this.annotation = lectureDto.getLectureModuleAnnotation();
        if (lectureDto.getLectureModuleKeyWords() != null){
            String[] strMain = lectureDto.getLectureModuleKeyWords().split(";");
            List<KeyWord> keyWords = new ArrayList<>(strMain.length);
            for (int i = 0; i < strMain.length; i++){
                KeyWord keyWord = new KeyWord();
                keyWord.setValue(strMain[i]);
                keyWords.add(keyWord);
            }
            this.keyWords = keyWords;
        }

        System.out.println(lectureDto.getModuleId());
        if (lectureDto.getModuleId() != null) {
            this.series = new Series(Long.valueOf(lectureDto.getModuleId()));
        }


        if (lectureDto.getDaysToFill() != null){
            this.daysToFill = lectureDto.getDaysToFill();
        }
        String statusAsString = lectureDto.getStatus(); // Assuming lectureDto.getStatus() returns a String value
        LectureStatus trackStatus = LectureStatus.valueOf(statusAsString);
        this.lectureStatus = trackStatus;

        if (lectureDto.getLearnCompetenceOne() != null){
            this.learnCompetenceOne = lectureDto.getLearnCompetenceOne();
        }
        if (lectureDto.getLearnCompetenceTwo() != null){
            this.learnCompetenceTwo = lectureDto.getLearnCompetenceTwo();
        }
        if (lectureDto.getLearnCompetenceThree() != null){
            this.learnCompetenceThree = lectureDto.getLearnCompetenceThree();
        }
        if (lectureDto.getLearnCompetenceFour() != null){
            this.learnCompetenceFour = lectureDto.getLearnCompetenceFour();
        }
        this.shape = new Shape(1L);
        this.createDate = LocalDateTime.now();
    }
    public String getSupervisor(){
        return supervisor!=null ? supervisor.getLastName() + " " + supervisor.getFirstName() + " " + supervisor.getMiddleName() : "<none>";
    }
}
