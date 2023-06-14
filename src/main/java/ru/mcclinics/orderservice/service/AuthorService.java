package ru.mcclinics.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mcclinics.orderservice.dao.AuthorRepository;
import ru.mcclinics.orderservice.domain.Author;

import java.util.List;

@Slf4j(topic = "order-service")
@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    public List<Author> findAuthors(){return authorRepository.findAll();}
    public Author findAuthorByGuid(String guid) {return authorRepository.findAuthorByGuid(guid);}

    public Author create(Author author) {
        return authorRepository.save(author);
    }
}
