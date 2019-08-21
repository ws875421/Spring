package com.webcomm.oa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.springdata.SpringDataDialect;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

/**
 * Thymeleaf設定
 * 
 * @author user
 *
 */
@Configuration
public class ThymeleafAutoConfiguration {

	/**
	 * Thymeleaf 分頁
	 * 
	 * @return
	 */
	@Bean
	public SpringDataDialect springDataDialect() {
		return new SpringDataDialect();
	}

	/**
	 * 安全性
	 * 
	 * @return
	 */
	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}
}
