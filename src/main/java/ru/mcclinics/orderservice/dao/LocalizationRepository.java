package ru.mcclinics.orderservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mcclinics.orderservice.domain.Localization;

public interface LocalizationRepository extends JpaRepository<Localization, Long> {
}
