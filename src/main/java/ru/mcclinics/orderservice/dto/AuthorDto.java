package ru.mcclinics.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import ru.mcclinics.orderservice.domain.Author;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class AuthorDto extends EntityDto implements Serializable {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("fullName")
    private String fullName;
    @JsonProperty("degree")
    private String degree;
    @JsonProperty("isSupervisor")
    private Boolean isSupervisor;
    @JsonProperty("passport")
    private String passport;
    @JsonProperty("diploma")
    private String diploma;
    @JsonProperty("diplomaScienceRank")
    private String diplomaScienceRank;
    @JsonProperty("diplomaScienceDegree")
    private String diplomaScienceDegree;
    @JsonProperty("noCriminalRecord")
    private String noCriminalRecord;
    @JsonProperty("healthStatus")
    private String healthStatus;
    @JsonProperty("employmentBook")
    private String employmentBook;

    public AuthorDto(Author author) {
        this.id = author.getAuthorId();
        this.fullName = author.getLastName() + " " + author.getFirstName() + " " + author.getMiddleName();
        this.degree = author.getAcademicDegreeName();
        this.passport = author.getPassportPdf();
        this.diploma = author.getDiplomaPdf();
        this.diplomaScienceRank = author.getDiplomaScienceRankPdf();
        this.diplomaScienceDegree = author.getDiplomaScienceDegreePdf();
        this.noCriminalRecord = author.getNoCriminalRecordPdf();
        this.healthStatus = author.getHealthStatusPdf();
        this.employmentBook = author.getEmploymentBookPdf();
    }
}
