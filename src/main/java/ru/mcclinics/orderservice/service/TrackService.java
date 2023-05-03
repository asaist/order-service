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

    public Track create(Track track) {
        return trackRepository.save(track);
    }
}
