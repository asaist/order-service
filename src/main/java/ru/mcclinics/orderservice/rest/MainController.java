package ru.mcclinics.orderservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mcclinics.orderservice.domain.Author;
import ru.mcclinics.orderservice.domain.Track;
import ru.mcclinics.orderservice.service.TrackService;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final TrackService service;
    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Track> tracks = service.findTracks();
        model.put("tracks", tracks);
        return "main";
    }
    @PostMapping("/main")
    public String add(@RequestParam String trackName,
               @RequestParam String annotation,
//               @RequestParam String author,
               Map<String, Object> model){
        Track track = new Track(trackName, annotation);
        track.setCreateDate(LocalDateTime.now());
//        Author author1 = new Author(author);
//        track.setAuthor(author1);
        service.save(track);
        Iterable<Track> tracks = service.findTracks();
        model.put("tracks", tracks);
        return "main";
    }
}
