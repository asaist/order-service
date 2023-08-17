package ru.mcclinics.orderservice.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mcclinics.orderservice.dao.KeyWordRepository;
import ru.mcclinics.orderservice.dao.MkbRepository;
import ru.mcclinics.orderservice.domain.*;
import ru.mcclinics.orderservice.dto.*;
import ru.mcclinics.orderservice.service.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
@Slf4j(topic = "order-service")
@Controller
@RequiredArgsConstructor
public class SchemeController {
    private final TrackService trackService;
    private final UniversityService universityService;
    private final LectureService lectureService;
    private final UserService userService;
    private final KeyWordRepository keyWordRepository;
    private final MkbRepository mkbRepository;
    private final SeriesService seriesService;
    private final AuthorService authorService;
    private final EntityDtoParamService entityDtoParamService;


    @GetMapping("/")
    public String main(Model model) throws JsonProcessingException {
        model.addAttribute("tracks", trackService.findTracks());
        model.addAttribute("universities", universityService.getUniversityList());
        model.addAttribute("lectures", lectureService.findLectures());
        model.addAttribute("series", seriesService.findSeries());
        model.addAttribute("profileTab", true);
        model.addAttribute("lectureFlag", true);
        model.addAttribute("moduleFlag", true);
//        model.addAttribute("track", trackService.findTrackById(1L));
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
    public ResponseEntity<TrackDto> addTrack(@RequestBody RequestData requestData
    ){
        Long trackId = null;
        Track track = new Track();
        if(requestData.getTrackId() != null){
            trackId = Long.parseLong(requestData.getTrackId());
            track = trackService.findTrackById(trackId);
        }
        List<AuthorDto> authorsDtoList = requestData.getAuthors();
        List<ModuleDto> modules = requestData.getModules();
        List<LectureDto> lecturesFromFront = requestData .getLectures();
        String university = requestData.getUniversity();
        String trackName = requestData.getTrackName();
        String trackAnnotation = requestData.getTrackAnnotation();
        String trackKeyWords = requestData.getTrackKeyWords();
//        List<Integer> authorsFromDB = requestData.getAuthorsFromDataBase();

        track.setTrackName(trackName);
        track.setAnnotation(trackAnnotation);
        track.setCreateDate(LocalDateTime.now());
        String[] strMain = trackKeyWords.split(";");
        List<KeyWord> keyWordList = new ArrayList<>();
        for (String line : strMain) {
            KeyWord keyWord = new KeyWord();
            keyWord.setValue(line);
            keyWord.setTrack(track);
            keyWordList.add(keyWord);
        }
//        List<Long> longList = authorsFromDB.stream()
//                .map(i -> (long) i)
//                .collect(Collectors.toList());
//        List<Author> authorList = authorService.findAuthorsByListId(longList);
//        Set<Author> authorSet = new HashSet<>(authorList);
//        track.setAuthors(authorSet);
//        University university1 = universityService.getUniversityById(Long.parseLong(university));
//        track.setUniversity(university1);
        Long index = null;
        for (AuthorDto authorDto : authorsDtoList) {
            if (authorDto.getIsSupervisor()) {
                index = authorDto.getId();
            }
        }
        if (index != null) {
            Author supervisor = authorService.findAuthorById(index);
            track.setSupervisor(supervisor);
        }
        List<Author> authorsFromDB = new ArrayList<>();
        List<Author> authorsFront = new ArrayList<>();
        List<Author> authorsFrontSavedDB = new ArrayList<>();
        List<Author> authors = authorsDtoList.stream().map(Author::new).collect(toList());
        for (Author author : authors) {
            if (author.getAuthorId() == null) {
                authorsFront.add(author);
            } else {
                authorsFromDB.add(author);
            }
        }
        List<Long> ids = authorsFromDB.stream()
                .filter(author -> author.getAuthorId() != null)
                .map(Author::getAuthorId)
                .toList();
        List<Author> authorList = authorService.findAuthorsByListId(ids);

        for(Author author : authorsFront){
            authorService.create(author);
            authorsFrontSavedDB.add(author);
        }
        authorList.addAll(authorsFrontSavedDB);
        Set<Author> authorSet = new HashSet<>(authorList);
        track.setAuthors(authorSet);

        University university1 = universityService.getUniversityById(Long.parseLong(university));
        track.setUniversity(university1);
        track.setKeyWords(keyWordList);
        Track savedTrack = trackService.save(track);
        List<Series> series = modules.stream().map(Series::new).collect(toList());
        lecturesFromFront = lecturesFromFront.stream()
                .filter(element -> element != null && !element.toString().isEmpty())
                .collect(Collectors.toList());
        List<Lecture> lectures = lecturesFromFront.stream().map(Lecture::new).collect(toList());
        series.stream().forEach(ser -> ser.setTrack(savedTrack));
        series.stream().forEach(ser -> ser.setCreateDate(LocalDateTime.now()));
        lectures.stream().forEach(lecture -> lecture.setTrack(savedTrack));
        lectures.stream().forEach(lecture -> lecture.setCreateDate(LocalDateTime.now()));
        List<Series> seriesFromBack = seriesService.saveAll(series);
        for (Lecture lecture1 : lectures){
            if (lecture1.getFrontEndModule() != null) {
                List<Series> filteredSeries = seriesFromBack.stream()
                        .filter(ser -> ser.getFrontEndId() == lecture1.getFrontEndModule())
                        .collect(Collectors.toList());
                lecture1.setSeries(filteredSeries.get(0));
            }
        }
        lectureService.saveAll(lectures);
        keyWordRepository.saveAll(keyWordList);
        Iterable<Track> tracks = trackService.findTracks();
        return ResponseEntity.ok(new TrackDto(track));
    }

    @PostMapping("/editTrack")
    public ResponseEntity<String> update(@RequestBody RequestData requestData
    ){
        TrackDto messageFromDb = new TrackDto();
        return ResponseEntity.ok("1");
    }
    @PostMapping("/addSeries")
    public ResponseEntity<ModuleDto> addSeries(@RequestBody ModuleRequestData moduleRequestData
    ){
        Long seriesId = null;
        Series series = new Series();
        if(moduleRequestData.getSeriesId() != null){
            seriesId = Long.parseLong(moduleRequestData.getSeriesId());
            series = seriesService.findSeriesById(seriesId);
        }
        List<AuthorDto> authorsDtoList = moduleRequestData.getModuleAuthors();
        List<LectureDto> lecturesFromFront = moduleRequestData .getLectures();
        String seriesName = moduleRequestData.getSeriesName();
        String seriesAnnotation = moduleRequestData.getSeriesAnnotation();
        String seriesKeyWords = moduleRequestData.getSeriesKeyWords();
        List<Mkb> mkbs = moduleRequestData.getMkbs();

        String[] strMain = seriesKeyWords.split(";");
        List<KeyWord> keyWordList = new ArrayList<>();
        for (String line : strMain) {
            KeyWord keyWord = new KeyWord();
            keyWord.setValue(line);
            keyWord.setSeries(series);
            keyWordList.add(keyWord);
        }

        List<Author> authorsFromDB = new ArrayList<>();
        List<Author> authorsFront = new ArrayList<>();
        List<Author> authorsFrontSavedDB = new ArrayList<>();
        List<Author> authors = authorsDtoList.stream().map(Author::new).collect(toList());
        for (Author author : authors) {
            if (author.getAuthorId() == null) {
                authorsFront.add(author);
            } else {
                authorsFromDB.add(author);
            }
        }
        List<Long> ids = authorsFromDB.stream()
                .filter(author -> author.getAuthorId() != null)
                .map(Author::getAuthorId)
                .toList();
        List<Author> authorList = authorService.findAuthorsByListId(ids);

        for(Author author : authorsFront){
            authorService.create(author);
            authorsFrontSavedDB.add(author);
        }
        authorList.addAll(authorsFrontSavedDB);
        Set<Author> authorSet = new HashSet<>(authorList);
        series.setAuthors(authorSet);

        Track track = trackService.findTrackById(Long.parseLong(moduleRequestData.getTrack()));
        series.setTrack(track);
//        series.setKeyWords(keyWordList);

        series.setCreateDate(LocalDateTime.now());
        series.setSeriesName(seriesName);
        series.setAnnotation(seriesAnnotation);
        Series savedSeries = seriesService.save(series);
        keyWordList.stream().forEach(keyWord -> keyWord.setSeries(savedSeries));
        lecturesFromFront = lecturesFromFront.stream()
                .filter(element -> element != null && !element.toString().isEmpty())
                .collect(Collectors.toList());
        List<Lecture> lectures = lecturesFromFront.stream().map(Lecture::new).collect(toList());
        lectures.stream().forEach(lecture -> lecture.setSeries(savedSeries));
        lectures.stream().forEach(lecture -> lecture.setCreateDate(LocalDateTime.now()));
        lectures.stream().forEach(lecture -> lecture.setAuthors(authorSet));
        mkbs.stream().forEach(mkb -> mkb.setSeries(savedSeries));
        lectureService.saveAll(lectures);
        keyWordRepository.saveAll(keyWordList);
        mkbRepository.saveAll(mkbs);
        Iterable<Series> series1 = seriesService.findSeries();
        log.info("/create [module {}]", series);

        return ResponseEntity.ok(new ModuleDto(series));
    }

    @PostMapping("/addLecture")
    public ResponseEntity<LectureDto> addLecture(@RequestBody LectureRequestData lectureRequestData
    ) {
        Long lectureId = null;
        Lecture lecture = new Lecture();
        if(lectureRequestData.getLectureId() != null){
            lectureId = Long.parseLong(lectureRequestData.getLectureId());
            lecture = lectureService.findLectureById(lectureId);
        }
        List<AuthorDto> authorsDtoList = lectureRequestData.getLectureAuthors();

        String lectureName = lectureRequestData.getLectureName();
        String lectureAnnotation = lectureRequestData.getLectureAnnotation();
        String lectureKeyWords = lectureRequestData.getLectureKeyWords();

        String[] strMain = lectureKeyWords.split(";");
        List<KeyWord> keyWordList = new ArrayList<>();
        for (String line : strMain) {
            KeyWord keyWord = new KeyWord();
            keyWord.setValue(line);
            keyWord.setLecture(lecture);
            keyWordList.add(keyWord);
        }

        List<Author> authorsFromDB = new ArrayList<>();
        List<Author> authorsFront = new ArrayList<>();
        List<Author> authorsFrontSavedDB = new ArrayList<>();
        List<Author> authors = authorsDtoList.stream().map(Author::new).collect(toList());
        for (Author author : authors) {
            if (author.getAuthorId() == null) {
                authorsFront.add(author);
            } else {
                authorsFromDB.add(author);
            }
        }
        List<Long> ids = authorsFromDB.stream()
                .filter(author -> author.getAuthorId() != null)
                .map(Author::getAuthorId)
                .toList();
        List<Author> authorList = authorService.findAuthorsByListId(ids);

        for(Author author : authorsFront){
            authorService.create(author);
            authorsFrontSavedDB.add(author);
        }
        authorList.addAll(authorsFrontSavedDB);
        Set<Author> authorSet = new HashSet<>(authorList);
        lecture.setAuthors(authorSet);
        Track track = trackService.findTrackById(Long.parseLong(lectureRequestData.getTrack()));
        lecture.setTrack(track);
        Series series = seriesService.findSeriesById(Long.parseLong(lectureRequestData.getSeries()));
        lecture.setSeries(series);
        lecture.setKeyWords(keyWordList);
        lecture.setLectureName(lectureName);
        lecture.setAnnotation(lectureAnnotation);
        lecture.setCreateDate(LocalDateTime.now());
        Lecture savedLecture = lectureService.save(lecture);
        List<MkbDto> mkbs = lectureRequestData.getMkbs();
        List<Mkb> mkbs1 = mkbs.stream().map(Mkb::new).collect(toList());
        mkbs1.stream().forEach(mkb -> mkb.setLecture(savedLecture));
        mkbRepository.saveAll(mkbs1);
        keyWordRepository.saveAll(keyWordList);
        log.info("/create [lecture {}]", lecture);
        return ResponseEntity.ok(new LectureDto(lecture));
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