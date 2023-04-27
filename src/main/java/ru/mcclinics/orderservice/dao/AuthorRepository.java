package ru.mcclinics.orderservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mcclinics.orderservice.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
