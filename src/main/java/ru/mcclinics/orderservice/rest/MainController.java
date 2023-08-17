package ru.mcclinics.orderservice.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mcclinics.orderservice.dao.KeyWordRepository;
import ru.mcclinics.orderservice.domain.*;
import ru.mcclinics.orderservice.dto.*;
import ru.mcclinics.orderservice.service.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.OK;
@Slf4j(topic = "order-service")
@Controller
@RequiredArgsConstructor
public class MainController {

    private final TrackService trackService;
    private final UniversityService universityService;
    private final SeriesService seriesService;
    private final LectureService lectureService;
    private final EntityDtoParamService entityDtoParamService;
    private final KeyWordRepository keyWordRepository;

    @Value("${files.upload.baseDir}")
    private String uploadPath;
//    @GetMapping("/")z
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

    @GetMapping("/authors_track/{id}")
    @ResponseStatus(OK)
    public @ResponseBody RequestData getAuthorsByTrackId(@PathVariable Long id) {
        log.info("/getAuthorsByTrackId");
        Track track = trackService.findTrackById(id);
        Set<Author> authors = trackService.findAuthorsById(id);
        List<AuthorDto> authorDtos = authors.stream().map(AuthorDto::new).collect(toList());
        if (track.getSupervisor() != "<none>") {
            Long supervisorId = track.getSupervisorId();
            authorDtos.stream()
                    .filter(authorDto -> authorDto.getId() == supervisorId)
                    .forEach(authorDto -> authorDto.setIsSupervisor(true));
        }
        List<Series> series = seriesService.findSeriesByTrackId(id);
        List<ModuleDto> moduleDtos = series.stream().map(ModuleDto::new).collect(toList());
        List<Lecture> lectures = lectureService.findLectureByTrackId(id);
        List<LectureDto> lectureDtos = lectures.stream().map(LectureDto::new).collect(toList());
        RequestData rd = new RequestData(authorDtos, moduleDtos, lectureDtos);
        return rd;
    }

    @GetMapping("/authors_module/{id}")
    @ResponseStatus(OK)
    public @ResponseBody RequestData getAuthorsByModuleId(@PathVariable Long id) {
        log.info("/getAuthorsByModuleId");
        Series series = seriesService.findSeriesById(id);
        Set<Author> authors = seriesService.findAuthorsById(id);
        List<AuthorDto> authorDtos = authors.stream().map(AuthorDto::new).collect(toList());
        List<Lecture> lectures = lectureService.findLectureBySeriesId(id);
        List<LectureDto> lectureDtos = lectures.stream().map(LectureDto::new).collect(toList());
        List<Mkb> mkbs = series.getMkbs();
        List<MkbDto> mkbDtos = mkbs.stream().map(MkbDto::new).collect(toList());
        RequestData rd = new RequestData(authorDtos, lectureDtos, mkbDtos, series.getId().toString());
        return rd;
    }

    @GetMapping("/authors_lecture/{id}")
    @ResponseStatus(OK)
    public @ResponseBody RequestData getAuthorsByLectureId(@PathVariable Long id) {
        log.info("/getAuthorsByLectureId");
        Lecture lecture = lectureService.findLectureById(id);
        Set<Author> authors = lectureService.findAuthorsById(id);
        List<AuthorDto> authorDtos = authors.stream().map(AuthorDto::new).collect(toList());
        List<Lecture> lectures = lectureService.findLectureBySeriesId(id);
        List<LectureDto> lectureDtos = lectures.stream().map(LectureDto::new).collect(toList());
        List<Mkb> mkbs = lecture.getMkbs();
        List<MkbDto> mkbDtos = mkbs.stream().map(MkbDto::new).collect(toList());
        RequestData rd = new RequestData(authorDtos, mkbDtos);
        return rd;
    }

    @GetMapping("/table_track/{id}")
    public String getOne(@PathVariable("id") Track track, Model model){
        model.addAttribute("track", track);
        return "track_up";
    }

    @GetMapping("/track/{id}")
    public String getTrack(@PathVariable("id") Track track, Model model){
        model.addAttribute("track", track);
        model.addAttribute("tracks", trackService.findTracks());
        model.addAttribute("series", seriesService.findSeries());
        model.addAttribute("lectures", lectureService.findLectures());
        model.addAttribute("universities", universityService.getUniversityList());
        model.addAttribute("profileTab", false);
        model.addAttribute("moduleFlag", true);
        model.addAttribute("lectureFlag", true);
        model.addAttribute("contactTab", false);
//        model.addAttribute("tracks", null);
        return "scheme";

    }

    @GetMapping("/module/{id}")
    public String getModule(@PathVariable("id") Series series, Model model) throws JsonProcessingException {
        model.addAttribute("seriesOne", series);
        model.addAttribute("series", seriesService.findSeries());
        model.addAttribute("universities", universityService.getUniversityList());
        model.addAttribute("moduleFlag", false);
        model.addAttribute("lectureFlag", true);
        model.addAttribute("profileTab", true);
        model.addAttribute("tracks", trackService.findTracks());
        model.addAttribute("lectures", lectureService.findLectures());
        List<Mkb10Dto> entityDtoList = entityDtoParamService.getEntityDtoList();
        model.addAttribute("mkb10", entityDtoList);
        return "scheme";

    }

    @GetMapping("/lecture/{id}")
    public String getLecture(@PathVariable("id") Lecture lecture, Model model) throws JsonProcessingException {
        model.addAttribute("lecture", lecture);
        model.addAttribute("series", seriesService.findSeries());
        model.addAttribute("universities", universityService.getUniversityList());
        model.addAttribute("lectureFlag", false);
        model.addAttribute("moduleFlag", true);
        model.addAttribute("profileTab", true);
        model.addAttribute("tracks", trackService.findTracks());
        model.addAttribute("lectures", lectureService.findLectures());
        List<Mkb10Dto> entityDtoList = entityDtoParamService.getEntityDtoList();
        model.addAttribute("mkb10", entityDtoList);
        return "scheme";

    }

    @PostMapping("/main")
    public String add(
                @AuthenticationPrincipal User user,
                @RequestParam String trackName,
                @RequestParam String annotation,
//                @RequestParam("file") MultipartFile file,
               Map<String, Object> model) throws IOException {
        Track track = new Track(trackName, annotation);
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

//    @PostMapping("/table_track")
//    public String addTrack(
//            @AuthenticationPrincipal User user,
//            @RequestParam University university,
//            @RequestParam String trackName,
//            @RequestParam String annotation,
//            @RequestParam String keyWordsFrontEnd,
////                @RequestParam("file") MultipartFile file,
//            Map<String, Object> model) throws IOException {
//        String[] strMain = keyWordsFrontEnd.split(";");
//        List<KeyWord> keyWordList = new ArrayList<>();
//        Author user1 = new Author();
//        Track track = new Track(trackName, annotation, user1, university);
//        track.setCreateDate(LocalDateTime.now());
//        for (String line : strMain) {
//            KeyWord keyWord = new KeyWord();
//            keyWord.setValue(line);
//            keyWord.setTrack(track);
//            keyWordList.add(keyWord);
//        }
//        track.setKeyWords(keyWordList);
////        if (file != null && !file.getOriginalFilename().isEmpty()) {
////            File uploadDir = new File(uploadPath);
////            if(!uploadDir.exists()) {
////                uploadDir.mkdirs();
////            }
////            String uuidFile = UUID.randomUUID().toString();
////            String resultFileName = uuidFile + "." + file.getOriginalFilename();
////            file.transferTo(new File(uploadPath + "/" + resultFileName));
////            track.setFileName(resultFileName);
////        }
//        trackService.save(track);
//        keyWordRepository.saveAll(keyWordList);
//        Iterable<Track> tracks = trackService.findTracks();
//        model.put("tracks", tracks);
//        return "redirect:/table_track";
//    }
}
