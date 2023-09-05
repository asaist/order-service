package ru.mcclinics.orderservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDocument {
    private String link;

    public OrderDocument(String link) {
        this.link = link;
    }
}
