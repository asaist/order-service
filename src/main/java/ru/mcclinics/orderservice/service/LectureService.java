package ru.mcclinics.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mcclinics.orderservice.dao.LectureRepository;
import ru.mcclinics.orderservice.domain.Lecture;
import ru.mcclinics.orderservice.domain.Track;


import java.util.List;

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
}
