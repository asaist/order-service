package ru.mcclinics.orderservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mcclinics.orderservice.dao.KeyWordRepository;
import ru.mcclinics.orderservice.domain.KeyWord;
import ru.mcclinics.orderservice.domain.Track;
import ru.mcclinics.orderservice.domain.University;
import ru.mcclinics.orderservice.domain.User;
import ru.mcclinics.orderservice.service.TrackService;
import ru.mcclinics.orderservice.service.UniversityService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final TrackService trackService;
    private final UniversityService universityService;
    private final KeyWordRepository keyWordRepository;

    @Value("${files.upload.baseDir}")
    private String uploadPath;
//    @GetMapping("/")
//    public String greeting(Map<String, Object> model){
//        return "scheme";
//    }
    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Track> tracks = trackService.findTracks();

        if (filter !=null && !filter.isEmpty()){
            tracks = trackService.findTrackByName(filter);
        } else {
            tracks = trackService.findTracks();
        }

        model.addAttribute("tracks", tracks);
        model.addAttribute("filter", filter);
        return "main";
    }

    @GetMapping("/table_track")
    public String listTrackForTable(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Track> tracks = trackService.findTracks();
        if (filter !=null && !filter.isEmpty()){
            tracks = trackService.findTrackByName(filter);
        } else {
            tracks = trackService.findTracks();
        }

        model.addAttribute("tracks", tracks);
        model.addAttribute("filter", filter);
        model.addAttribute("universities", universityService.getUniversityList());
        return "table_track";
    }

    @GetMapping("/table_track/{id}")
    public String getOne(@PathVariable("id") Track track, Model model){
        model.addAttribute("track", track);
        return "track_up";
    }

    @PostMapping("/main")
    public String add(
                @AuthenticationPrincipal User user,
                @RequestParam String trackName,
                @RequestParam String annotation,
//                @RequestParam("file") MultipartFile file,
               Map<String, Object> model) throws IOException {
        Track track = new Track(trackName, annotation, user);
//        if (file != null && !file.getOriginalFilename().isEmpty()) {
//            File uploadDir = new File(uploadPath);
//            if(!uploadDir.exists()) {
//                uploadDir.mkdirs();
//            }
//            String uuidFile = UUID.randomUUID().toString();
//            String resultFileName = uuidFile + "." + file.getOriginalFilename();
//            file.transferTo(new File(uploadPath + "/" + resultFileName));
//            track.setFileName(resultFileName);
//        }
        track.setCreateDate(LocalDateTime.now());
        trackService.save(track);
        Iterable<Track> tracks = trackService.findTracks();
        model.put("tracks", tracks);
        return "main";
    }

    @PostMapping("/table_track")
    public String addTrack(
            @AuthenticationPrincipal User user,
            @RequestParam University university,
            @RequestParam String trackName,
            @RequestParam String annotation,
            @RequestParam String keyWordsFrontEnd,
//                @RequestParam("file") MultipartFile file,
            Map<String, Object> model) throws IOException {
        String[] strMain = keyWordsFrontEnd.split(";");
        List<KeyWord> keyWordList = new ArrayList<>();
        Track track = new Track(trackName, annotation, user, university);
        track.setCreateDate(LocalDateTime.now());
        for (String line : strMain) {
            KeyWord keyWord = new KeyWord();
            keyWord.setValue(line);
            keyWord.setTrack(track);
            keyWordList.add(keyWord);
        }
        track.setKeyWords(keyWordList);
//        if (file != null && !file.getOriginalFilename().isEmpty()) {
//            File uploadDir = new File(uploadPath);
//            if(!uploadDir.exists()) {
//                uploadDir.mkdirs();
//            }
//            String uuidFile = UUID.randomUUID().toString();
//            String resultFileName = uuidFile + "." + file.getOriginalFilename();
//            file.transferTo(new File(uploadPath + "/" + resultFileName));
//            track.setFileName(resultFileName);
//        }
        trackService.save(track);
        keyWordRepository.saveAll(keyWordList);
        Iterable<Track> tracks = trackService.findTracks();
        model.put("tracks", tracks);
        return "redirect:/table_track";
    }
}
