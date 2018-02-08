# Spring Security

Steps for Spring Security which Spring provides.

1. Add a dependency in pom.xml file. Spring-boot-starter-security.
2. Restart the application.
3. in the logs we will get the password which will be something like this
	Using generated security password: 69f9a6b5-5bb8-4d04-9216-ea9a213263c0
4. User name will be user.
5. Now if we access any uri for rest service, basic login page will pop up and we need to enter the username and password.

## Note: The password will change with every server restart.

Steps to override Basic Security which Spring provides.

1. Create a class and annotate it with 
	1. @Configuration
	2. @EnableWebSecurity
	
2. Extends the class with WebSecurityConfigurerAdapter

3. Override its two methods.

	1. configure(HttpSecurity) - this will let how requests are secured by interceptors.
	
	2. configure(AuthenticationMangerBuilder) - override to configure user details. 


@Configuration overrides the spring framework auto-configuration for Security
                for this bean.

@EnableWebSecurity enables Spring Security and overrides the default
                    security, but this annotation is not enough in itself, it
                  needs WebSecurityConfigurerAdapter for security in Spring



@Value - it reads the value from properties file. Internally @PropertyPlaceHolderConfigurer bean is called.

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
				http.authorizeRequests().antMatchers("/v1/public/books").hasRole(rolename).and().formLogin();
			}
		
			@Override
			protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
				String username [] = usernames.split(",");
				for(String user : username)
				authenticationManagerBuilder.inMemoryAuthentication().withUser(user).password("{noop}" + password)
						.roles(rolename);
			}
		}



values which are defined in the properties file are as under.

	spring.data.name=arun,adwiti
	spring.data.password=thepassword
	spring.data.role=admin
	