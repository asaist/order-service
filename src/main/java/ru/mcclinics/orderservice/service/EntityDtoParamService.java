package ru.mcclinics.orderservice.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.mcclinics.orderservice.domain.Author;
import ru.mcclinics.orderservice.dto.EmployeeDto;
import ru.mcclinics.orderservice.dto.Mkb10Dto;

import java.io.IOException;
import java.util.*;

import static org.springframework.security.oauth2.core.OAuth2TokenIntrospectionClaimNames.CLIENT_ID;
import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.CLIENT_SECRET;

@Service
public class EntityDtoParamService {

    public List<Mkb10Dto> getEntityDtoList() throws JsonProcessingException {
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
        Map<String, Object> map1 = mapper.
                readValue(responseBody, new TypeReference<Map<String,Object>>(){});

        // парсим JSON-строку
        JSONObject jsonObject = new JSONObject(map1);
        String tokenKeyCloak = jsonObject.getAsString("access_token");
        int i =1;

        HttpHeaders headers2 = new HttpHeaders();
        headers2.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers2.set(HttpHeaders.AUTHORIZATION, "Bearer " + tokenKeyCloak);
        HttpEntity<?> entity = new HttpEntity<>(headers2);


        HttpEntity<String> response2 = restTemplate.exchange(
                "http://172.26.1.156:2989/values?limit=15000&param_id=25",
                HttpMethod.GET,
                entity,
                String.class
        );
        String responseBody2 = response2.getBody();
        List<Mkb10Dto> mkb10DtoList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody2);
            JsonNode dataArrayNode = rootNode.get("data");
            if (dataArrayNode.isArray()) {
                 mkb10DtoList = Arrays.asList(objectMapper.treeToValue(dataArrayNode, Mkb10Dto[].class));
                // Do something with the deserialized list of PilonidalCyst objects
            }
        } catch (JsonParseException e) {
            // Handle exception
        } catch (JsonMappingException e) {
            // Handle exception
        } catch (IOException e) {
            // Handle exception
        }
//        ResponseEntity<Mkb10Dto[]> response4 = restTemplate.exchange(
//                "http://172.26.1.156:2989/values?limit=5000&param_id=25",
//                HttpMethod.GET,
//                entity,
//                Mkb10Dto[].class
//        );
        int j = 1;
//
        Map<String, Mkb10Dto> map3 = new HashMap<String, Mkb10Dto>();

        for(Mkb10Dto mkb10Dto:mkb10DtoList){
            map3.put(mkb10Dto.getId(), mkb10Dto);
        }
        Iterator<Map.Entry<String, Mkb10Dto>> iterator = map3.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Mkb10Dto> entry = iterator.next();
            if (entry.getValue().getCode() == null) {
                iterator.remove();
            }
        }
        List<Mkb10Dto> result = new ArrayList<>(map3.values());
        Collections.sort(result, Comparator.comparing(Mkb10Dto::getName));
        return  result;

    }

}
