package ru.mcclinics.orderservice.cors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class McclinicsCorsConfiguration implements WebMvcConfigurer {

  @Value("${files.upload.baseDir}")
  private String uploadPath;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/pdf/**")
            .addResourceLocations("file://" + uploadPath +"/");
    registry.addResourceHandler("/static/**")
            .addResourceLocations("classpath:/static/");
  }

//  @Override
//  public void addViewControllers(ViewControllerRegistry registry) {
//    registry.addViewController("/login").setViewName("login");
//  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {

    registry.addMapping("/**")
            .allowedOrigins("https://dev.service.samsmu.ru")
            .allowedMethods("*")
            .allowedHeaders("*")
            .exposedHeaders("*")
            .allowCredentials(true).maxAge(3600);
  }

//  @Bean
//  public FilterRegistrationBean<CorsFilter> corsFilter() {
//    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//    CorsConfiguration config = new CorsConfiguration();
//    config.setAllowCredentials(true);
//    config.addAllowedOrigin("https://dev.service.samsmu.ru"); // Set your specific domain here instead of "*"
//    config.addAllowedHeader("*");
//    config.addAllowedMethod("*");
//    source.registerCorsConfiguration("/**", config);
//    FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
//    bean.setOrder(0);
//    return bean;
//  }


//  @Bean
//  public UrlBasedCorsConfigurationSource corsConfigurationSource2() {
//    CorsConfiguration mcclinicsCorsConfiguration = new CorsConfiguration();
//    mcclinicsCorsConfiguration.setAllowCredentials(true);
//    mcclinicsCorsConfiguration.addAllowedHeader("*");
//    mcclinicsCorsConfiguration.addAllowedMethod("*");
//    mcclinicsCorsConfiguration.addAllowedOriginPattern("*");
//    mcclinicsCorsConfiguration.addExposedHeader(HttpHeaders.SET_COOKIE);
//    UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
//    corsConfigurationSource.registerCorsConfiguration("/**", mcclinicsCorsConfiguration);
//    return corsConfigurationSource;
//  }
}
