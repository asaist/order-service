package ru.mcclinics.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mcclinics.orderservice.dao.AuthorRepository;
import ru.mcclinics.orderservice.domain.Author;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j(topic = "order-service")
@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    public List<Author> findAuthors(){
        List<Author> authorList = authorRepository.findAll();
        Collections.sort(authorList, Comparator.comparing(Author::getLastName));
        return authorList;
    }
    public Author findAuthorByGuid(String guid) {return authorRepository.findAuthorByGuid(guid);}

    public Author create(Author author) {
        return authorRepository.save(author);
    }
}
