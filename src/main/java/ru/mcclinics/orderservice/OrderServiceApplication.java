package ru.mcclinics.orderservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableScheduling
@SpringBootApplication
public class OrderServiceApplication {
	public static void main(String[] args) throws JsonProcessingException {
		System.setProperty("file.encoding", "UTF-8");
		SpringApplication.run(OrderServiceApplication.class, args);
	}
}
