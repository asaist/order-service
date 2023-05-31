package ru.mcclinics.orderservice.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mcclinics.orderservice.domain.University;

import java.util.List;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
}
