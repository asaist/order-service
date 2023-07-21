package ru.mcclinics.orderservice.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mcclinics.orderservice.domain.Author;
import ru.mcclinics.orderservice.domain.Track;
import ru.mcclinics.orderservice.domain.User;
import ru.mcclinics.orderservice.service.AuthorService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j(topic = "order-service")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/author")
public class AuthorController {

    @Value("${files.upload.baseDir}")
    private String uploadPath;

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
    @PostMapping("/docs")
    public Author docs(@RequestParam("id") String id,
                       @RequestParam("fullName") String fullName,
                       @RequestParam("passport") MultipartFile passport
    ) throws IOException {
        Author author = new Author();
        author.setAuthorId(Long.valueOf(id));
        String[] strMain = fullName.split(" ");
        author.setLastName(strMain[0]);
        author.setFirstName(strMain[1]);
        author.setMiddleName(strMain[2]);
        Author authorFromDB = service.findAuthorById(author.getAuthorId());
        // Delete existing passport file, if it exists
        String existingPassportFileName = authorFromDB.getPassportPdf();
        if (existingPassportFileName != null) {
            File existingPassportFile = new File(uploadPath + "/" + existingPassportFileName);
            if (existingPassportFile.exists()) {
                existingPassportFile.delete();
                log.info("Existing passport file deleted: {}", existingPassportFileName);
            }
        }
        if (passport != null && !passport.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + passport.getOriginalFilename();
            passport.transferTo(new File(uploadPath + "/" + resultFileName));
            author.setPassportPdf(resultFileName);
        }
        log.info("/create [author {}]", author);
        return service.create(author);
    }

}
