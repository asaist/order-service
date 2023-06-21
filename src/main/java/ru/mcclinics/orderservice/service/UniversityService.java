package ru.mcclinics.orderservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.mcclinics.orderservice.dao.UniversityRepository;
import ru.mcclinics.orderservice.domain.University;

import java.util.List;

@Service
public class UniversityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UniversityService.class);
    private final UniversityRepository universityRepository;

    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }
    public List<University> getUniversityList(){return universityRepository.findAll();}
    public University getUniversityById(Long id){return universityRepository.findUniversityByUniversityId(id);}
}
