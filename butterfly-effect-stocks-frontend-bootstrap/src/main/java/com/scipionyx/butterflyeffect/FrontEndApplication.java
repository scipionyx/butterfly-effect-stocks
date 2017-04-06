package com.scipionyx.butterflyeffect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

//
@SpringBootApplication

//
@EnableDiscoveryClient

//
@EnableSpringConfigured
@EnableLoadTimeWeaving(aspectjWeaving = AspectJWeaving.ENABLED)
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)

public class FrontEndApplication {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(FrontEndApplication.class, args);
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate() {

		RestTemplate restTemplate = new RestTemplate();

		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		return restTemplate;
	}

}
