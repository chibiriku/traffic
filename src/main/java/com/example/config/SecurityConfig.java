package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Override
	public void configure(WebSecurity web) throws Exception{
		web
			.ignoring()
				.antMatchers("/h2-console/**")
				.antMatchers("/webjars/**")
				.antMatchers("/css/**")
				.antMatchers("/js/**");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception{

		//直リンク許可
		http
			.authorizeRequests()
				.antMatchers("/rpc/login").permitAll()
				.antMatchers("/rpc/test").permitAll()
				.antMatchers("/rpc/list").hasAuthority("ROLE_ADMIN")
				.antMatchers("/rpc/add").hasAuthority("ROLE_ADMIN")
				.anyRequest().authenticated();
		//ログイン処理
		http
			.formLogin(login ->login
				.loginProcessingUrl("/rpc/login")
				.loginPage("/rpc/login")
				.failureUrl("/login?error")
				.usernameParameter("mail")
				.passwordParameter("password")
				.successHandler((request, response, authentication) -> {
		            if (authentication.getAuthorities().stream()
		                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
		                response.sendRedirect("/rpc/list"); // adminの場合のリダイレクト先
		            } else {
		                response.sendRedirect("/rpc/traffic/list"); // その他の場合のデフォルトのリダイレクト先
		            }
		        })
			);
				//.defaultSuccessUrl("/rpc/traffic/list", true));

		http
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		        .logoutSuccessUrl("/login?logout");



		//.antMatchers("/rpc/add").hasAuthority("ROLE_ADMIN")
		//CSRF対策を無効に設定
		//http.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{

		PasswordEncoder encoder = passwordEncoder();
		/*
		auth
			.inMemoryAuthentication()
				.withUser("user")
					.password(encoder.encode("user"))
					.roles("GENERAL")
				.and()
				.withUser("admin")
					.password(encoder.encode("admin"))
					.roles("ADMIN");
		 */

		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(encoder);
	}
}
