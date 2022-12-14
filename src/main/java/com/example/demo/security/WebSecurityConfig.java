package com.example.demo.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${server.host.front}")
	private String frontHost;

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration configuration = new CorsConfiguration();

		configuration.addAllowedOrigin(frontHost);
		configuration.addAllowedMethod("*");
		configuration.addAllowedHeader("*");
		configuration.setMaxAge((long) 3600);
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.httpBasic().and()
				.csrf().disable()
				.headers()
				.frameOptions().sameOrigin()
				.and().cors().and()
				.authorizeRequests()
				.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
				.antMatchers("/loginInsert").authenticated().antMatchers("/index").permitAll()
					.anyRequest().authenticated();
	}

	@Bean
	public UserDetailsService users() {
		User.UserBuilder users = User.withDefaultPasswordEncoder();
		UserDetails user = users
				.username("happydaddy")
				.password("{noop}1234")
				.roles("USER")
				.build();
		UserDetails admin = users
				.username("angrydaddy")
				.password("{noop}1234")
				.roles("USER")
				.build();
		UserDetails user4 = users
				.username("jin")
				.password("{noop}1234")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user, admin,user4);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("happydaddy")
				.password("{noop}1234")
				.roles("USER")
				.and()
				.withUser("angrydaddy")
				.password("{noop}1234")
				.roles("USER")
				.and()
				.withUser("jin")
				.password("{noop}1234")
				.roles("USER")
				.and()
				.withUser("guest")
				.password("{noop}1234")
				.roles("GUEST");
	}

}