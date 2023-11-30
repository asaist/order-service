package ru.mcclinics.orderservice.dto;

import lombok.*;
import ru.mcclinics.orderservice.domain.Lecture;
import ru.mcclinics.orderservice.domain.Track;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LectureDto extends EntityDto implements Serializable {

    private Long id;
    private Long moduleId;
    private Boolean idDb;
    private Boolean executed;
    private String lectureModuleName;
    private String lectureModuleAnnotation;
    private String lectureModuleKeyWords;
    private String daysToFill;
    private String status;
    private String supervisor;
    private String authorsNames;
    private String link;
    private List<AuthorDto> authors;
    private String learnCompetenceOne;
    private String learnCompetenceTwo;
    private String learnCompetenceThree;
    private String learnCompetenceFour;

    public LectureDto(Lecture lecture) {
        this.id = lecture.getId();
        if (lecture.getSeries() != null) {
            this.moduleId = lecture.getSeries().getId();
            this.supervisor = lecture.getSupervisor();
        }
        this.lectureModuleName = lecture.getLectureName();
        this.lectureModuleAnnotation = lecture.getAnnotation();
        this.daysToFill = lecture.getDaysToFill();
//        this.status = lecture.getLectureStatus().name();
        this.learnCompetenceOne = lecture.getLearnCompetenceOne();
        this.learnCompetenceTwo = lecture.getLearnCompetenceTwo();
        this.learnCompetenceThree = lecture.getLearnCompetenceThree();
        this.learnCompetenceFour = lecture.getLearnCompetenceFour();
        this.authorsNames = lecture.getAuthors().stream()
                .map(author -> author.getLastName() + " "
                        + author.getFirstName() + " "
                        + author.getMiddleName())
                .collect(Collectors.joining("; "));
        this.link = "https://dev.track.samsmu.ru/lecture/" + lecture.getId();
    }
}
