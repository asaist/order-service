package ru.mcclinics.orderservice.domain;

import jakarta.persistence.AttributeConverter;

public class TrackStatusConverter implements AttributeConverter<TrackStatus, String> {
    @Override
    public String convertToDatabaseColumn(TrackStatus trackStatus) {
        return trackStatus.name();
    }

    @Override
    public TrackStatus convertToEntityAttribute(String dbData) {
        return TrackStatus.valueOf(dbData);
    }
}
