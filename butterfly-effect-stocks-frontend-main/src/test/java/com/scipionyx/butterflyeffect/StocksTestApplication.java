package com.scipionyx.butterflyeffect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author Renato Mendes
 *
 */
//
@SpringBootApplication

//
@EnableDiscoveryClient

//
// @EnableSpringConfigured
// @EnableLoadTimeWeaving(aspectjWeaving = AspectJWeaving.ENABLED)
// @EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class StocksTestApplication {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(StocksTestApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
