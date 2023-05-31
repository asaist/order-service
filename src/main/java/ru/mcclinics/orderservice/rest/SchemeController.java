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

@Controller
@RequiredArgsConstructor
public class SchemeController {
    private final TrackService trackService;
    private final UniversityService universityService;
    private final LectureService lectureService;

    @GetMapping("/scheme")
    public String main(Model model) {
        Iterable<Track> tracks = trackService.findTracks();
        Iterable<University> universities = universityService.getUniversityList();
        model.addAttribute("tracks", tracks);
        model.addAttribute("universities", universities);
        return "scheme";
    }
}
