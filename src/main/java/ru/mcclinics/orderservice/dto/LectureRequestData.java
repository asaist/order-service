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
    private LectureDto lecture;
    private List<MkbDto> mkbs;
    private List<MkbDto> diss;
    private List<MkbDto> locs;
}
