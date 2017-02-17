package com.scipionyx.butterflyeffect.api.stocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author Renato Mendes
 *
 */
@SpringBootApplication()
@ComponentScan()

@Configuration()
@EnableAutoConfiguration
public class StocksTestApplication {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(StocksTestApplication.class, args);
	}

}
