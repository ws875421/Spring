package com.webcomm.oa.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.webcomm.oa.controller", "com.webcomm.oa.service", "com.webcomm.oa.config" })
@EnableJpaRepositories(basePackages = { "com.webcomm.oa.repository" })
@EntityScan(basePackages = { "com.webcomm.oa.domain" })
public class OaSpringRexApplication implements CommandLineRunner {

	@Autowired
	private ApplicationContext appContext;

	public static void main(String[] args) {
		SpringApplication.run(OaSpringRexApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		String[] beans = appContext.getBeanDefinitionNames();
//		Arrays.sort(beans);
//		for (String bean : beans) {
//			System.out.println(bean);
//		}

	}

}
