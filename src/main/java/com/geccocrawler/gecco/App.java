package com.geccocrawler.gecco;

import com.geccocrawler.gecco.myGecco.GeccoEngine;
import com.geccocrawler.gecco.spring.SpringGeccoEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class App {


	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);

	}
	
}
