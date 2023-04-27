package ru.mcclinics.orderservice.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
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
    private String name;
    @Column(name = "track_annotation")
    private String annotation;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;
    @Column(name = "create_date")
    private LocalDate createDate;
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
    private List<Lecture> lectures;
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "track")
    private List<KeyWord> keyWords;

}
