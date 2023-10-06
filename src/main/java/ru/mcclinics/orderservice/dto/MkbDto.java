package ru.mcclinics.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mcclinics.orderservice.domain.Discipline;
import ru.mcclinics.orderservice.domain.Localization;
import ru.mcclinics.orderservice.domain.Mkb;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MkbDto extends EntityDto implements Serializable {
    private Long id;
    private String value;
    private String track;
    private String series;
    private String lecture;

    public MkbDto(Mkb mkb) {
        this.id = mkb.getId();
        this.value = mkb.getValue();
    }

    public MkbDto(Discipline dis) {
        this.id = dis.getId();
        this.value = dis.getValue();
    }

    public MkbDto(Localization loc) {
        this.id = loc.getId();
        this.value = loc.getValue();
    }

}
