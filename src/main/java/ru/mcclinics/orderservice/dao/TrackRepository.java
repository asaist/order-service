package ru.mcclinics.orderservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.mcclinics.orderservice.domain.Author;
import ru.mcclinics.orderservice.domain.Track;

import java.util.List;
import java.util.Set;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
    List<Track> findByTrackNameStartsWithIgnoreCase(String trackName);
    Track findTrackByTrackName(String trackName);
    Track findTrackById(Long id);
    @Query("SELECT t.authors FROM Track t WHERE t.id = :trackId")
    Set<Author> findAuthorsById(@Param("trackId")Long id);
}
