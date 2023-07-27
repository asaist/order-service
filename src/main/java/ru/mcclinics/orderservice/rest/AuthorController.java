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
                       @RequestParam(value = "passport", required = false) MultipartFile passport,
                       @RequestParam(value = "diploma", required = false) MultipartFile diploma,
                       @RequestParam(value = "diplomaScienceRank", required = false) MultipartFile diplomaScienceRank,
                       @RequestParam(value = "diplomaScienceDegree", required = false) MultipartFile diplomaScienceDegree,
                       @RequestParam(value = "noCriminalRecord", required = false) MultipartFile noCriminalRecord,
                       @RequestParam(value = "healthStatus", required = false) MultipartFile healthStatus,
                       @RequestParam(value = "employmentBook", required = false) MultipartFile employmentBook
    ) throws IOException {
        Author author = new Author();
        author.setAuthorId(Long.valueOf(id));
        String[] strMain = fullName.split(" ");
        author.setLastName(strMain[0]);
        author.setFirstName(strMain[1]);
        author.setMiddleName(strMain[2]);
        Author authorFromDB = service.findAuthorById(author.getAuthorId());


        // Delete existing passport file, if it exists
        if (passport != null && !passport.getOriginalFilename().isEmpty()) {
        String existingPassportFileName = authorFromDB.getPassportPdf();
        if (existingPassportFileName != null) {
            File existingPassportFile = new File(uploadPath + "/" + existingPassportFileName);
            if (existingPassportFile.exists()) {
                existingPassportFile.delete();
                log.info("Existing passport file deleted: {}", existingPassportFileName);
            }
        }

            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + passport.getOriginalFilename();
            passport.transferTo(new File(uploadPath + "/" + resultFileName));
            author.setPassportPdf(resultFileName);
        }

        // Delete existing diploma file, if it exists
        if (diploma != null && !diploma.getOriginalFilename().isEmpty()) {
            String existingDiplomaFileName = authorFromDB.getDiplomaPdf();
            if (existingDiplomaFileName != null) {
                File existingDiplomaFile = new File(uploadPath + "/" + existingDiplomaFileName);
                if (existingDiplomaFile.exists()) {
                    existingDiplomaFile.delete();
                    log.info("Existing diplom file deleted: {}", existingDiplomaFileName);
                }
            }

            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + diploma.getOriginalFilename();
            diploma.transferTo(new File(uploadPath + "/" + resultFileName));
            author.setDiplomaPdf(resultFileName);
        }

        // Delete existing diplomaScienceRank file, if it exists
        if (diplomaScienceRank != null && !diplomaScienceRank.getOriginalFilename().isEmpty()) {
            String existingDiplomaScienceRankFileName = authorFromDB.getDiplomaScienceRankPdf();
            if (existingDiplomaScienceRankFileName != null) {
                File existingDiplomaScienceRankFile = new File(uploadPath + "/" + existingDiplomaScienceRankFileName);
                if (existingDiplomaScienceRankFile.exists()) {
                    existingDiplomaScienceRankFile.delete();
                    log.info("Existing diplomaScienceRank file deleted: {}", existingDiplomaScienceRankFileName);
                }
            }

            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + diplomaScienceRank.getOriginalFilename();
            diplomaScienceRank.transferTo(new File(uploadPath + "/" + resultFileName));
            author.setDiplomaScienceRankPdf(resultFileName);
        }

        // Delete existing diplomaScienceDegree file, if it exists
        if (diplomaScienceDegree != null && !diplomaScienceDegree.getOriginalFilename().isEmpty()) {
            String existingDiplomaScienceDegreeFileName = authorFromDB.getDiplomaScienceRankPdf();
            if (existingDiplomaScienceDegreeFileName != null) {
                File existingDiplomaScienceDegreeFile = new File(uploadPath + "/" + existingDiplomaScienceDegreeFileName);
                if (existingDiplomaScienceDegreeFile.exists()) {
                    existingDiplomaScienceDegreeFile.delete();
                    log.info("Existing diplomaScienceRank file deleted: {}", existingDiplomaScienceDegreeFileName);
                }
            }

            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + diplomaScienceDegree.getOriginalFilename();
            diplomaScienceDegree.transferTo(new File(uploadPath + "/" + resultFileName));
            author.setDiplomaScienceDegreePdf(resultFileName);
        }

        // Delete existing noCriminalRecord file, if it exists
        if (noCriminalRecord != null && !noCriminalRecord.getOriginalFilename().isEmpty()) {
            String existingNoCriminalRecordFileName = authorFromDB.getDiplomaScienceRankPdf();
            if (existingNoCriminalRecordFileName != null) {
                File existingNoCriminalRecordFile = new File(uploadPath + "/" + existingNoCriminalRecordFileName);
                if (existingNoCriminalRecordFile.exists()) {
                    existingNoCriminalRecordFile.delete();
                    log.info("Existing diplomaScienceRank file deleted: {}", existingNoCriminalRecordFileName);
                }
            }

            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + noCriminalRecord.getOriginalFilename();
            noCriminalRecord.transferTo(new File(uploadPath + "/" + resultFileName));
            author.setNoCriminalRecordPdf(resultFileName);
        }


        // Delete existing healthStatus file, if it exists
        if (healthStatus != null && !healthStatus.getOriginalFilename().isEmpty()) {
            String existingHealthStatusFileName = authorFromDB.getDiplomaScienceRankPdf();
            if (existingHealthStatusFileName != null) {
                File existingNoCriminalRecordFile = new File(uploadPath + "/" + existingHealthStatusFileName);
                if (existingNoCriminalRecordFile.exists()) {
                    existingNoCriminalRecordFile.delete();
                    log.info("Existing diplomaScienceRank file deleted: {}", existingHealthStatusFileName);

                }
            }

            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + healthStatus.getOriginalFilename();
            healthStatus.transferTo(new File(uploadPath + "/" + resultFileName));
            author.setHealthStatusPdf(resultFileName);
        }


        // Delete existing employmentBook file, if it exists
        if (employmentBook != null && !employmentBook.getOriginalFilename().isEmpty()) {
            String existingEmploymentBookFileName = authorFromDB.getDiplomaScienceRankPdf();
            if (existingEmploymentBookFileName != null) {
                File existingEmploymentBookFile = new File(uploadPath + "/" + existingEmploymentBookFileName);
                if (existingEmploymentBookFile.exists()) {
                    existingEmploymentBookFile.delete();
                    log.info("Existing diplomaScienceRank file deleted: {}", existingEmploymentBookFileName);

                }
            }

            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + employmentBook.getOriginalFilename();
            employmentBook.transferTo(new File(uploadPath + "/" + resultFileName));
            author.setEmploymentBookPdf(resultFileName);
        }

        log.info("/create [author {}]", author);
        return service.create(author);
    }
}
