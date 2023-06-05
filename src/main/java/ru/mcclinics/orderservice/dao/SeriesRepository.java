package ru.mcclinics.orderservice.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mcclinics.orderservice.domain.Series;
import ru.mcclinics.orderservice.domain.Track;

import java.util.List;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    List<Series> findSeriesByTrack(Track track);
    List<Series> findSeriesByTrackId(Long id);
}
