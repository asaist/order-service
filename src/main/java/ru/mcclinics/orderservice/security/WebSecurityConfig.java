package ru.mcclinics.orderservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import ru.mcclinics.orderservice.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig{
    private final UserService userService;
    public WebSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain configure1(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .requestMatchers("/", "/registration", "/static/**").permitAll()
//                .anyRequest().authenticated()
//            .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//            .and()
//                .logout()
//                .permitAll();
////        http.securityContext(securityContext -> securityContext.
////                securityContextRepository(new HttpSessionSecurityContextRepository())
////        );
        //рабочий спек
//        http.authorizeRequests()
//                .requestMatchers("/**").permitAll() // Разрешаем доступ к открытым ресурсам всем пользователям
//                .anyRequest().authenticated(); // Все остальные запросы требуют аутентификации
//        http.csrf().disable();

        //рабочий спек для кейклок oauth2 client
        http
                .csrf()
                .disable()
                .cors().disable()
                .authorizeHttpRequests()
//                .requestMatchers("/public", "/", "/templates/**", "/static/**", "/api/**", "/resources/**", "/table_track", "/module/**").permitAll()
                .requestMatchers("/**").permitAll();
        ///----Отключил 01.12.2023 для демонстрации Денису работы приложения --- //////
//                .anyRequest()
//                .authenticated()
//                .and()
//                .oauth2Login();
//        http
//                .oauth2ResourceServer()
//                .jwt();
        //////------------------------------------------------------------------//////
        http.headers().frameOptions().disable();
        return http.build();
    }
    @Autowired
    protected void configure2(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

}
