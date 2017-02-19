package com.scipionyx.butterflyeffect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
@SpringBootApplication
@ComponentScan(lazyInit = true)

@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = { "com.scipionyx.butterflyeffect" })
public class BackendApplication {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}