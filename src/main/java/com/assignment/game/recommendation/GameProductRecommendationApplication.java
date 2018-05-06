package com.assignment.game.recommendation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={MultipartAutoConfiguration.class})
@EntityScan(basePackages = {"com.assignment.game.recommendation.entity"})
public class GameProductRecommendationApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameProductRecommendationApplication.class, args);
	}
}
