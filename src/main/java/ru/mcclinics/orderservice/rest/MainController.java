package ru.mcclinics.orderservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.mcclinics.orderservice.domain.Track;
import ru.mcclinics.orderservice.domain.User;
import ru.mcclinics.orderservice.service.TrackService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final TrackService service;

    @Value("${files.upload.baseDir}")
    private String uploadPath;
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
                @RequestParam("file") MultipartFile file,
               Map<String, Object> model) throws IOException {
        Track track = new Track(trackName, annotation, user);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            track.setFileName(resultFileName);
        }
        track.setCreateDate(LocalDateTime.now());
        service.save(track);
        Iterable<Track> tracks = service.findTracks();
        model.put("tracks", tracks);
        return "main";
    }
}
