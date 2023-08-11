package ru.mcclinics.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.mcclinics.orderservice.domain.Lecture;
import ru.mcclinics.orderservice.domain.Series;

import java.io.Serializable;
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ModuleDto extends EntityDto implements Serializable {
    @JsonProperty("id")
    private Long id;
    private String moduleNameModal;
    private String moduleModalAnnotation;
    private String  moduleModalKeyWords;

    public ModuleDto(Series series) {
        this.id = series.getId();
        this.moduleNameModal = series.getSeriesName();
        this.moduleModalAnnotation = series.getAnnotation();
    }
}
