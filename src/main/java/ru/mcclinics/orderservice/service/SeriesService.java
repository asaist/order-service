package ru.mcclinics.orderservice.service;

import org.springframework.stereotype.Service;
import ru.mcclinics.orderservice.dao.SeriesRepository;
import ru.mcclinics.orderservice.domain.Author;
import ru.mcclinics.orderservice.domain.Series;
import ru.mcclinics.orderservice.domain.Track;

import java.util.List;
import java.util.Set;

@Service
public class SeriesService {
    private final SeriesRepository seriesRepository;
    public SeriesService(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }
    public Series save(Series series){
        return seriesRepository.save(series);
    }
    public List<Series> findSeriesByName(String name) { return seriesRepository.findSeriesBySeriesNameStartingWith(name); }
    public List<Series> findSeries(){return seriesRepository.findAll();}
    public List<Series> findSeriesByTrackId(Long id){return seriesRepository.findSeriesByTrackId(id);}
    public List<Series> findSeriesByTrack(Track track){return seriesRepository.findSeriesByTrack(track);}
    public List<Series> saveAll(List<Series> series) {return seriesRepository.saveAll(series);}
    public Series findSeriesById(Long id){return seriesRepository.findSeriesById(id);}
    public Set<Author> findAuthorsById(Long id){return seriesRepository.findAuthorsById(id);}
}
