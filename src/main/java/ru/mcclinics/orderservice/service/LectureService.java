package ru.mcclinics.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mcclinics.orderservice.dao.LectureRepository;
import ru.mcclinics.orderservice.domain.Lecture;


import java.util.List;
import java.util.Optional;

@Slf4j(topic = "order-service")
@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    @Transactional(readOnly = true)
    public List<Lecture> findLectures(){return lectureRepository.findAll();}

    public Lecture save(Lecture lecture) {return lectureRepository.save(lecture);}

    public List<Lecture> findLectureByName(String lectureName){
        return lectureRepository.findByLectureNameContainsIgnoreCase(lectureName);
    }

    public List<Lecture> saveAll(List<Lecture> lectures) {return lectureRepository.saveAll(lectures);}
    public Lecture findLectureById(Long id){return lectureRepository.findLectureById(id);}
    public List<Lecture> findLectureByTrackId(Long id) {return lectureRepository.findLectureByTrackId(id);}
}
