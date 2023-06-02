package ru.mcclinics.orderservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mcclinics.orderservice.domain.KeyWord;

@Repository
public interface KeyWordRepository extends JpaRepository<KeyWord, Long> {
}
