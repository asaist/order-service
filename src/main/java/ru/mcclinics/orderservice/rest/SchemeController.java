package ru.mcclinics.orderservice.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mcclinics.orderservice.dao.KeyWordRepository;
import ru.mcclinics.orderservice.domain.*;
import ru.mcclinics.orderservice.service.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class SchemeController {
    private final TrackService trackService;
    private final UniversityService universityService;
    private final LectureService lectureService;
    private final UserService userService;
    private final KeyWordRepository keyWordRepository;
    private final SeriesService seriesService;
    private final EmployeeDtoClientService employeeDtoClientService;

    @GetMapping("/")
    public String main(Model model) throws JsonProcessingException {
        model.addAttribute("tracks", trackService.findTracks());
        model.addAttribute("universities", universityService.getUniversityList());
        model.addAttribute("lectures", lectureService.findLectures());
        model.addAttribute("users", userService.findUsers());
        model.addAttribute("series", seriesService.findSeries());
        model.addAttribute("employees", seriesService.findSeries());
        return "scheme";
    }

    @PostMapping("/addTrack")
    public String addTrack(
            @RequestParam User trackUser,
            @RequestParam University university,
            @RequestParam String trackName,
            @RequestParam String trackAnnotation,
            @RequestParam String trackKeyWords,
//                @RequestParam("file") MultipartFile file,
            Map<String, Object> model) throws IOException {
        String[] strMain = trackKeyWords.split(";");
        List<KeyWord> keyWordList = new ArrayList<>();
        Track track = new Track(trackName, trackAnnotation, trackUser, university);
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
        return "redirect:/";
    }
    @PostMapping("/addSeries")
    public String addSeries(
            @RequestParam User seriesUser,
            @RequestParam String seriesName,
            @RequestParam String seriesAnnotation,
            @RequestParam String seriesKeyWords,
            @RequestParam Track seriesTrack,
            Model model
    ) {
        String[] strMain = seriesKeyWords.split(";");
        List<KeyWord> keyWordList = new ArrayList<>();

        Series series = new Series(seriesName, seriesUser, seriesAnnotation, seriesTrack);
        for (String line : strMain) {
            KeyWord keyWord = new KeyWord();
            keyWord.setValue(line);
            keyWord.setSeries(series);
            keyWordList.add(keyWord);
        }
        series.setKeyWords(keyWordList);
        series.setCreateDate(LocalDateTime.now());
        seriesService.save(series);
        keyWordRepository.saveAll(keyWordList);
        Iterable<Series> series1 = seriesService.findSeries();
        model.addAttribute("series", series1);
        return "redirect:/";
    }

    @PostMapping("/addLecture")
    public String addLecture(
            @RequestParam User lectureUser,
            @RequestParam String lectureName,
            @RequestParam String lectureAnnotation,
            @RequestParam String lectureKeyWords,
            @RequestParam Track lectureTrack,
            @RequestParam Series lectureSeries,
            Model model
    ) {
        String[] strMain = lectureKeyWords.split(";");
        List<KeyWord> keyWordList = new ArrayList<>();
        Lecture lecture = new Lecture(lectureName, lectureUser, lectureAnnotation,  keyWordList, lectureTrack, lectureSeries);
        for (String line : strMain) {
            KeyWord keyWord = new KeyWord();
            keyWord.setValue(line);
            keyWord.setLecture(lecture);
            keyWordList.add(keyWord);
        }

        lecture.setKeyWords(keyWordList);
        lecture.setCreateDate(LocalDateTime.now());
        lectureService.save(lecture);
        keyWordRepository.saveAll(keyWordList);
        Iterable<Lecture> lectures = lectureService.findLectures();
        model.addAttribute("lectures", lectures);
        return "redirect:/";
    }

}
