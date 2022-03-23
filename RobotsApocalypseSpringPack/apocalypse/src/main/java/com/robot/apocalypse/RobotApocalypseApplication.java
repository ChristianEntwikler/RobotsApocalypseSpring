package com.robot.apocalypse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RobotApocalypseApplication {

	public static void main(String[] args) {
		SpringApplication.run(RobotApocalypseApplication.class, args);
	}

        @Bean
        public RestTemplate getRestTemplate() 
        {
            
            return new RestTemplate();
        }

}
