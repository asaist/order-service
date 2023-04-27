package ru.mcclinics.orderservice.rest;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.mcclinics.orderservice.domain.Track;
import ru.mcclinics.orderservice.service.TrackService;
import ru.mcclinics.orderservice.swagger.SwaggerConfiguration;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Slf4j(topic = "order-service")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/track")
public class TrackController {

    @Autowired
    private final TrackService trackService;

    @GetMapping("/tracks")
    @ResponseStatus(OK)
    @Operation(
            summary = "Получение всех треков",
            tags = {"track", SwaggerConfiguration.UNSECURED})
    public List<Track> getAllTracks() {
        log.info("/tracks");
        return trackService.findTracks();
    }

}
