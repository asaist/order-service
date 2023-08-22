package ru.mcclinics.orderservice.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class PdfGenerator {
    public String generatePdf(String text) throws DocumentException, FileNotFoundException {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();
        document.add(new Paragraph(text));
        document.close();
        // Конвертация PDF в массив байтов
        byte[] pdfBytes = baos.toByteArray();

        // Преобразование массива байтов в base64
        String base64 = Base64.encodeBytes(pdfBytes);

        System.out.println("PDF в base64:\n" + base64);
        return base64;
    }




}
