package ru.mcclinics.orderservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class AuthorDto extends EntityDto implements Serializable {
    private String id;
    private String fullName;
    private String degree;
    private Boolean  isSupervisor;
//    private StandardMultipartHttpServletRequest passport;
}
