package com.qdynasty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author fei.qin
 *
 */
@SpringBootApplication
@EnableConfigurationProperties
public class SpringSecurityApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}
}
