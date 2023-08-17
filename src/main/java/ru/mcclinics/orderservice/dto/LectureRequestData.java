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
    private String lectureId;
    private List<AuthorDto> lectureAuthors;
    private String track;
    private String series;
    private String lectureName;
    private String lectureAnnotation;
    private String lectureKeyWords;
    private List<MkbDto> mkbs;
}
