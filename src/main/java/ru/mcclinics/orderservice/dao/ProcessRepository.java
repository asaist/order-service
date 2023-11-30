package ru.mcclinics.orderservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mcclinics.orderservice.domain.Lecture;
import ru.mcclinics.orderservice.domain.Process;

import java.util.List;

@Repository
public interface ProcessRepository extends JpaRepository<Process, Long> {
    List<Process> findByProcessState(int processState);
    List<Process> findProcessByLectureId(Long lectureId);
}
