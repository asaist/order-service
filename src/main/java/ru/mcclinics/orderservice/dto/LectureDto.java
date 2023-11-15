package ru.mcclinics.orderservice.dto;

import lombok.*;
import ru.mcclinics.orderservice.domain.Lecture;
import ru.mcclinics.orderservice.domain.Track;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LectureDto extends EntityDto implements Serializable {

    private Long id;
    private Long moduleId;
    private Boolean idDb;
    private String lectureModuleName;
    private String lectureModuleAnnotation;
    private String lectureModuleKeyWords;
    private String daysToFill;
    private List<AuthorDto> authors;
    private String status;
    private String supervisor;
    private String learnCompetenceOne;
    private String learnCompetenceTwo;
    private String learnCompetenceThree;
    private String learnCompetenceFour;

    public LectureDto(Lecture lecture) {
        this.id = lecture.getId();
        if (lecture.getSeries() != null) {
            this.moduleId = lecture.getSeries().getId();
            this.supervisor = lecture.getSeries().getSupervisor1();
        }
        this.lectureModuleName = lecture.getLectureName();
        this.lectureModuleAnnotation = lecture.getAnnotation();
        this.daysToFill = lecture.getDaysToFill();
        this.status = lecture.getLectureStatus().name();
        this.learnCompetenceOne = lecture.getLearnCompetenceOne();
        this.learnCompetenceTwo = lecture.getLearnCompetenceTwo();
        this.learnCompetenceThree = lecture.getLearnCompetenceThree();
        this.learnCompetenceFour = lecture.getLearnCompetenceFour();
    }
}
