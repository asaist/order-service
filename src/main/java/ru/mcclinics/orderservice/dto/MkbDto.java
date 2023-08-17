package ru.mcclinics.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
}
