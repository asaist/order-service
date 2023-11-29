package ru.mcclinics.orderservice.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itextpdf.text.DocumentException;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mcclinics.orderservice.dao.*;
import ru.mcclinics.orderservice.domain.*;
import ru.mcclinics.orderservice.domain.Process;
import ru.mcclinics.orderservice.dto.*;
import ru.mcclinics.orderservice.service.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
@Slf4j(topic = "order-service")
@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "https://dev.service.samsmu.ru")
public class SchemeController {
    private final TrackService trackService;
    private final UniversityService universityService;
    private final LectureService lectureService;
    private final UserService userService;
    private final KeyWordRepository keyWordRepository;
    private final ProcessRepository processRepository;
    private final MkbRepository mkbRepository;
    private final LocalizationRepository localizationRepository;
    private final DisciplineRepository disciplineRepository;
    private final SeriesService seriesService;
    private final AuthorService authorService;
    private final EntityDtoParamService entityDtoParamService;
    private final PdfGenerator pdfGenertor;
    private final DocumentProcessingService documentProcessingService;
    private final ShapeService shapeService;
    private final CheckTokenService checkTokenService;

    public static final String ApplicationForApprovalProcessType = "114";
    public static final String ForExecutionProcessType = "119";
    public static final String initDocType = "1";

    @GetMapping("/msg")
    @ResponseStatus(HttpStatus.OK)
    public String sendMsg(){
        return "msg";
    }

    @GetMapping("/public")
    @ResponseStatus(HttpStatus.OK)
    public String setFlag(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {

        System.out.println("TokenController on @GetMapping(\"/public\") track.samsmu.ru: " + authorizationHeader);
        Date d = new Date();
        SimpleDateFormat simpDate;
        simpDate = new SimpleDateFormat("kk:mm:ss");
        System.out.println(simpDate.format(d));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/public/home");
        headers.add("Authorization", authorizationHeader);
        headers.add("X-Frame-Options", "ALLOWED");
//        headers.add("Access-Control-Allow-Origin", "*");
//        headers.add("Access-Control-Allow-Headers", "*");
//            OAuth2AuthorizedClient authorizedClient = inMemoryOAuth2AuthorizedClientService.loadAuthorizedClient("keycloak", authentication.getName());
//            String accessToken = authorizedClient.getAccessToken().getTokenValue();
        return "public";
//            return ResponseEntity.ok().headers(headers).build();
//            return "redirect: /";
//            return "redirect:/public/home";
    }



    @GetMapping("/public/home")
    @ResponseStatus(HttpStatus.OK)
    public String main(Model model, @RequestHeader(value = "Authorization", required = false) String authorizationHeader) throws JsonProcessingException {
        if (authorizationHeader != null) {
            System.out.println("TokenController on @GetMapping(\"/public/home\") track.samsmu.ru: " + authorizationHeader);
        }

//        model.addAttribute("track", trackService.findTrackById(1L));
//        model.addAttribute("users", userService.findUsers());
//        model.addAttribute("authors", authorService.findAuthors());

//        entityDtoList.removeIf(obj -> obj.getCode() == null);
//        entityDtoList.stream().filter(b -> b.getCode().equals(null)).getFirst().ifPresent(books::remove);
            model.addAttribute("tracks", trackService.findTracks());
            model.addAttribute("universities", universityService.getUniversityList());
            model.addAttribute("lectures", lectureService.findLectures());
            model.addAttribute("series", seriesService.findSeries());
            model.addAttribute("shapes", shapeService.findShapes());
            model.addAttribute("profileTab", true);
            model.addAttribute("lectureFlag", true);
            model.addAttribute("moduleFlag", true);
            List<Mkb10Dto> entityDtoList = entityDtoParamService.getEntityDtoList();
            List<Mkb10Dto> localization = entityDtoParamService.getEntityLocalizationList();
            List<Mkb10Dto> discipline = entityDtoParamService.getEntityDisciplineList();
            List<Mkb10Dto> spravochnik = entityDtoParamService.getEntitySpravochnikList();
            model.addAttribute("localization", localization);
            model.addAttribute("discipline", discipline);
            model.addAttribute("spravochnik", spravochnik);
            model.addAttribute("mkb10", entityDtoList);
            Author supervisor = checkTokenService.checkToken(authorizationHeader);
            model.addAttribute("supervisor", supervisor.getAuthorId());
            return  supervisor!=null ? "scheme" : "public";
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
    ) throws DocumentException, FileNotFoundException, JsonProcessingException {
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
    @PostMapping(value = "/sendTrack", consumes = {"application/json"})
    public ResponseEntity sendTrackForApproval(@RequestParam Long savedTrack
    ) throws DocumentException, IOException, JRException {

//        String greeting = "Вам направляется на согласование : https://dev.track.samsmu.ru/";
//        String base64 = pdfGenertor.generatePdf(greeting + "track/" + savedTrack);
//        documentProcessingService.launchProcess(base64);
        Track track = trackService.findTrackById(savedTrack);
        OrderDocument reportData = new OrderDocument("https://dev.track.samsmu.ru/track/" + savedTrack);
        reportData.setName(track.getTrackName());
        reportData.setAnnotation(track.getAnnotation());
        String keyWords = String.join("; ", track.getKeyWords());
        reportData.setKeywords(keyWords);
        reportData.setSupervisor(track.getSupervisor());
        Set<Author> authors = track.getAuthors();
        String authorsNames = authors.stream()
                .map(author -> author.getLastName() + " " + author.getFirstName() + " " + author.getMiddleName())
                .collect(Collectors.joining("; "));
        reportData.setAuthors(authorsNames);
        List<OrderDocument> orderDocumentList = Arrays.asList(reportData);
        LectureDto lectureDto = new LectureDto();
        documentProcessingService.generatePdfForExecution(
                lectureDto,
                ApplicationForApprovalProcessType,
                track.getSupervisor(),
                null, null);


        return ResponseEntity.ok(200);
    }

    @PostMapping(value = "/sendCourse", consumes = {"application/json"})
    public ResponseEntity sendCourseForApproval(@RequestParam Long savedSeries) throws IOException {
        System.out.println("CONTROLLER: " + "/sendCourse");
        Series series = seriesService.findSeriesById(savedSeries);
        Author supervisor = series.getSupervisor();
        List<Lecture> lectures = series.getLectures();
        List<LectureDto> lectureDtos = lectures.stream().map(LectureDto::new).collect(toList());
        ModuleDto moduleDto = new ModuleDto(series);
        documentProcessingService.generatePdfForApprove(moduleDto,
                    ApplicationForApprovalProcessType,
                    supervisor.getGuid(),
                    null, null, initDocType, lectureDtos);
        return ResponseEntity.ok(200);
    }
    @PostMapping(value = "/sendCourseForExecution", consumes = {"application/json"})
    public ResponseEntity sendCourseForExecution(
            @RequestParam Long savedSeries,
            @RequestParam(required = false) Long savedLecture
    ) throws IOException, JRException {
        Series series = seriesService.findSeriesById(savedSeries);
        Author supervisor = series.getSupervisor();
        List<Lecture> lectures = new ArrayList<>();
        System.out.println("Лекция на отправку ID: " + savedLecture);
        if (savedLecture != null){
            lectures.add(lectureService.findLectureById(savedLecture));
            System.out.println("Лекция: " + lectures.get(0).getLectureName());
        } else {
            lectures = series.getLectures();
        }
        for (Lecture lecture : lectures) {
            LectureDto lectureDto = new LectureDto(lecture);
            for (Author author : lecture.getAuthors()){
                Process process = documentProcessingService.generatePdfForExecution(
                        lectureDto,
                        ForExecutionProcessType,
                        supervisor.getGuid(),
                        author.getGuid(), initDocType);
                process.setLecture(lecture);
                process.setSeries(series);
                processRepository.save(process);

            }
        }
        return ResponseEntity.ok(200);
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
        series.setModuleLearnCompetenceOne(moduleRequestData.getModuleLearnCompetenceOne());
        series.setModuleLearnCompetenceTwo(moduleRequestData.getModuleLearnCompetenceTwo());
        series.setModuleLearnCompetenceThree(moduleRequestData.getModuleLearnCompetenceThree());
        series.setModuleLearnCompetenceFour(moduleRequestData.getModuleLearnCompetenceFour());
        if(moduleRequestData.getSeriesId() != null){
            seriesId = Long.parseLong(moduleRequestData.getSeriesId());
            series = seriesService.findSeriesById(seriesId);
        }
        List<AuthorDto> authorsDtoList = moduleRequestData.getModuleAuthors();
        List<LectureDto> lecturesFromFront = moduleRequestData.getLectures();
        System.out.println("lecturesFromFront: " + lecturesFromFront);
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

        Long index = null;
        for (AuthorDto authorDto : authorsDtoList) {
            if (authorDto.getIsSupervisor()) {
                index = authorDto.getId();
            }
        }
        if (index != null) {
            Author supervisor = authorService.findAuthorById(index);
            series.setSupervisor(supervisor);
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
        //TODO Refactor kostil
        series.setShape(new Shape(1L));
        Series savedSeries = seriesService.save(series);
        keyWordList.stream().forEach(keyWord -> keyWord.setSeries(savedSeries));
        if (lecturesFromFront != null) {
            lecturesFromFront = lecturesFromFront.stream()
                    .filter(element -> element != null && !element.toString().isEmpty())
                    .collect(Collectors.toList());
            List<Lecture> lectures = lecturesFromFront.stream().map(Lecture::new).collect(toList());
            System.out.println("ID LECTURE: " + lectures.get(0).getId());
            lectures.stream().forEach(lecture -> lecture.setSeries(savedSeries));
            lectures.stream().forEach(lecture -> lecture.setCreateDate(LocalDateTime.now()));
            lectures.stream().forEach(lecture -> lecture.setAuthors(authorSet));
            //TODO Refactor kostil
            lectures.stream().forEach(lecture -> lecture.setShape(new Shape(1L)));
            lectureService.saveAll(lectures);
            Lecture lectureDb = lectures.get(lectures.size() - 1);
            System.out.println("ID NEW LECTURE: " + lectures.get(lectures.size() - 1).getId());
        }
        mkbs.stream().forEach(mkb -> mkb.setSeries(savedSeries));
        keyWordRepository.saveAll(keyWordList);
        mkbRepository.saveAll(mkbs);
        log.info("/create [module {}]", series);
        System.out.println("ID NEW COURSE: " + series.getId());
        return ResponseEntity.ok(new ModuleDto(series));
    }

    @PostMapping("/addLecture")
    public ResponseEntity<LectureDto> addLecture(@RequestBody LectureRequestData lectureRequestData
    ) {
        Lecture lecture = new Lecture(lectureRequestData.getLecture());
        List<AuthorDto> authors = lectureRequestData.getLecture().getAuthors();
        Set<Author> authorsSet = new HashSet<>();
        if (authors != null) {
            List<Author> authorsFront = authors.stream().map(Author::new).collect(toList());

            List<Long> ids = authorsFront.stream()
                    .filter(author -> author.getAuthorId() != null)
                    .map(Author::getAuthorId)
                    .toList();
            List<Author> authorList = authorService.findAuthorsByListId(ids);

            authorsSet = authorList.stream()
                    .collect(Collectors.toSet());
        }
        lecture.setAuthors(authorsSet);
        lecture.setSupervisor(authorsSet.iterator().next());
        Lecture savedLecture = lectureService.save(lecture);
        System.out.println("SavedLectireId: " + savedLecture.getId());
        String lectureKeyWords = lectureRequestData.getLecture().getLectureModuleKeyWords();
        String[] strMain = lectureKeyWords.split(";");
        List<KeyWord> keyWordList = new ArrayList<>();
        for (String line : strMain) {
            KeyWord keyWord = new KeyWord();
            keyWord.setValue(line);
            keyWord.setLecture(savedLecture);
            keyWordList.add(keyWord);
        }





        System.out.println("key words in lecture: ");
        keyWordList.stream()
                .map(KeyWord::getValue)
                .forEach(System.out::println);

        List<MkbDto> mkbs = lectureRequestData.getMkbs();
        List<MkbDto> diss = lectureRequestData.getDiss();
        List<MkbDto> locs = lectureRequestData.getLocs();

        List<Mkb> mkbs1 = mkbs.stream().map(Mkb::new).collect(toList());
        List<Discipline> diss1 = diss.stream().map(Discipline::new).collect(toList());
        List<Localization> locs1 = locs.stream().map(Localization::new).collect(toList());

        mkbs1.stream().forEach(mkb -> mkb.setLecture(savedLecture));
        diss1.stream().forEach(mkb -> mkb.setLecture(savedLecture));
        locs1.stream().forEach(mkb -> mkb.setLecture(savedLecture));

        mkbRepository.saveAll(mkbs1);
        disciplineRepository.saveAll(diss1);
        localizationRepository.saveAll(locs1);

        keyWordRepository.saveAll(keyWordList);
        log.info("/create [lecture {}]", savedLecture);
        return ResponseEntity.ok(new LectureDto(savedLecture));
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