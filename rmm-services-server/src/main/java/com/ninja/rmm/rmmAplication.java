package com.ninja.rmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ninja.rmm.security.JWTAuthorizationFilter;

@SpringBootApplication
public class rmmAplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(rmmAplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/customerLogin").permitAll()
				.antMatchers(HttpMethod.GET, "/customer").permitAll()
				.antMatchers(HttpMethod.POST, "/customer").permitAll()
				.antMatchers(HttpMethod.GET, "/services").permitAll()
				.anyRequest().authenticated();
		}
	}
}
