package ru.mcclinics.orderservice.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import ru.mcclinics.orderservice.dto.TrackDto;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Table(name = "track")
@Entity
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_id")
    private Long id;
    @Column(name = "track_name")
    private String trackName;
    @Column(name = "track_annotation")
    private String annotation;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "university_id")
    private University university;
    private String fileName;
    @Column(name = "create_date", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    @Column(name = "dep_lead_date")
    private LocalDate DepartmentLeadDate;
    @Column(name = "expert_group_date")
    private LocalDate ExpertGroupDate;
    @Column(name = "dep_lead_status")
    @Enumerated
    private TrackStatus DepartmentLeadStatus;
    @Column(name = "expert_group_status")
    @Enumerated
    private TrackStatus ExpertGroupStatus;
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "track")
    @JsonManagedReference(value="track-lecture")
    private List<Lecture> lectures;
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "track")
    @JsonManagedReference(value="track-series")
    private List<Series> series;
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "track")
    private List<KeyWord> keyWords;

    public Track(TrackDto track) {

        this.trackName = track.getName();
    }
    public Track() {

    }
    public Track(String trackName, String annotation, User user) {
        this.trackName = trackName;
        this.annotation = annotation;
        this.author = user;
    }
    public Track(String trackName, String annotation, User user, University university) {
        this.trackName = trackName;
        this.annotation = annotation;
        this.author = user;
        this.university = university;
    }
    public String getAuthorName(){
        return author!=null ? author.getUsername() : "<none>";
    }
    public String getAnnotation(){
        return annotation!=null ? annotation : "<none>";
    }
    public String getUniversityName(){
        return university!=null ? university.getUniversityName() : "<none>";
    }

}
