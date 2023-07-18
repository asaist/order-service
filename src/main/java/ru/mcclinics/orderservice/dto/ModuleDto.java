package ru.mcclinics.orderservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Setter
@Getter
@ToString
public class ModuleDto extends EntityDto implements Serializable {
    private String id;
    private String moduleNameModal;
    private String moduleModalAnnotation;
    private String  moduleModalKeyWords;
}
