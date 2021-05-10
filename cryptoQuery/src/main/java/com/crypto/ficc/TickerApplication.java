package com.crypto.ficc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class TickerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TickerApplication.class, args);
	}

}
