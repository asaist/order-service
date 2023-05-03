package ru.mcclinics.orderservice.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.mcclinics.orderservice.domain.Track;
import ru.mcclinics.orderservice.service.TrackService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Slf4j(topic = "order-service")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/track")
public class TrackController {

    private final TrackService service;


    @GetMapping("/tracks")
    @ResponseStatus(OK)
    public List<Track> getAllTracks() {
        log.info("/tracks");
        List<Track> tracks = service.findTracks();
        return tracks;
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Track create(@RequestBody Track track){
        log.info("/create [track {}]", track);
        return service.create(track);
    }


}
