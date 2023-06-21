package ru.mcclinics.orderservice.domain;

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
    private Long authorId;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    private String guid;
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
