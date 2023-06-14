package ru.mcclinics.orderservice.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mcclinics.orderservice.dao.KeyWordRepository;
import ru.mcclinics.orderservice.domain.*;
import ru.mcclinics.orderservice.dto.EmployeeDto;
import ru.mcclinics.orderservice.service.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.toList;

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
    private final AuthorService authorService;

    @GetMapping("/")
    public String main(Model model) throws JsonProcessingException {
        model.addAttribute("tracks", trackService.findTracks());
        model.addAttribute("universities", universityService.getUniversityList());
        model.addAttribute("lectures", lectureService.findLectures());

        model.addAttribute("series", seriesService.findSeries());
        List<EmployeeDto> employeeDtoList = employeeDtoClientService.getEmployeeDtoList();
        List<User> users = employeeDtoList.stream().map(User::new).collect(toList());
        model.addAttribute("users", users);
        //  model.addAttribute("users", userService.findUsers();
        model.addAttribute("employees", employeeDtoClientService.getEmployeeDtoList());
        int i = 1;
        return "scheme";
    }

    @PostMapping("/addTrack")
    public String addTrack(
            @RequestParam University university,
            @RequestParam String trackAuthor,
            @RequestParam String trackName,
            @RequestParam String trackAnnotation,
            @RequestParam String trackKeyWords,
//                @RequestParam("file") MultipartFile file,
            Map<String, Object> model
    ){
        //парсим автора из сервиса users
        String[] strMain1 = trackAuthor.split(", ");
        Author author = new Author();
        List<String> list = new ArrayList<>();
        for (String line : strMain1) {
            int equalsIndex = line.indexOf("=");
            if (equalsIndex >= 0) {
                String result = line.substring(equalsIndex + 1);
                list.add(result);
            }
        }
        author.setGuid(list.get(0));
        author.setFirstName(list.get(1));
        author.setLastName(list.get(2));
        author.setMiddleName(list.get(3));

        Track track = new Track(trackName, trackAnnotation, author, university);
        track.setCreateDate(LocalDateTime.now());
        String[] strMain = trackKeyWords.split(";");
        List<KeyWord> keyWordList = new ArrayList<>();

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
        List<Author> authors = authorService.findAuthors();
        Map<String, Author> map = new HashMap<String, Author>();
        for(Author author1:authors){
            map.put(author1.getGuid(), author1);
        }
        if (!map.containsKey(author.getGuid())){
            authorService.create(author);
        }
        track.setAuthor(authorService.findAuthorByGuid(list.get(0)));
        trackService.save(track);
        keyWordRepository.saveAll(keyWordList);
        Iterable<Track> tracks = trackService.findTracks();
        model.put("tracks", tracks);
        return "redirect:/";
    }
    @PostMapping("/addSeries")
    public String addSeries(
            @RequestParam String seriesAuthor,
            @RequestParam String seriesName,
            @RequestParam String seriesAnnotation,
            @RequestParam String seriesKeyWords,
            @RequestParam Track seriesTrack,
            Model model
    ) {
        //парсим автора из сервиса users
        String[] strMain1 = seriesAuthor.split(", ");
        Author author = new Author();
        List<String> list = new ArrayList<>();
        for (String line : strMain1) {
            int equalsIndex = line.indexOf("=");
            if (equalsIndex >= 0) {
                String result = line.substring(equalsIndex + 1);
                list.add(result);
            }
        }
        author.setGuid(list.get(0));
        author.setFirstName(list.get(1));
        author.setLastName(list.get(2));
        author.setMiddleName(list.get(3));

        Series series = new Series(seriesName, author, seriesAnnotation, seriesTrack);

        String[] strMain = seriesKeyWords.split(";");
        List<KeyWord> keyWordList = new ArrayList<>();
        for (String line : strMain) {
            KeyWord keyWord = new KeyWord();
            keyWord.setValue(line);
            keyWord.setSeries(series);
            keyWordList.add(keyWord);
        }
        List<Author> authors = authorService.findAuthors();
        Map<String, Author> map = new HashMap<String, Author>();
        for(Author author1:authors){
            map.put(author1.getGuid(), author1);
        }
        if (!map.containsKey(author.getGuid())){
            authorService.create(author);
        }
        series.setAuthor(authorService.findAuthorByGuid(list.get(0)));

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
            @RequestParam String lectureAuthor,
            @RequestParam String lectureName,
            @RequestParam String lectureAnnotation,
            @RequestParam String lectureKeyWords,
            @RequestParam Track lectureTrack,
            @RequestParam Series lectureSeries,
            Model model
    ) {
        //парсим автора из сервиса users
        String[] strMain1 = lectureAuthor.split(", ");
        Author author = new Author();
        List<String> list = new ArrayList<>();
        for (String line : strMain1) {
            int equalsIndex = line.indexOf("=");
            if (equalsIndex >= 0) {
                String result = line.substring(equalsIndex + 1);
                list.add(result);
            }
        }
        author.setGuid(list.get(0));
        author.setFirstName(list.get(1));
        author.setLastName(list.get(2));
        author.setMiddleName(list.get(3));

        Lecture lecture = new Lecture(lectureName, author, lectureAnnotation,  lectureTrack, lectureSeries);

        String[] strMain = lectureKeyWords.split(";");
        List<KeyWord> keyWordList = new ArrayList<>();
        for (String line : strMain) {
            KeyWord keyWord = new KeyWord();
            keyWord.setValue(line);
            keyWord.setLecture(lecture);
            keyWordList.add(keyWord);
        }

        List<Author> authors = authorService.findAuthors();
        Map<String, Author> map = new HashMap<String, Author>();
        for(Author author1:authors){
            map.put(author1.getGuid(), author1);
        }
        if (!map.containsKey(author.getGuid())){
            authorService.create(author);
        }
        lecture.setAuthor(authorService.findAuthorByGuid(list.get(0)));

        lecture.setKeyWords(keyWordList);
        lecture.setCreateDate(LocalDateTime.now());
        lectureService.save(lecture);
        keyWordRepository.saveAll(keyWordList);
        Iterable<Lecture> lectures = lectureService.findLectures();
        model.addAttribute("lectures", lectures);
        return "redirect:/";
    }

}
