package ru.mcclinics.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mcclinics.orderservice.domain.Track;

import java.sql.Date;
import java.time.LocalDateTime;

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

    public TrackDto(Track track) {
        this.id = track.getId();
        this.name = track.getTrackName();
    }
}
