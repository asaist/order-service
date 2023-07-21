package ru.mcclinics.orderservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
public class RequestData extends EntityDto implements Serializable {
    private String trackId;
    private List<AuthorDto> authors;
    private String university;
    private String trackName;
    private String trackAnnotation;
    private String trackKeyWords;
    private List<ModuleDto> modules;
    private List<LectureDto> lectures;
//    private MultipartFile passportFile;
}
