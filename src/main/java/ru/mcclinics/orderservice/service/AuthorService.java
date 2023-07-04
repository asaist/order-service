package ru.mcclinics.orderservice.service;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<Author> findAuthors(int offset, int limit) {
        Pageable pageable = PageRequest.of(offset / limit, limit);
        Page<Author> page = authorRepository.findAll(pageable);
        return page.getContent();
    }
    public List<Author> findAuthors(String query, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize); // номер страницы начинается с 1
        if (StringUtils.isBlank(query)) {
            return authorRepository.findAll(pageable).getContent();
        } else {
            return authorRepository.findByFullNameContainingIgnoreCase(query, pageable).getContent();
        }
    }
    public List<Author> findAuthorsByListId(List<Long> authors){return authorRepository.findAuthorByAuthorIdIn(authors);}
    public Author findAuthorByGuid(String guid) {return authorRepository.findAuthorByGuid(guid);}
    public Author findAuthorById(Long id) {return authorRepository.findAuthorByAuthorId(id);}

    public Author create(Author author) {
        return authorRepository.save(author);
    }
}
