package ru.mcclinics.orderservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.mcclinics.orderservice.dto.ProcessDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@Table(name = "process")
@Entity
public class Process {
    @Id
    @Column(name = "process_id")
    private Long id;
    @Column(name = "process_type")
    private Long processType;
    @Column(name = "process_state")
    private Long processState;
    @Column(name = "process_date")
    private LocalDate processDate;
    @Column(name = "person_snils")
    private String personSnils;
    @Column(name = "person_guid")
    private String personGuid;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id")
    private Series series;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    public Process() {
    }

    public Process(ProcessDto processDto) {
        this.id = Long.parseLong(processDto.getProcessID());
        this.processType = Long.parseLong(processDto.getProcessType());
        this.processState = Long.parseLong(processDto.getProcessState());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.processDate = LocalDate.parse(processDto.getProcessDate(), formatter);
        this.personSnils = processDto.getPersonSNILS();
        this.personGuid = processDto.getPersonID();
    }
}
