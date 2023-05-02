package ru.mcclinics.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mcclinics.orderservice.dao.AuthorRepository;
import ru.mcclinics.orderservice.domain.Author;

import java.util.List;


@Slf4j(topic = "order-service")
@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    @Transactional(readOnly = true)
    public List<Author> findAuthors(){return authorRepository.findAll();}
}
