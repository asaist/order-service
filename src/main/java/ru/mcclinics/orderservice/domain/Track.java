package ru.mcclinics.orderservice.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import ru.mcclinics.orderservice.dto.TrackDto;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
//    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "track")
//    @JsonManagedReference(value="track-lecture")
//    private List<Lecture> lectures;
//    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "track")
//    @JsonManagedReference(value="track-series")
//    private List<Series> series;

    public Track(TrackDto track) {
        this.id = track.getId();
        this.trackName = track.getName();
    }
    public Track() {

    }
    public Track(String trackName, String annotation, User user) {
        this.trackName = trackName;
        this.annotation = annotation;
        this.author = user;
    }
    public String getAuthorName(){
        return author!=null ? author.getUsername() : "<none>";
    }

}
