package kr.co.kmarket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 접근권한
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/product/order").hasAnyRole("1", "2", "3", "4", "5") ;
		http.authorizeRequests().antMatchers("/product/orderComplete").hasAnyRole("1", "2", "3", "4", "5") ;
		http.authorizeRequests().antMatchers("/admin/**").permitAll();
		http.authorizeRequests().antMatchers("/mypage/**").hasAnyRole("1", "2", "3", "4", "5") ;
		http.authorizeRequests().antMatchers("/product/list/**").permitAll();
		http.authorizeRequests().antMatchers("/product/view/**").permitAll();
		http.authorizeRequests().antMatchers("/product/cart/**").permitAll();
		
		// 위조 방지
		http.csrf().disable();
		
		// 로그인 설정
		http.formLogin()
			.loginPage("/member/login")
			.defaultSuccessUrl("/index")
			.failureUrl("/member/login?success=100")
			.usernameParameter("uid")
			.passwordParameter("pass");
		
		// 로그아웃 설정
		http.logout()
		.invalidateHttpSession(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
		.logoutSuccessUrl("/index");
		
	}
	
	@Autowired
	private SecurityUserService userService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// 로그인 인증 처리 서비스, 암호화 방식 설정
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	

}
