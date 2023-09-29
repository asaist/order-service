package ru.mcclinics.orderservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mcclinics.orderservice.domain.Shape;


public interface ShapeRepository extends JpaRepository<Shape, Long> {
}
