package ru.mcclinics.orderservice.dto;

import lombok.*;

import java.io.Serializable;
@Setter
@Getter
@ToString
public class DocumentDto extends EntityDto implements Serializable {
     private String  processType;
     private String  senderID;
     private String  processInitDocBody;
}
