package com.nevergiveup.datn;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.nevergiveup.datn.entity.Users;
import com.nevergiveup.datn.service.UsersService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UsersService usersService;

	@Autowired
	BCryptPasswordEncoder pe;

//	//cung cap nguon dl dang nhap
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> {
			try {
				Users user = usersService.findByUsername(username);
				String password = user.getPassword();
				Boolean active = user.getIsActive();
				Boolean delete = user.getIsDisable();
				Boolean isActive = false;
				if (active == false || delete == true) {
					isActive = true;
				}
				String[] roles = user.getAuthorities().stream().map(er -> er.getRole().getId())
						.collect(Collectors.toList()).toArray(new String[0]);
				return User.withUsername(username).password(password).disabled(isActive).roles(roles).build();
			} catch (Exception e) {
				throw new UsernameNotFoundException(username + " not found");
			}
		});
	}

//	
//	//phan quyen truy cap
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/order/**", "/account/edit", "/account/repass", "/account/change", "/product/wishlist")
				.authenticated()// kiểm tra đăng nhập authencation
				.antMatchers("/admin/**")// kiểm tra quyên athorization
				.hasAnyRole("STAFF", "ADMIN").antMatchers("/rest/authorities/**", "/assets/admin/authority/index.html")// phải là admin
				.hasAnyRole("ADMIN").anyRequest().permitAll();

		http.formLogin().loginPage("/account/login").loginProcessingUrl("/account/login")
				.defaultSuccessUrl("/account/login/success", false).failureUrl("/account/login/error").permitAll();

		http.rememberMe().tokenValiditySeconds(86400);

		http.exceptionHandling().accessDeniedPage("/account/unauthoried");

		http.logout().logoutUrl("/account/logoff").logoutSuccessUrl("/account/logoff/success");

		// Oauth2
		http.oauth2Login().loginPage("/account/login").defaultSuccessUrl("/oauth2/login/success", true)
				.failureUrl("/account/login/error").authorizationEndpoint().baseUri("/oauth2/authorization");
	}

//	//co che ma hoa mat khau
	@Bean
	public BCryptPasswordEncoder getpPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	
	// cho phep truy xuat rest api tu ben ngoai
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
}
