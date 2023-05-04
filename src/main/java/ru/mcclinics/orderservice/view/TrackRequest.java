package ru.mcclinics.orderservice.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.sql.Date;
import java.time.LocalDate;

public class TrackRequest {

    @Schema(description = "Идентификатор трека", example = "123", implementation = Long.class)
    @JsonProperty("id")
    private Long id;



}
