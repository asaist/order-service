package ru.mcclinics.orderservice.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mcclinics.orderservice.dao.KeyWordRepository;
import ru.mcclinics.orderservice.domain.*;
import ru.mcclinics.orderservice.dto.Mkb10Dto;
import ru.mcclinics.orderservice.dto.RequestData;
import ru.mcclinics.orderservice.service.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class SchemeController {
    private final TrackService trackService;
    private final UniversityService universityService;
    private final LectureService lectureService;
    private final UserService userService;
    private final KeyWordRepository keyWordRepository;
    private final SeriesService seriesService;
    private final AuthorService authorService;
    private final EntityDtoParamService entityDtoParamService;

    @GetMapping("/")
    public String main(Model model) throws JsonProcessingException {
        model.addAttribute("tracks", trackService.findTracks());
        model.addAttribute("universities", universityService.getUniversityList());
        model.addAttribute("lectures", lectureService.findLectures());
        model.addAttribute("series", seriesService.findSeries());
//        model.addAttribute("users", userService.findUsers());
//        model.addAttribute("authors", authorService.findAuthors());
        List<Mkb10Dto> entityDtoList = entityDtoParamService.getEntityDtoList();
//        entityDtoList.removeIf(obj -> obj.getCode() == null);
//        entityDtoList.stream().filter(b -> b.getCode().equals(null)).getFirst().ifPresent(books::remove);
        model.addAttribute("mkb10", entityDtoList);
        return "scheme";
    }

    @PostMapping("/addTrack1")
    public String addTrack(
//            @RequestParam University university,
//            @RequestParam String authors,
//            @RequestParam String trackName,
//            @RequestParam String trackAnnotation,
//            @RequestParam String trackKeyWords,
//                @RequestParam("file") MultipartFile file,
            Map<String, Object> model
    ){
        Track track = new Track();
        track.setCreateDate(LocalDateTime.now());
//        String[] strMain = trackKeyWords.split(";");
//        List<KeyWord> keyWordList = new ArrayList<>();
//        for (String line : strMain) {
//            KeyWord keyWord = new KeyWord();
//            keyWord.setValue(line);
//            keyWord.setTrack(track);
//            keyWordList.add(keyWord);
//        }
//        track.setKeyWords(keyWordList);
//        trackService.save(track);
//        keyWordRepository.saveAll(keyWordList);
        Iterable<Track> tracks = trackService.findTracks();
        model.put("tracks", tracks);
        return "redirect:/";
    }

    @PostMapping("/addTrack2")
    public String addTrack(@RequestBody RequestData requestData
    ){
        Track track = new Track();
        track.setCreateDate(LocalDateTime.now());
//        String[] strMain = trackKeyWords.split(";");
        List<KeyWord> keyWordList = new ArrayList<>();
//        for (String line : strMain) {
//            KeyWord keyWord = new KeyWord();
//            keyWord.setValue(line);
//            keyWord.setTrack(track);
//            keyWordList.add(keyWord);
//        }
//        List<Long> longList = authors.stream()
//                .map(i -> (long) i)
//                .collect(Collectors.toList());
//        List<Author> authorList = authorService.findAuthorsByListId(longList);
//        Set<Author> authorSet = new HashSet<>(authorList);
//        track.setAuthors(authorSet);
//        University university1 = universityService.getUniversityById(Long.parseLong(university));
//        track.setUniversity(university1);
        track.setKeyWords(keyWordList);
        trackService.save(track);
        keyWordRepository.saveAll(keyWordList);
        Iterable<Track> tracks = trackService.findTracks();
        return "redirect:/";
    }
    @PostMapping("/addSeries")
    public String addSeries(
            @RequestParam Author seriesAuthor,
            @RequestParam String seriesName,
            @RequestParam String seriesAnnotation,
            @RequestParam String seriesKeyWords,
            @RequestParam Track seriesTrack,
            Model model
    ) {
        Series series = new Series(seriesName, seriesAuthor, seriesAnnotation, seriesTrack);
        String[] strMain = seriesKeyWords.split(";");
        List<KeyWord> keyWordList = new ArrayList<>();
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
            @RequestParam Author lectureAuthor,
            @RequestParam String lectureName,
            @RequestParam String lectureAnnotation,
            @RequestParam String lectureKeyWords,
            @RequestParam Track lectureTrack,
            @RequestParam Series lectureSeries,
            Model model
    ) {
        Lecture lecture = new Lecture(lectureName, lectureAuthor, lectureAnnotation,  lectureTrack, lectureSeries);

        String[] strMain = lectureKeyWords.split(";");
        List<KeyWord> keyWordList = new ArrayList<>();
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




//
//Author author = new Author();
//    List<String> list = new ArrayList<>();
//        for (String line : strMain1) {
//                int equalsIndex = line.indexOf("=");
//                if (equalsIndex >= 0) {
//                String result = line.substring(equalsIndex + 1);
//                list.add(result);
//                }
//                }
//                author.setGuid(list.get(0));
//                author.setFirstName(list.get(1));
//                author.setLastName(list.get(2));
//                author.setMiddleName(list.get(3));

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