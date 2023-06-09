package ru.mcclinics.orderservice.domain;

import jakarta.persistence.*;
import lombok.Data;
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

    public Author(String lastName) {
        this.lastName = lastName;
    }

    public Author() {
    }
}
