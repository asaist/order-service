package ru.mcclinics.orderservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mcclinics.orderservice.domain.Track;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
}
