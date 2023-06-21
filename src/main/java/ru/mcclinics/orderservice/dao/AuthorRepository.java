package ru.mcclinics.orderservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.mcclinics.orderservice.domain.Author;

import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByLastNameStartsWithIgnoreCase(String lastName);
    Author findAuthorByGuid(String guid);
    List<Author> findAuthorByAuthorIdIn(List<Long> authors);
}
