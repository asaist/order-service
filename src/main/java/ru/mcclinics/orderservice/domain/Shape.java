package ru.mcclinics.orderservice.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Table(name = "shape")
@Entity
public class Shape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shape_id")
    private Long id;

    @Column(name = "shape_name")
    private String shapeName;
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "shape")
    @JsonManagedReference(value="shape-series")
    private List<Series> series;

}
