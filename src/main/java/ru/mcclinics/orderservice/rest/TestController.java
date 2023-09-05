package ru.mcclinics.orderservice.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nonapi.io.github.classgraph.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;
import ru.mcclinics.orderservice.service.UniversityService;

import java.util.Enumeration;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Тестовые операции", description = "Получение результата для проверки работоспособности")
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
//    @GetMapping("/public")
//    @ResponseStatus(HttpStatus.OK)
//    @Operation(description = "Получить тестовый ответ")
//    @ApiResponse(responseCode = "200", description = "Операция выполнена успешно")
//    @ApiResponse(responseCode = "400", description = "Некорректный формат запроса")
//    @ApiResponse(responseCode = "500", description = "Некорректный формат запроса")
//    public String getPublicMessage(@RequestHeader(value = "Authorization", required = false) String authorizationHeader){
//        // Ваша логика обработки параметров
//        HttpHeaders headers = new HttpHeaders();
//
//        if (authorizationHeader != null) {
//            System.out.println(authorizationHeader);
//            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + authorizationHeader);
//            return "forward:/";
//        } else {
//            return "forward:/";
//        }
//
//
//    }
//    public String getPublicMessage(){
//        return "Public message from server ";
//    }


    public static String getJWT(HttpServletRequest request, String tokenName) {
        String JWT = "";

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Enumeration<String> headerNames = httpRequest.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                if (tokenName.equalsIgnoreCase(headerName)) {
                    JWT = httpRequest.getHeader(headerName);
                    break;
                }
            }
        }
//logger.info(JWT);
        return JWT;
    }




    @GetMapping("/moderator-access")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Получить тестовый ответ")
    @ApiResponse(responseCode = "200", description = "Операция выполнена успешно")
    @ApiResponse(responseCode = "400", description = "Некорректный формат запроса")
    @ApiResponse(responseCode = "500", description = "Некорректный формат запроса")
    public String getModeratorMessage(){
        return "Public message from server ";
    }

//    @GetMapping("/user")
//    @ResponseStatus(HttpStatus.OK)
//    @Operation(description = "Получить тестовый ответ")
//    @ApiResponse(responseCode = "200", description = "Операция выполнена успешно")
//    @ApiResponse(responseCode = "400", description = "Некорректный формат запроса")
//    @ApiResponse(responseCode = "500", description = "Некорректный формат запроса")
//    public String getAllUsersMessage(){
//        return "All users message from server ";
//    }

    @GetMapping("/admin-access")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Получить тестовый ответ")
    @ApiResponse(responseCode = "200", description = "Операция выполнена успешно")
    @ApiResponse(responseCode = "400", description = "Некорректный формат запроса")
    @ApiResponse(responseCode = "500", description = "Некорректный формат запроса")
    public String getAdminMessage(){
        return "Admin message from server ";
    }
}
