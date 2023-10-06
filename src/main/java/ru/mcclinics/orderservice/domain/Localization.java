package ru.mcclinics.orderservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.mcclinics.orderservice.dto.EntityDto;
import ru.mcclinics.orderservice.dto.MkbDto;

import java.io.Serializable;
@Setter
@Getter
@Table(name = "localization")
@Entity
public class Localization extends EntityDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "value")
    private String value;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id")
    private Track track;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id")
    private Series series;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    public Localization(MkbDto mkbDto) {
        if (mkbDto.getId() != null){
            this.id = mkbDto.getId();
        }
        this.value = mkbDto.getValue();
    }

    public Localization() {

    }
}
