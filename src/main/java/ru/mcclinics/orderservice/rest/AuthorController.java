package ru.mcclinics.orderservice.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.mcclinics.orderservice.domain.Author;
import ru.mcclinics.orderservice.domain.Track;
import ru.mcclinics.orderservice.service.AuthorService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j(topic = "order-service")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/author")
public class AuthorController {
    private final AuthorService service;

    @GetMapping("/authors")
    @ResponseStatus(OK)
    public List<Author> getAllTracks() {
        log.info("/authors");
        List<Author> authors = service.findAuthors();
        return authors;
    }
    @PostMapping
    public Author create(@RequestBody Author author){
        log.info("/create [author {}]", author);
        return service.create(author);
    }


}
