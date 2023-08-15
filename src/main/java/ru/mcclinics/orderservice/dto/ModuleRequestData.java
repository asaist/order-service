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
public class ModuleRequestData extends EntityDto implements Serializable {
    private String seriesId;
    private List<AuthorDto> moduleAuthors;
    private List<Mkb> mkbs;
    private String track;
    private String seriesName;
    private String seriesAnnotation;
    private String seriesKeyWords;
    private List<LectureDto> lectures;
}
