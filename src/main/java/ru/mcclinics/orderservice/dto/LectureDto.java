package ru.mcclinics.orderservice.dto;

import lombok.*;
import ru.mcclinics.orderservice.domain.Lecture;
import ru.mcclinics.orderservice.domain.Track;

import java.io.Serializable;
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LectureDto extends EntityDto implements Serializable {
    private String id;
    private String moduleId;
    private String lectureModuleName;
    private String lectureModuleAnnotation;
    private String lectureModuleKeyWords;

    public LectureDto(Lecture lecture) {
        this.id = String.valueOf(lecture.getId());

    }
}
