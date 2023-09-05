package ru.mcclinics.orderservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtGrantedAuthoritiesConverterAdapter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.mcclinics.orderservice.service.UserService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class WebSecurityConfig{
    private final UserService userService;


//    private final MatchPathToRoleFilter matchPathToRoleFilter;
//    private final CustomJwtConverter customJwtConverter;

//    @Bean
//        public SecurityWebFilterChain configureResourceServer(ServerHttpSecurity httpSecurity) {
//
//            return httpSecurity
//                    .authorizeExchange()
//                    .pathMatchers("/public").permitAll()
//                    .anyExchange()
//                    .authenticated()
//                    .and()
//                    .addFilterAfter(matchPathToRoleFilter, SecurityWebFiltersOrder.AUTHORIZATION)
//                    .csrf().disable()
//                    .httpBasic().disable()
//                    .formLogin().disable()
//                    .logout().disable()
//                    .oauth2ResourceServer().jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
//                    .and()
//                    .build();
//        }
//
//        @Bean
//        public ReactiveJwtAuthenticationConverter jwtAuthenticationConverter() {
//            var jwtAuthenticationConverter = new ReactiveJwtAuthenticationConverter();
//            jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(
//                    new ReactiveJwtGrantedAuthoritiesConverterAdapter(customJwtConverter));
//
//            return jwtAuthenticationConverter;
//    }

    @Bean
    public RequestHeaderAuthorizationFilter requestHeaderAuthorizationFilter() {
        return new RequestHeaderAuthorizationFilter();
    }

    public static class RequestHeaderAuthorizationFilter extends OncePerRequestFilter {
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
            String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader != null) {
                // Если заголовок "Authorization" присутствует, позволяется доступ к остальным URL-адресам
//                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(null, null, Collections.emptyList()));
                response.setHeader("Authorization", "Bearer " + authorizationHeader);
                response.setHeader("X-Frame-Options", "SAMEORIGIN");
                System.out.println( "filter authorizationHeader: " + authorizationHeader);
                response.sendRedirect("/");
                return;
            }
            filterChain.doFilter(request, response);
        }
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/public").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login();
        http
                .oauth2ResourceServer()
                .jwt();
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

//

//    http
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
    ////
//                .requestMatchers("/").hasRole("uma_authorization")
    ////
    //рабочий спек для кейклок oauth2 client
//      http
//              .csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/public").permitAll()
////                .requestMatchers("/").access(authorizationManager)
//                .anyRequest()
//                .authenticated()
//                .and()
//                .oauth2Login();
//        return http.build();

//    @Bean
//    public SecurityFilterChain configure1(HttpSecurity http, CustomAuthorizationManager authorizationManager) throws Exception {
////
//        http
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/public").permitAll()
////                .requestMatchers("/").access(authorizationManager)
//                .anyRequest()
//                .authenticated()
//                .and()
//                .oauth2Login();
//        return http.build();
//    }

//    @Bean
//    public SecurityWebFilterChain configureResourseService(ServerHttpSecurity httpSecurity){
//        return httpSecurity
//                .authorizeExchange()
//                .pathMatchers("/public","/").permitAll()
//                .anyExchange().authenticated()
//                .and()
//                .csrf().disable()
//                .httpBasic().disable()
//                .formLogin().disable()
//                .logout().disable()
//                .oauth2ResourceServer().jwt()
//                .and()
//                .and()
//                .build();
//    }

//    @Bean
//    public SecurityWebFilterChain configureResourceServer(ServerHttpSecurity httpSecurity) {
//
//        return httpSecurity
//                .authorizeExchange()
//                .pathMatchers("/actuator/health/**", "/*/*/*/public/**").permitAll()
//                .anyExchange()
//                .authenticated()
//                .and()
//                .addFilterAfter(matchPathToRoleFilter, SecurityWebFiltersOrder.AUTHORIZATION)
//                .csrf().disable()
//                .httpBasic().disable()
//                .formLogin().disable()
//                .logout().disable()
//                .oauth2ResourceServer().jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
//                .and()
//                .build();
//    }

//    @Bean
//    public ReactiveJwtDecoder reactiveJwtDecoder() {
//        return new NimbusReactiveJwtDecoder("https://sso.samsmu.ru/auth/realms/SAMGMU/protocol/openid-connect/certs");
//    }


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        CorsConfiguration cors_config = new CorsConfiguration();
//        cors_config.setAllowCredentials(true);
//        cors_config.applyPermitDefaultValues();
//        cors_config.setAllowedOrigins(Arrays.asList("https://dev.service.samsmu.ru"));
//        cors_config.setAllowedMethods(List.of("GET","POST","OPTIONS","DELETE"));
//        cors_config.setAllowedHeaders(List.of("*"));
//        http.cors().configurationSource(source -> cors_config);
//
//
//        return http.build();
//    }

//    @Bean
//    public RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter() throws Exception {
//        RequestHeaderAuthenticationFilter filter = new RequestHeaderAuthenticationFilter();
//        filter.setPrincipalRequestHeader("Authorization"); // Замените на имя заголовка с токеном
////        filter.setAuthenticationManager(authenticationManager());
//        filter.setExceptionIfHeaderMissing(false);
//        filter.setContinueFilterChainOnUnsuccessfulAuthentication(true);
//        filter.setAuthenticationSuccessHandler((request, response, authentication) -> {
//            // Задайте свои дополнительные действия при успешной аутентификации из заголовка
//            // Например, перенаправление на нужную страницу
//            response.sendRedirect("/");
//        });
//        return filter;
//    }



//    @Override
//    protected void configure3(HttpSecurity http) throws Exception {
//        http.addFilterBefore(requestHeaderAuthenticationFilter(), AbstractPreAuthenticatedProcessingFilter.class);
//    }


    @Autowired
    protected void configure2(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

}
