package ru.mcclinics.orderservice.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.mcclinics.orderservice.domain.Track;
import ru.mcclinics.orderservice.service.TrackService;

import java.util.List;
import java.util.Map;

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

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message){
        Map<String, String> messageFromDb = getMessage("id");
        messageFromDb.putAll(message);
        messageFromDb.put("id", id);
        return messageFromDb;
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestBody Track track){
        Map<String, String> message = getMessage(id);
        messages.remove(message);
    }

    private Map<String, String> getTrack(String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }


}
