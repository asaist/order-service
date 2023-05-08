package ru.mcclinics.orderservice.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.mcclinics.orderservice.domain.Track;
import ru.mcclinics.orderservice.dto.TrackDto;
import ru.mcclinics.orderservice.service.TrackService;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.OK;

@Slf4j(topic = "order-service")
@RestController
@RequiredArgsConstructor
@RequestMapping("track")
public class TrackController {

    private final TrackService service;


    @GetMapping
    @ResponseStatus(OK)
    public List<TrackDto> getAllTracks() {
        log.info("/tracks");
        List<Track> tracks = service.findTracks();
        List<TrackDto> trackDtos = tracks.stream().map(TrackDto::new).collect(toList());
        return trackDtos;
    }

    @GetMapping("{id}")
    public TrackDto getOne(@PathVariable("id") TrackDto trackDto){
        return trackDto;
    }


//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Track create(@RequestBody Track track){
//        log.info("/create [track {}]", track);
//        return service.create(track);
//    }

    @PostMapping
    public TrackDto create(@RequestBody TrackDto trackDto){
        Track track = new Track();
        track.setId(trackDto.getId());
        track.setTrackName(trackDto.getName());
        track.setCreateDate(LocalDateTime.now());
        service.save(track);
        return new TrackDto(track);
    }

    @PutMapping("{id}")
    public TrackDto update(
            @PathVariable("id") Long id,
            @RequestBody TrackDto messageFromUser
    ){
        TrackDto messageFromDb = new TrackDto();
        messageFromDb.setId(id);
        BeanUtils.copyProperties(messageFromUser, messageFromDb, "id");
        service.save(new Track(messageFromDb));
        return messageFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        Track track = new Track();
        track.setId(id);
        service.delete(track);
    }
}
