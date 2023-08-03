package ru.mcclinics.orderservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class EmployeeDto extends EntityDto implements Serializable {
    private String employeeGuid;
    private String firstName;
    private String lastName;
    private String patronymicName;
}
