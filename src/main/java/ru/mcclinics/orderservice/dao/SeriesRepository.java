package ru.mcclinics.orderservice.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.mcclinics.orderservice.domain.Author;
import ru.mcclinics.orderservice.domain.Series;
import ru.mcclinics.orderservice.domain.Track;

import java.util.List;
import java.util.Set;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    List<Series> findSeriesByTrack(Track track);
    List<Series> findSeriesByTrackId(Long id);
    List<Series> findSeriesBySeriesNameStartingWith(String name);
    Series findSeriesById(Long id);
    @Query("SELECT t.authors FROM Series t WHERE t.id = :seriesId")
    Set<Author> findAuthorsById(@Param("seriesId")Long id);
}
