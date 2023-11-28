package ru.mcclinics.orderservice.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mcclinics.orderservice.domain.Author;
import ru.mcclinics.orderservice.dto.UserInfoDto;

@Service
@AllArgsConstructor
public class CheckTokenService {

    private final AuthorService authorService;

    public Author checkToken(String token){

        RestTemplate restTemplate = new RestTemplate();
        System.out.println("Token in CheckToken: " + token);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String url = "https://sso.samsmu.ru/auth/realms/SAMGMU/protocol/openid-connect/userinfo";
        ResponseEntity<UserInfoDto> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                UserInfoDto.class);
        UserInfoDto userinfo = response.getBody();
        System.out.println("USER_GUID: " + userinfo.getSub());
        Author supervisor = authorService.findAuthorByGuid(userinfo.getSub());
        System.out.println("User: " + userinfo.getSub() + userinfo.getGivenName() + userinfo.getFamilyName());
//        return userinfo.getSub() != null;
        return supervisor;
    }
}
