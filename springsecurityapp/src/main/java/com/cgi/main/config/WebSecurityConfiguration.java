package com.cgi.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication()
			.withUser("imtiaz").password(passwordEncoder().encode("imtiaz")).roles("ADMIN","MANAGER");
			
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    		//http.authorizeRequests().antMatchers(HttpMethod.POST).hasAnyRole("ADMIN");
    		//.antMatchers(HttpMethod.GET).hasAnyRole("ADMIN")
    		//.antMatchers(HttpMethod.GET).hasAnyRole("MANAGER");
    		
    		//http.cors().disable();
    		//http.csrf().disable();
    		super.configure(http);
    }
	
	@Bean
	public  PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
