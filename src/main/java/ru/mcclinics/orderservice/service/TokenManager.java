package ru.mcclinics.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDateTime;
import java.util.Date;
public class TokenManager {
    private String token;
    private Date expirationDate;

    public String getToken() throws JsonProcessingException {
        if (token == null || isExpired()) {
            refreshToken();
        }
        return token;
    }

    public void setToken(String token, int expiresIn) {
        this.token = token;
        this.expirationDate = new Date(System.currentTimeMillis() + expiresIn * 1000);
    }

    public boolean isExpired() {
        return expirationDate == null || expirationDate.before(new Date());
    }

    private void refreshToken() throws JsonProcessingException {
        // запрос на сервер для получения нового токена
        String newToken = EmployeeDtoClientService.getToken();
        int expiresIn = 240;
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.now().plusMinutes(5));
        setToken(newToken, expiresIn);
    }
}
