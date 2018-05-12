package com.smoothai.smoothai;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.smoothai.smoothai.storage.StorageService;

@SpringBootApplication
public class SmoothaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmoothaiApplication.class, args);
	}
	
	@Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
        	storageService.deleteAll();
            storageService.init();
        };
    }
}
