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

    @GetMapping("/authors1")
    @ResponseStatus(OK)
    public List<Author> getAllAuthors(@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        log.info("/authors");
        List<Author> authors = service.findAuthors(offset, limit);
        return authors;
    }
    @RequestMapping(value = "/authors2", method = RequestMethod.GET)
    public List<Author> getAuthors(@RequestParam("q") String query,
                                   @RequestParam("page") int page) {
//        SearchRequest searchRequest = new SearchRequest(query, page);
        List<Author> authors = service.findAuthors();
        return authors;
    }
    @GetMapping("/authors")
    public List<Author> getAllAuthors(@RequestParam(value = "q", required = false) String query,
                                      @RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {

         log.info("/authors");
        List<Author> authors = service.findAuthors(query, page, pageSize);
        return authors;
    }
    @PostMapping
    public Author create(@RequestBody Author author){
        log.info("/create [author {}]", author);
        return service.create(author);
    }


}
