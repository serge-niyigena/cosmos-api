package com.cosmos.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cosmos.handlers.ApiAccessDeniedHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfigs extends WebSecurityConfigurerAdapter{


	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.cors().and().csrf().disable()
	            .authorizeRequests()
	            .antMatchers("api/v1/**").permitAll()
	            .antMatchers("/api/v1/userSignOut").authenticated()
	            .antMatchers("/v2/api-docs", "/swagger-ui/**", "/swagger-resources/**", "/webjars/**")
	            .permitAll();
	    }
	    
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    ApiAccessDeniedHandler jwtAccessDeniedHandler() {
	        return new ApiAccessDeniedHandler();
	    }
	
}
