package ru.mcclinics.orderservice.rest;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public PdfController(PdfGenerator pdfGenerator) {
        this.pdfGenerator = pdfGenerator;
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
