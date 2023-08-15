package ru.mcclinics.orderservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mcclinics.orderservice.domain.Mkb;

public interface MkbRepository extends JpaRepository<Mkb, Long> {
}
