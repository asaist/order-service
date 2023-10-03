package ru.mcclinics.orderservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDocument {
    private String name;
    private String annotation;
    private String keywords;
    private String supervisor;
    private String authors;
    private String link;

    public OrderDocument(String link) {
        this.link = link;
    }

    public OrderDocument(String name, String annotation, String keywords, String supervisor, String authors, String link) {
        this.name = name;
        this.annotation = annotation;
        this.keywords = keywords;
        this.supervisor = supervisor;
        this.authors = authors;
        this.link = link;
    }

    public OrderDocument(Lecture lecture) {
        this.link = "https://dev.track.samsmu.ru/lecture/" + lecture.getId();
        this.name = lecture.getLectureName();
        this.annotation = lecture.getAnnotation();
        this.keywords = lecture.getKeyWords();
        String authorsNames = lecture.getAuthors().stream()
                .map(author -> author.getLastName() + " " + author.getFirstName() + " " + author.getMiddleName())
                .collect(Collectors.joining("; "));
        this.authors = authorsNames;
    }
}
