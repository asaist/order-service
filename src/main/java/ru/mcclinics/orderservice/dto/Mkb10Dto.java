package ru.mcclinics.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Mkb10Dto extends EntityDto implements Serializable {
    private String id;
    private String name;
    @JsonProperty("ext_guid")
    private String extGuid;
    private String readonly;
    private String paramId;
    private String shortDesc;
    private String code;
    @JsonProperty("ref_code")
    private String refCode;

}
