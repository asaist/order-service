package ru.mcclinics.orderservice.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class McclinicsCorsConfiguration implements WebMvcConfigurer {
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("login");
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
        .allowCredentials(true)
        .allowedOriginPatterns("*")
        .allowedHeaders("*")
        .allowedMethods("*")
        .exposedHeaders(HttpHeaders.SET_COOKIE);
  }

  @Bean
  public CorsFilter corsWebFilter(UrlBasedCorsConfigurationSource corsConfigurationSource) {
    return new CorsFilter(corsConfigurationSource);
  }

  @Bean
  public UrlBasedCorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration mcclinicsCorsConfiguration = new CorsConfiguration();
    mcclinicsCorsConfiguration.setAllowCredentials(true);
    mcclinicsCorsConfiguration.addAllowedHeader("*");
    mcclinicsCorsConfiguration.addAllowedMethod("*");
    mcclinicsCorsConfiguration.addAllowedOriginPattern("*");
    mcclinicsCorsConfiguration.addExposedHeader(HttpHeaders.SET_COOKIE);
    UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
    corsConfigurationSource.registerCorsConfiguration("/**", mcclinicsCorsConfiguration);
    return corsConfigurationSource;
  }
}
