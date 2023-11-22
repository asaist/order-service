package ru.mcclinics.orderservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.mcclinics.orderservice.domain.Mkb;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
public class LectureRequestData extends EntityDto implements Serializable {
    private String id;
    private List<AuthorDto> lectureAuthors;
    private String track;
    private String series;
    private String lectureName;
    private String lectureAnnotation;
    private String lectureKeyWords;
    private List<MkbDto> mkbs;
    private List<MkbDto> diss;
    private List<MkbDto> locs;
    private String learnCompetenceOne;
    private String learnCompetenceTwo;
    private String learnCompetenceThree;
    private String learnCompetenceFour;
    private String daysToFill;
    private String status;
}
