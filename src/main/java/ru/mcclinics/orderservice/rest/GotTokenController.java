package ru.mcclinics.orderservice.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GotTokenController {
    private boolean flag = true;

    public boolean getFlag() {
        return flag;
    }
    @GetMapping("/public")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> setFlag(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        if (authorizationHeader != null) {
            this.flag = true;
            System.out.println("TokenController on track.samsmu.ru: " + authorizationHeader);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/");
            headers.add("Authorization", authorizationHeader);
            headers.add("X-Frame-Options", "SAMEORIGIN");
            return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
        }
        return null;
    }

}
