package com.arun.springtutorial.SpringInerview.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 
 * @author Adwiti
 *
 * @Configuration overrides the spring framework auto-configuration for Security
 *                for this bean.
 * 
 * @EnableWebSecurity enables Spring Security and overrides the default
 *                    security, but this annotation is not enough in itself, it
 *                    needs WebSecurityConfigurerAdapter for security in Spring
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${spring.data.name}")
	private String usernames;

	@Value("${spring.data.password}")
	private String password;

	@Value("${spring.data.role}")
	private String rolename;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/v1/**","/swagger-ui.html")
				.hasRole(rolename).and().formLogin();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		/*String username[] = usernames.split(",");
		for (String user : username)*/
			authenticationManagerBuilder.inMemoryAuthentication().withUser(usernames).password("{noop}" + password)
					.roles(rolename);
	}
}
