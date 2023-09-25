package ru.mcclinics.orderservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

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
}
