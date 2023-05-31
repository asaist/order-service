package ru.mcclinics.orderservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Table(name = "university")
@Entity
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "university_id")
    private Long universityId;
    @Column(name = "university_name")
    private String universityName;

}
