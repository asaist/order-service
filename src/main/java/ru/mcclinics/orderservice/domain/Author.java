package ru.mcclinics.orderservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import ru.mcclinics.orderservice.dto.EmployeeDto;

import java.util.HashSet;
import java.util.Set;

@Table(name = "author")
@Entity
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    @JsonProperty("id")
    private Long authorId;
    @JsonProperty("lastName")
    @Column(name = "last_name")
    private String lastName;
    @JsonProperty("firstName")
    @Column(name = "first_name")
    private String firstName;
    @JsonProperty("middleName")
    @Column(name = "middle_name")
    private String middleName;
    private String guid;
    @Column(name = "academic_degree_name")
    private String academicDegreeName;
    @JsonIgnore
    @ManyToMany(mappedBy = "authors")
    private Set<Track> tracks = new HashSet<>();

    public Author(String lastName) {
        this.lastName = lastName;
    }

    public Author() {
    }

    public Author(EmployeeDto employeeDto) {
        this.guid = employeeDto.getEmployeeGuid();
        this.lastName= employeeDto.getLastName();
        this.firstName = employeeDto.getFirstName();
        this.middleName = employeeDto.getPatronymicName();
    }
}
