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
import ru.mcclinics.orderservice.domain.Author;
import ru.mcclinics.orderservice.dto.EmployeeDto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
