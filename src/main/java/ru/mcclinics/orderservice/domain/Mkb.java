package ru.mcclinics.orderservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.mcclinics.orderservice.dto.EntityDto;
import ru.mcclinics.orderservice.dto.MkbDto;

import java.io.Serializable;

@Setter
@Getter
@Table(name = "mkb_used")
@Entity
public class Mkb extends EntityDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
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

    public Mkb(MkbDto mkbDto) {
        if (mkbDto.getId() != null){
            this.id = mkbDto.getId();
        }
        this.value = mkbDto.getValue();
    }

    public Mkb() {

    }
}
