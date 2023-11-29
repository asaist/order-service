package ru.mcclinics.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mcclinics.orderservice.dao.LectureRepository;
import ru.mcclinics.orderservice.dao.ProcessRepository;
import ru.mcclinics.orderservice.domain.Lecture;
import ru.mcclinics.orderservice.domain.Process;
import ru.mcclinics.orderservice.domain.Series;

import java.util.List;

@Slf4j(topic = "order-service")
@Service
@RequiredArgsConstructor
public class ProcessService {
    private final ProcessRepository processRepository;
    public Process save(Process process){return processRepository.save(process);}
    public List<Process> findActiveProcesses(){return processRepository.findByProcessState(1);}

}
