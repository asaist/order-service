package ru.mcclinics.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mcclinics.orderservice.domain.Track;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackDto extends EntityDto {
    @Schema(description = "Идентификатор трека", example = "123", implementation = Long.class)
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Наименование трека", example = "Поджелудочная железа", implementation = String.class)
    @JsonProperty("name")
    private String name;

    @Schema(description = "Аннотация трека", example = "Трек о поджелудочной железе", implementation = String.class)
    @JsonProperty("annotation")
    private String annotation;

    @Schema(description = "Руководитель трека", example = "Кувалдин Виктор Петрович", implementation = String.class)
    @JsonProperty("supervisor")
    private String supervisor;

    public TrackDto(Track track) {
        this.id = track.getId();
        this.name = track.getTrackName();
        this.annotation = track.getAnnotation();
        this.supervisor = track.getSupervisor();
    }
}
