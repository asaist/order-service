package ru.mcclinics.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ru.mcclinics.orderservice.domain.Author;
import ru.mcclinics.orderservice.dto.EmployeeDegreeDto;
import ru.mcclinics.orderservice.dto.EmployeeDto;
import ru.mcclinics.orderservice.dto.Mkb10Dto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.springframework.security.oauth2.core.OAuth2TokenIntrospectionClaimNames.CLIENT_ID;
import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.CLIENT_SECRET;

@Service
@RequiredArgsConstructor
public class EmployeeDtoClientService {

    private final AuthorService authorService;
    public List<EmployeeDto> getEmployeeDtoList() throws JsonProcessingException {
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
        headers2.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers2.set(HttpHeaders.AUTHORIZATION, "Bearer " + tokenKeyCloak);
        HttpEntity<?> entity = new HttpEntity<>(headers2);


        HttpEntity<String> response2 = restTemplate.exchange(
                "https://dev.service.samsmu.ru/users/users/employee",
                HttpMethod.GET,
                entity,
                String.class
        );
        ResponseEntity<EmployeeDto[]> response4 = restTemplate.exchange(
                "https://dev.service.samsmu.ru/users/users/employee",
                HttpMethod.GET,
                entity,
                EmployeeDto[].class
        );
        EmployeeDto[] employeeDtos = response4.getBody();
        List<EmployeeDto> employeeDtoList = Arrays.asList(employeeDtos);
        return employeeDtoList;
    }

    public static String getToken() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(CLIENT_ID, CLIENT_SECRET);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");
        map.add("client_id", "track-samsmu-service");
        map.add("client_secret", "775f3ea5-e6b3-47c3-b4ba-d00aaceb3da7");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("https://sso.samsmu.ru/auth/realms/SAMGMU/protocol/openid-connect/token", request, String.class);

        String responseBody = response.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map1 = mapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {
        });

        // парсим JSON-строку
        JSONObject jsonObject = new JSONObject(map1);
        String tokenKeyCloak = jsonObject.getAsString("access_token");
        return tokenKeyCloak;
    }
    @Scheduled(cron = "0 40 21 * * *")
    public String getEmployeeDegree() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        TokenManager tokenManager = new TokenManager();
        HttpHeaders headers2 = new HttpHeaders();
        headers2.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);


        String baseUrl = "https://dev.service.samsmu.ru/users/users/employee/";

        List<Author> authors = authorService.findAuthors();

        for(Author author : authors) {
            headers2.set(HttpHeaders.AUTHORIZATION, "Bearer " + tokenManager.getToken());
            HttpEntity<?> entity = new HttpEntity<>(headers2);
            if ((author.getGuid()!= null) && (author.getAcademicDegreeName() == null)) {
                String url = baseUrl + author.getGuid() + "/EmployeeAcademicDegrees";
                ResponseEntity<EmployeeDegreeDto[]> response2 = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        EmployeeDegreeDto[].class
                );

                Map<String, EmployeeDegreeDto> degrees = new HashMap<>();
                List<EmployeeDegreeDto> employeeDegreesList = Arrays.asList(response2.getBody());
                Map<String, EmployeeDegreeDto> map3 = new HashMap<String, EmployeeDegreeDto>();
                for(EmployeeDegreeDto employeeDegreeDto:employeeDegreesList){
                    map3.put(employeeDegreeDto.getEmployeeGuid(), employeeDegreeDto);
                }

                if (response2.getBody().length != 0) {

                    if (response2.getBody().length > 1) {
                        map3.forEach((key, value) -> {
                            if (value.getAcademicDegreeName().contains("Доктор")) {
                                degrees.put(key, value);
                            }
                        });
                    }
                    if (response2.getBody().length == 1) {
                        map3.forEach((key, value) -> {
                            degrees.put(key, value);
                        });
                    }


//                    Iterator<Map.Entry<String, EmployeeDegreeDto>> iterator = map3.entrySet().iterator();
//                    while (iterator.hasNext()) {
//                        Map.Entry<String, EmployeeDegreeDto> entry = iterator.next();
//                        if (entry.getValue().getDocDate() == null) {
//                            iterator.remove();
//                        }
//                    }
//                    List<EmployeeDegreeDto> result = new ArrayList<>(map3.values());
//                    EmployeeDegreeDto employeeDegreeDto1 = Arrays.stream(response2.getBody())
//                            .min(Comparator.comparing(EmployeeDegreeDto::getDocDate))
//                            .orElse(null);


                    List<EmployeeDegreeDto> result = new ArrayList<>(map3.values());
                    author.setAcademicDegreeName(result.get(0).getAcademicDegreeName());
                    authorService.create(author);
                }
            }
            int j = 0;


        }
        return "employeeDtoList";
    }
    @Scheduled(cron = "0 55 06 * * *")
    public void getEmployeeDto() throws JsonProcessingException {

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


        HttpHeaders headers2 = new HttpHeaders();
        headers2.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers2.set(HttpHeaders.AUTHORIZATION, "Bearer " + tokenKeyCloak);
        HttpEntity<?> entity = new HttpEntity<>(headers2);

        ResponseEntity<EmployeeDto[]> response4 = restTemplate.exchange(
                "https://dev.service.samsmu.ru/users/users/employee?toPosition=150000",
                HttpMethod.GET,
                entity,
                EmployeeDto[].class
        );
        EmployeeDto[] employeeDtos = response4.getBody();
        List<EmployeeDto> employeeDtoList = Arrays.asList(employeeDtos);
        Map<String, Author> authorMapFromUsers = new HashMap<String, Author>();
        for (EmployeeDto employeeDto: employeeDtoList){
            Author author = new Author();
            author.setGuid(employeeDto.getEmployeeGuid());
            author.setLastName(employeeDto.getLastName());
            author.setFirstName(employeeDto.getFirstName());
            author.setMiddleName(employeeDto.getPatronymicName());
            authorMapFromUsers.put(employeeDto.getEmployeeGuid(), author);
        }
        Map<String, Author> authorMapFromDb = new HashMap<String, Author>();
        List<Author> authors = authorService.findAuthors();
        for(Author author1:authors){
            authorMapFromDb.put(author1.getGuid(), author1);
        }
        for (String key : authorMapFromUsers.keySet()) {
            if (!authorMapFromDb.containsKey(key)){
                authorService.create(authorMapFromUsers.get(key));
            }
        }
        System.out.println("GOT EMPLOYEE");
    }


}
