package ru.mcclinics.orderservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mcclinics.orderservice.domain.Track;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
    List<Track> findByTrackNameStartsWithIgnoreCase(String trackName);
    Track findTrackByTrackName(String trackName);
    Track findTrackById(Long id);
}
