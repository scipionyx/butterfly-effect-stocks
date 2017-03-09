package com.scipionyx.butterflyeffect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
@EnableElasticsearchRepositories(basePackages = { "com.scipionyx.butterflyeffect" })
@EnableTransactionManagement
public class BackendApplication {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}