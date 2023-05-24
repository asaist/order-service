package ru.mcclinics.orderservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mcclinics.orderservice.domain.Track;
import ru.mcclinics.orderservice.domain.User;
import ru.mcclinics.orderservice.service.TrackService;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final TrackService service;
    @GetMapping("/")
    public String greeting(Map<String, Object> model){
        return "greeting";
    }
    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Track> tracks = service.findTracks();

        if (filter !=null && !filter.isEmpty()){
            tracks = service.findTrackByName(filter);
        } else {
            tracks = service.findTracks();
        }

        model.addAttribute("tracks", tracks);
        model.addAttribute("filter", filter);
        return "main";
    }
    @PostMapping("/main")
    public String add(
                @AuthenticationPrincipal User user,
                @RequestParam String trackName,
                @RequestParam String annotation,
               Map<String, Object> model){
        Track track = new Track(trackName, annotation, user);
        track.setCreateDate(LocalDateTime.now());
        service.save(track);
        Iterable<Track> tracks = service.findTracks();
        model.put("tracks", tracks);
        return "main";
    }
}
