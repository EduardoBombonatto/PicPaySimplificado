package com.picpaysimplificado.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

	public RestTemplate restTamplate() {
		return new RestTemplate();
	}
}
