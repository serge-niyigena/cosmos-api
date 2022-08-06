package com.cosmos.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cosmos.filters.JwtFilter;
import com.cosmos.handlers.ApiAccessDeniedHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfigs extends WebSecurityConfigurerAdapter{
	
	 @Autowired private JwtFilter jwtFilter;


	@Value(value = "${default.value.security.actuator-ip}")
    private String actuatorIp;

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.cors().and().csrf().disable()
	            .authorizeRequests()
	            .antMatchers("/auth/login").permitAll()
	          //  .antMatchers("/user").hasAuthority("createUser")
	            .antMatchers("/api/v1/**").authenticated()
	            .antMatchers("/v2/api-docs", "/swagger-ui/**", "/swagger-resources/**", "/webjars/**").permitAll()
	            .antMatchers("/actuator/**").hasIpAddress(actuatorIp)
	            .anyRequest().authenticated()
	            .and().exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler())
	            .and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	           
	    }
	    
	    @Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
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
