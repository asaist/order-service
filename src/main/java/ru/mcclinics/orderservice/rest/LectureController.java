package ru.mcclinics.orderservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mcclinics.orderservice.domain.Lecture;
import ru.mcclinics.orderservice.domain.Track;
import ru.mcclinics.orderservice.domain.User;
import ru.mcclinics.orderservice.service.LectureService;
import ru.mcclinics.orderservice.service.TrackService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;
    private final TrackService trackService;
//    private final SeriesService seriesService;

    @GetMapping
    public String lectureList(@RequestParam(required = false, defaultValue = "") String filter, Model model){
        List<Lecture> lectures;
        if (filter !=null && !filter.isEmpty()){
            lectures = lectureService.findLectureByName(filter);
        } else {
            lectures = lectureService.findLectures();
        }
        model.addAttribute("lectures", lectures);
        model.addAttribute("tracks", trackService.findTracks());
        model.addAttribute("filter", filter);
        return "lectureList";
    }

    @PostMapping
    public String addLecture(
            @AuthenticationPrincipal User user,
            @RequestParam String lectureName,
            @RequestParam String annotation,
            @RequestParam String videoReference,
            @RequestParam Track track,
            Model model
    ){

        Lecture lecture = new Lecture(lectureName, user, annotation,  videoReference, track);
        lecture.setCreateDate(LocalDateTime.now());
        lectureService.save(lecture);
        Iterable<Lecture> lectures = lectureService.findLectures();
        model.addAttribute("lectures", lectures);
        return "redirect:/lectures";
    }

}