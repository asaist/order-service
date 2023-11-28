package ru.mcclinics.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.sf.jasperreports.engine.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.mcclinics.orderservice.domain.Lecture;
import ru.mcclinics.orderservice.domain.Process;
import ru.mcclinics.orderservice.domain.Series;
import ru.mcclinics.orderservice.dto.DocumentDto;
import ru.mcclinics.orderservice.dto.LectureDto;
import ru.mcclinics.orderservice.dto.ModuleDto;
import ru.mcclinics.orderservice.dto.ProcessDto;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.springframework.security.oauth2.core.OAuth2TokenIntrospectionClaimNames.CLIENT_ID;
import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.CLIENT_SECRET;

@Slf4j(topic = "order-service")
@Service
@RequiredArgsConstructor

public class DocumentProcessingService {

    public ProcessDto launchProcess(String base64, String processType, String supervisorGuid, String executorGuid, String initDocType) throws JsonProcessingException {
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
        params.add("processType", processType);
        params.add("processInitDocBody", base64);
        params.add("senderID", supervisorGuid);
        params.add("initDocType", initDocType);
        if (executorGuid != null){
            params.add("personID", executorGuid);
//            params.add("personDivision", "00ЗК-0190");
//            params.add("personPosition", "Главный инженер-программист");
        }

        HttpEntity<?> entity = new HttpEntity<>(params, headers2);



        ResponseEntity<ProcessDto> response3 = restTemplate.postForEntity(
                "https://dev.service.samsmu.ru/document-processing/documentProcessing/processToPeople",
                entity,
                ProcessDto.class
        );
        System.out.println(response3);

        ProcessDto processDto = response3.getBody();
//        List<EmployeeDto> employeeDtoList = Arrays.asList(employeeDtos);
        return processDto;
    }

    public Process generatePdfForExecution(LectureDto lecture, String processType,
                                        String supervisorGuid, String executorGuid,
                                        String initDocType)
            throws JRException, IOException {
        String base64 = null;

        try {
            Map<String, Object> model = new HashMap<>();
            model.put("lecture", lecture);

            // Создание объекта конфигурации FreeMarker
            Configuration configuration = new Configuration();

            // Установка пути к шаблонам (директория, где находятся .ftlh файлы)
            configuration.setClassForTemplateLoading(FreemarkerUtils.class, "/templates");
            configuration.setDefaultEncoding("UTF-8");
            configuration.setObjectWrapper(new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_31).build());
            // Загрузка и компиляция шаблона
            Template template = configuration.getTemplate("execute_course.ftlh", StandardCharsets.UTF_8.name());

            // Отрисовка шаблона с данными
            StringWriter writer = new StringWriter();
            template.process(model, writer);
            String renderedHtmlContent = writer.toString();

            System.out.println("FTLH -> HTML: " + renderedHtmlContent);

            String fileName = "output.html";
            try (PrintWriter writer1 = new PrintWriter(fileName, "UTF-8")) {
                writer1.print(renderedHtmlContent);
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            // Кодирование HTML-документа в формат Base64
            base64 = Base64.getEncoder().encodeToString(renderedHtmlContent.getBytes(StandardCharsets.UTF_8));

            System.out.println(base64);
        } catch (IOException | TemplateException e) {
            // Обработка исключения, если файл не найден, возникла ошибка чтения или отрисовки шаблона
            e.printStackTrace();
        }

        // Отправка PDF в виде Base64 через RestTemplate
        ProcessDto processDto = launchProcess(base64, processType, supervisorGuid, executorGuid, initDocType);
        Process process = new Process(processDto);
        return process;
    }


    public void generatePdfForApprove(ModuleDto moduleDto, String processType,
                                      String supervisorGuid, String executorGuid,
                                      String link, String initDocType, List<LectureDto> lectureDtos)
            throws IOException {
        String base64 = null;
        try {
            Map<String, Object> model = new HashMap<>();
            model.put("moduleDto", moduleDto);
            model.put("lectures", lectureDtos);

            // Создание объекта конфигурации FreeMarker
            Configuration configuration = new Configuration();

            // Установка пути к шаблонам (директория, где находятся .ftlh файлы)
            configuration.setClassForTemplateLoading(FreemarkerUtils.class, "/templates");
            configuration.setDefaultEncoding("UTF-8");
            configuration.setObjectWrapper(new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_31).build());
            // Загрузка и компиляция шаблона
            Template template = configuration.getTemplate("annotation_to_track.ftlh", StandardCharsets.UTF_8.name());

            // Отрисовка шаблона с данными
            StringWriter writer = new StringWriter();
            template.process(model, writer);
            String renderedHtmlContent = writer.toString();

            System.out.println("FTLH -> HTML: " + renderedHtmlContent);

            String fileName = "output.html";
            try (PrintWriter writer1 = new PrintWriter(fileName, "UTF-8")) {
                writer1.print(renderedHtmlContent);
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            // Кодирование HTML-документа в формат Base64
            base64 = Base64.getEncoder().encodeToString(renderedHtmlContent.getBytes(StandardCharsets.UTF_8));

            System.out.println(base64);
        } catch (IOException | TemplateException e) {
            // Обработка исключения, если файл не найден, возникла ошибка чтения или отрисовки шаблона
            e.printStackTrace();
        }

        // Отправка PDF в виде Base64 через RestTemplate
        launchProcess(base64, processType, supervisorGuid, executorGuid, initDocType);


    }

    public void testFreeMarker() throws Exception {

        Series series = new Series();
        series.setSeriesName("Your Series Name");
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("series", series );
        StringWriter out = new StringWriter();

        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(FreemarkerUtils.class, "/templates" );
        cfg.setObjectWrapper( new DefaultObjectWrapper());
        Template temp = cfg.getTemplate( "annotation_to_track.ftlh" );
        temp.process(root, out);

        System.out.println(out.getBuffer().toString());
    }

    //-----------------------------------JasperReports---------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------
//        // Загрузка шаблона отчета Jasper Reports из файла
//        Resource resource = null;
//        resource = new ClassPathResource("/reports/order_to_expert.jrxml");
//
//        InputStream jasperReportStream = resource.getInputStream();
//        JasperReport jasperReport = JasperCompileManager.compileReport(jasperReportStream);
//
//        // Заполнение отчета данными
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportData);
//        JRBeanCollectionDataSource itemJRBean = new JRBeanCollectionDataSource(lectureDtos);
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("CollectionBeanParam", itemJRBean);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//        JasperExportManager.exportReportToPdfFile(jasperPrint, "report.pdf");
//
//        // Преобразование отчета в PDF и кодирование его в Base64
//        byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
//        String base64 = Base64.getEncoder().encodeToString(pdfBytes);
//        System.out.println(base64);
    //------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------
    //-----------------------------------Отчеты в pdf и HTML со ссылкой-------------------------------------------
    //------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------
    // Загрузка шаблона отчета Jasper Reports из файла
//    Resource resource = null;
////        if (processType.equals(ApplicationForApprovalProcessType)){
////            resource = new ClassPathResource("/reports/order_to_expert.jrxml");
////        } else {
////            resource = new ClassPathResource("/reports/order.jrxml");
////        }
//    resource = new ClassPathResource("/reports/order.jrxml");
//    InputStream jasperReportStream = resource.getInputStream();
//
//
//    //        InputStream reportStream = getClass().getResourceAsStream("/reports/report.jrxml");
//    JasperReport jasperReport = JasperCompileManager.compileReport(jasperReportStream);
//
//    // Получение данных для отчета из базы данных
////        List<ReportData> reportData = reportService.getReportData();
//
////        String classPath = System.getProperty("user.dir") + "/template/";
//
//    // Заполнение отчета данными
//    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportData);
//    //        JRBeanCollectionDataSource itemJRBean = new JRBeanCollectionDataSource(lectureDtos);
//    Map<String, Object> parameters = new HashMap<>();
//    //        parameters.put("CollectionBeanParam", itemJRBean);
////        parameters.put("classPath", classPath);
//    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//
//    // Экспорт отчета в формат HTML
//    HtmlExporter exporter = new HtmlExporter();
//        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//
//    ByteArrayOutputStream htmlOutputStream = new ByteArrayOutputStream();
//        exporter.setExporterOutput(new SimpleHtmlExporterOutput(htmlOutputStream));
//        exporter.exportReport();
//
//    String htmlContent = htmlOutputStream.toString("UTF-8");
//
//    String test = "<html><head>\n" +
//            "    <meta charset=\"UTF-8\">\n" +
//            "  </head>\n" +
//            "<body>Hello world! <p> Здравствуйте, товарищи! </p></body></html>";
//
//    String wrappedHtml = "<a href=\"" + link + "\">" + htmlContent + "</a>";
//
//        System.out.println("wrappedHtml" + new String(wrappedHtml.getBytes("UTF-8"), "UTF-8"));
//        JasperExportManager.exportReportToPdfFile(jasperPrint, "report.pdf");
//    String fileName = "output.html";
//        try (PrintWriter writer = new PrintWriter(fileName, "UTF-8")) {
//        writer.print(wrappedHtml);
//    } catch (FileNotFoundException | UnsupportedEncodingException e) {
//        e.printStackTrace();
//    }
//
//    // Преобразование отчета в PDF и кодирование его в Base64
////        byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
////
////
////        String base64 = Base64.getEncoder().encodeToString(pdfBytes);
////        System.out.println(base64);
//    //////
//    // Convert wrappedHtml to Base64
//
//
//    byte[] testBytes = test.getBytes("UTF-8");
//    String testBytesBase64 = Base64.getEncoder().encodeToString(testBytes);
//        System.out.println("base64" + testBytesBase64);
//
//
//    byte[] wrappedHtmlBytes = wrappedHtml.getBytes("UTF-8");
//    String base64 = Base64.getEncoder().encodeToString(wrappedHtmlBytes);
//        System.out.println("base64" + base64);
//    //////
    //------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------
}
