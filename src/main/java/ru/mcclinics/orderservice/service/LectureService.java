package ru.mcclinics.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mcclinics.orderservice.dao.LectureRepository;
import ru.mcclinics.orderservice.domain.Lecture;


import java.util.List;

@Slf4j(topic = "order-service")
@Service
@RequiredArgsConstructor
public class LectureService {
    @Autowired
    private LectureRepository lectureRepository;
    @Transactional(readOnly = true)
    public List<Lecture> findLectures(){return lectureRepository.findAll();}
}
