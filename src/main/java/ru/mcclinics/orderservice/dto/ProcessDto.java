package ru.mcclinics.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class  ProcessDto extends EntityDto implements Serializable {
    @JsonProperty("processID")
    private String processID;
    @JsonProperty("processType")
    private String processType;
    @JsonProperty("processState")
    private String processState;
    @JsonProperty("processDate")
    private String processDate;
    @JsonProperty("personSNILS")
    private String personSNILS;
    @JsonProperty("personID")
    private String personID;
//    @JsonProperty("personDivision")
//    private String personDivision;
//    @JsonProperty("personPosition")
//    private String personPosition;
//    @JsonProperty("processInitReceiver")
//    private String processInitReceiver;
//    @JsonProperty("processInitData")
//    private String processInitData;
//    @JsonProperty("processInitData")
//    private String processFinalData;
}
