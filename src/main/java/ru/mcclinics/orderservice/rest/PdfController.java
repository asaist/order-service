package ru.mcclinics.orderservice.rest;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mcclinics.orderservice.domain.Author;
import ru.mcclinics.orderservice.service.AuthorService;
import ru.mcclinics.orderservice.service.PdfGenerator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class PdfController {
    @Value("${files.upload.baseDir}")
    private String uploadPath;
    private final PdfGenerator pdfGenerator;
    private final AuthorService authorService;
    public PdfController(PdfGenerator pdfGenerator, AuthorService authorService) {
        this.pdfGenerator = pdfGenerator;
        this.authorService = authorService;
    }

    @GetMapping("/pdf/{filename}")
    public ResponseEntity<Resource> getPdf(@PathVariable String filename) throws IOException {
        Path filePath = Paths.get(uploadPath + filename);
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(filePath));
        HttpHeaders headers = new HttpHeaders();
        filename = URLEncoder.encode(filename, "UTF-8");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    @DeleteMapping("/pdf/{id}/{document}/{filename}")
    public ResponseEntity<String> deletePdf(@PathVariable String id,
                                            @PathVariable String document,
                                            @PathVariable String filename
    ) throws IOException {
        Author author = authorService.findAuthorById(Long.parseLong(id));
        if (document.equals("passportDB")){
            author.setPassportPdf(null);
        }
        Path filePath = Paths.get(uploadPath + filename);
        Files.delete(filePath);
        authorService.create(author);
        return ResponseEntity.ok("файл удален");
    }

    @GetMapping("/generate-pdf")
    public String generatePdf(@RequestParam String text,
                              @RequestParam(defaultValue = "output.pdf") String filename) {
        try {
            String greeting = "Вам направляется на согласование : ";
            pdfGenerator.generatePdf(greeting + text);
            return "PDF generated successfully!";
        } catch (DocumentException e) {
            e.printStackTrace();
            return "Failed to generate PDF.";
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
