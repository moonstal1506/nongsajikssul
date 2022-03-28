package com.nongsa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.nongsa.config.auth.PrincipalDetailService;

@Configuration 
@EnableWebSecurity 
@EnableGlobalMethodSecurity(prePostEnabled = true) 
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http
	.csrf().disable()
	.authorizeRequests()
		.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**") 
		.permitAll()
		.anyRequest()
		.authenticated()
	.and()
	.formLogin()
	.loginPage("/auth/loginForm")
	.loginProcessingUrl("/auth/loginProc")
	.defaultSuccessUrl("/");
	}
}
