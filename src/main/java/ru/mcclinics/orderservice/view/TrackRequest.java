package ru.mcclinics.orderservice.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.sql.Date;
import java.time.LocalDate;

public class TrackRequest {

    @Schema(description = "Идентификатор трека", example = "123", implementation = Long.class)
    @JsonProperty("id")
    private Long id;
    private String trackName;
    private String annotation;
    @Schema(description = "Идентификатор автора", example = "123", implementation = Long.class)
    @JsonProperty("author_id")
    private Long authorId;
    @Schema(
            description = "Дата создания трека",
            format = "yyyy-MM-dd",
            example = "2000-01-01",
            implementation = Date.class)
    private LocalDate createDate;


}
