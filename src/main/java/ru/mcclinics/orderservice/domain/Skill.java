package ru.mcclinics.orderservice.domain;


import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;
@Getter
@Setter
@Table(name = "skill")
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "skill")
    private List<KeyWord> keyWords;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "competence_id")
    private Competence competence;
}
