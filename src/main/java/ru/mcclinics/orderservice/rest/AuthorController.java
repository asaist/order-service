package ru.mcclinics.orderservice.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.mcclinics.orderservice.domain.Author;
import ru.mcclinics.orderservice.service.AuthorService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Slf4j(topic = "order-service")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/author")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/authors")
    @ResponseStatus(OK)
    public List<Author> getAllTracks() {
        log.info("/authors");
        List<Author> authors = authorService.findAuthors();
        return authors;

    }
}
