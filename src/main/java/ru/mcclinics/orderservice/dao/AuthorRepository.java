package ru.mcclinics.orderservice.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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
    Author findAuthorByAuthorId(Long id);
    List<Author> findAuthorByAuthorIdIn(List<Long> authors);
    @Query("SELECT a FROM Author a WHERE LOWER(CONCAT(a.lastName, ' ', a.firstName)) LIKE %:name%")
    Slice<Author> findByFullNameContainingIgnoreCase(@Param("name")String query, Pageable pageable);




//    Slice<Author> findByNameContainingIgnoreCase(String query, Pageable pageable);
}
