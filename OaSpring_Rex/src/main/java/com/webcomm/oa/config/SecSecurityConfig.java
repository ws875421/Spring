package com.webcomm.oa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 安全性設定
 * @author user
 *
 */
@Configuration
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication()
				.withUser("user1").password(passwordEncoder().encode("123456")).roles("USER")
				.and()
				.withUser("admin").password(passwordEncoder().encode("123456")).roles("ADMIN", "USER")
				.and()
				.withUser("user2").password(passwordEncoder().encode("123456")).roles("ADMIN", "USER");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/caseReq/*").hasRole("USER")
			.antMatchers("/admin/**").hasRole("ADMIN")
			.and().formLogin().defaultSuccessUrl("/index")
			.and().logout().logoutSuccessUrl("/index")
			.and().csrf().disable();
	}

}
