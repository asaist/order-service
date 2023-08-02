package ru.mcclinics.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mcclinics.orderservice.dao.TrackRepository;
import ru.mcclinics.orderservice.domain.Track;


import java.util.List;

@Slf4j(topic = "order-service")
@Service
@RequiredArgsConstructor
public class TrackService {

    private final TrackRepository trackRepository;
    public List<Track> findTracks(){return trackRepository.findAll();}
    public Track findTrackByTrackName(String trackName){return trackRepository.findTrackByTrackName(trackName);}
    public List<Track> findTrackByName(String trackName){return trackRepository.findByTrackNameStartsWithIgnoreCase(trackName);}
    public Track save(Track track) {
        return trackRepository.save(track);
    }
    public void delete(Track track) {
        trackRepository.delete(track);
    }
    public Track findTrackById(Long id){return trackRepository.findTrackById(id);}
}
