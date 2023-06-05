package ru.mcclinics.orderservice.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mcclinics.orderservice.domain.Series;
import ru.mcclinics.orderservice.service.SeriesService;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/hui")
public class TrackRoyalController {
    private final SeriesService seriesService;
    @GetMapping("/findSeriesByTrack/{id}")
    public List<Series> getSeriesByTrack(@PathVariable("id") Long id) {
        return seriesService.findSeriesByTrackId(id);
    }


}
