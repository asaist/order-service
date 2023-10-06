package ru.mcclinics.orderservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mcclinics.orderservice.domain.Discipline;

public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
}
