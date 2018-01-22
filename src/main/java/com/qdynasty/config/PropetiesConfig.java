package com.qdynasty.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qdynasty.constants.SystemConfigProperties;

@Configuration
public class PropetiesConfig {

	@Bean
	SystemConfigProperties systemConfigProperties() {
		return new SystemConfigProperties();
	}
}
