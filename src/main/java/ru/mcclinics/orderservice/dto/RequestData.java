package ru.mcclinics.orderservice.dto;

import lombok.*;
import ru.mcclinics.orderservice.domain.Mkb;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestData extends EntityDto implements Serializable {
    private String trackId;
    private String seriesId;
    private List<AuthorDto> authors;
    private String university;
    private String trackName;
    private String trackAnnotation;
    private String trackKeyWords;
    private List<ModuleDto> modules;
    private List<LectureDto> lectures;
    private List<MkbDto> mkbs;
    private List<MkbDto> diss;
    private List<MkbDto> locs;

    public RequestData(List<AuthorDto> authors, List<MkbDto> mkbs, List<MkbDto> diss, List<MkbDto> locs) {
        this.authors = authors;
        this.mkbs = mkbs;
        this.diss = diss;
        this.locs = locs;
    }

    public RequestData(List<AuthorDto> authors, List<ModuleDto> modules, List<LectureDto> lectures) {
        this.authors = authors;
        this.modules = modules;
        this.lectures = lectures;
    }

    public RequestData(List<AuthorDto> authors, List<LectureDto> lectures, List<MkbDto> mkbs, String seriesId) {
        this.authors = authors;
        this.lectures = lectures;
        this.mkbs = mkbs;
        this.seriesId = seriesId;
    }

}
