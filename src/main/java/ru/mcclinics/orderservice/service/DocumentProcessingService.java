package ru.mcclinics.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.mcclinics.orderservice.domain.OrderDocument;
import ru.mcclinics.orderservice.dto.DocumentDto;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.oauth2.core.OAuth2TokenIntrospectionClaimNames.CLIENT_ID;
import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.CLIENT_SECRET;

@Slf4j(topic = "order-service")
@Service
@RequiredArgsConstructor

public class DocumentProcessingService {

    public ResponseEntity<String> launchProcess(String base64) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(CLIENT_ID, CLIENT_SECRET);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");
        map.add("client_id", "track-samsmu-service");
        map.add("client_secret", "775f3ea5-e6b3-47c3-b4ba-d00aaceb3da7");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("https://sso.samsmu.ru/auth/realms/SAMGMU/protocol/openid-connect/token", request , String.class);

        String responseBody = response.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map1 = mapper.readValue(responseBody, new TypeReference<Map<String,Object>>(){});

        // парсим JSON-строку
        JSONObject jsonObject = new JSONObject(map1);
        String tokenKeyCloak = jsonObject.getAsString("access_token");
        int i =1;

        HttpHeaders headers2 = new HttpHeaders();
//        headers2.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers2.set(HttpHeaders.AUTHORIZATION, "Bearer " + tokenKeyCloak);
        System.out.println(tokenKeyCloak);
        headers2.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        DocumentDto documentDto = new DocumentDto();
        documentDto.setProcessType("114");
        documentDto.setSenderID("62b9220d-6d49-4bf1-bca5-949c1db40025");
        documentDto.setProcessInitDocBody(base64);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("processType", "114");
        params.add("processInitDocBody", base64);
        params.add("senderID", "62b9220d-6d49-4bf1-bca5-949c1db40025");
//        params.add("personID", "defecd0d-521b-4414-84f6-87b8476c3b82");

        HttpEntity<?> entity = new HttpEntity<>(params, headers2);



        ResponseEntity<String> response3 = restTemplate.postForEntity(
                "https://dev.service.samsmu.ru/document-processing/documentProcessing/processToPeople",
                entity,
                String.class
        );
//                HttpEntity<String>  response2 = restTemplate.exchange(
//                "https://dev.service.samsmu.ru/document-processing/documentProcessing/processToPeople",
//                HttpMethod.POST,
//                entity,
//                String.class
//        );
//        ResponseEntity<EmployeeDto[]> response4 = restTemplate.exchange(
//                "https://dev.service.samsmu.ru/users/users/employee",
//                HttpMethod.GET,
//                entity,
//                EmployeeDto[].class
//        );
//        EmployeeDto[] employeeDtos = response4.getBody();
//        List<EmployeeDto> employeeDtoList = Arrays.asList(employeeDtos);
        return (ResponseEntity<String>) response3;
    }


    public void generatePdfJasper(Collection<?> reportData) throws JRException, IOException {

        // Загрузка шаблона отчета Jasper Reports из файла

        Resource resource = new ClassPathResource("/reports/order.jrxml");
        InputStream jasperReportStream = resource.getInputStream();


//        InputStream reportStream = getClass().getResourceAsStream("/reports/report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperReportStream);

        // Получение данных для отчета из базы данных
//        List<ReportData> reportData = reportService.getReportData();

//        String classPath = System.getProperty("user.dir") + "/template/";

        // Заполнение отчета данными
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportData);
        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("classPath", classPath);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        JasperExportManager.exportReportToPdfFile(jasperPrint, "report.pdf");

        // Преобразование отчета в PDF и кодирование его в Base64
        byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
        String base64 = Base64.getEncoder().encodeToString(pdfBytes);
        System.out.println(base64);
        // Отправка PDF в виде Base64 через RestTemplate
        launchProcess(base64);

    }


}
