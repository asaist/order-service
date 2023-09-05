package ru.mcclinics.orderservice.cors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;

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
    registry
        .addMapping("/**")
        .allowCredentials(true)
        .allowedOriginPatterns("*")
        .allowedHeaders("*")
        .allowedMethods("*")
        .exposedHeaders(HttpHeaders.SET_COOKIE);
    registry.addMapping("/**")
            .allowedOrigins("https://dev.service.samsmu.ru") // Specify the allowed origin
            .allowedMethods("*") // Allowing all methods
            .allowedHeaders("*") // Allowing all headers
            .allowCredentials(true); // Allowing credentials
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new HandlerInterceptor() {
      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Ваша логика предварительной обработки
        return true;
      }

      @Override
      public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Ваша логика постобработки
      }

      @Override
      public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Ваша логика после завершения обработки запроса
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
      }
    });
  }

//  @Bean
//  public CorsFilter corsWebFilter(UrlBasedCorsConfigurationSource corsConfigurationSource) {
//    return new CorsFilter(corsConfigurationSource);
//  }
//
//  @Bean
//  public UrlBasedCorsConfigurationSource corsConfigurationSource() {
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

  @Bean
  public FilterRegistrationBean<CorsFilter> corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("https://dev.service.samsmu.ru"); // Set your specific domain here instead of "*"
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    source.registerCorsConfiguration("/**", config);
    FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
    bean.setOrder(0);
    return bean;
  }
}
