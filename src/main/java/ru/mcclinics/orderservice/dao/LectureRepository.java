package ru.mcclinics.orderservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.mcclinics.orderservice.domain.Author;
import ru.mcclinics.orderservice.domain.Lecture;
import ru.mcclinics.orderservice.domain.Track;

import java.util.List;
import java.util.Set;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findByLectureNameContainsIgnoreCase(String lectureName);
    Lecture findLectureById(Long id);
    List<Lecture> findLectureByTrackId(Long id);
    List<Lecture> findLectureBySeriesId(Long id);
    @Query("SELECT t.authors FROM Lecture t WHERE t.id = :lectureId")
    Set<Author> findAuthorsById(@Param("lectureId")Long id);
}
