package ru.mcclinics.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessDto extends EntityDto implements Serializable {
    private String processID;
    private String processType;
    private String processState;
    private String processDate;
    private String personSNILS;
    private String personID;
    private String personDivision;
    private String personPosition;
    private String processInitReceiver;
    private String processInitData;
    private String processFinalData;
}
