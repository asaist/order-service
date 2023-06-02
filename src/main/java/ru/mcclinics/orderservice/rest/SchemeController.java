package ru.mcclinics.orderservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mcclinics.orderservice.domain.Track;
import ru.mcclinics.orderservice.domain.University;
import ru.mcclinics.orderservice.service.LectureService;
import ru.mcclinics.orderservice.service.TrackService;
import ru.mcclinics.orderservice.service.UniversityService;
import ru.mcclinics.orderservice.service.UserService;

@Controller
@RequiredArgsConstructor
public class SchemeController {
    private final TrackService trackService;
    private final UniversityService universityService;
    private final LectureService lectureService;
    private final UserService userService;

    @GetMapping("/")
    public String main(Model model) {

        model.addAttribute("tracks", trackService.findTracks());
        model.addAttribute("universities", universityService.getUniversityList());
        model.addAttribute("lectures", lectureService.findLectures());
        model.addAttribute("users", userService.findUsers());
        return "scheme";
    }
}
