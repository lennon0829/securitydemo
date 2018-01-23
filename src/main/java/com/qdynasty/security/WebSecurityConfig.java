/**
 * 
 */
package com.qdynasty.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.qdynasty.constants.AppConstants;
import com.qdynasty.constants.SecurityConfigProperties;
import com.qdynasty.security.validate.ValidateCodeSecurityConfig;

/**
 * @author fei.qin
 * @param <T>
 *
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;

	@Autowired
	private SecurityConfigProperties securityConfigProperties;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserDetailsService appUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// 使用表单登录，不再使用默认httpBasic方式
				.formLogin().loginPage(AppConstants.DEFAULT_UNAUTHENTICATION_URL)
				.loginProcessingUrl(AppConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM).and()
				// 验证码拦截
				.apply(validateCodeSecurityConfig).and()
				// 记住我
				.rememberMe().tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(securityConfigProperties.getRememberMeTime())
				.userDetailsService(appUserDetailsService).and().sessionManagement()
				.invalidSessionUrl("/session/invalid").maximumSessions(1).maxSessionsPreventsLogin(false).and().and()
				.logout().logoutUrl("/signOut").logoutSuccessUrl("/register").deleteCookies("JSESSIONID").and()
				.authorizeRequests()
				.antMatchers(AppConstants.DEFAULT_UNAUTHENTICATION_URL,
						AppConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM, AppConstants.DEFAULT_REGISTER_URL,
						AppConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE,
						AppConstants.DEFAULT_SIGN_IN_URL_MOBILE_PAGE, "/register", "/social/info", "/session/invalid",
						"/**/*.js", "/**/*.css", "/**/*.jpg", "/**/*.png", "/**/*.woff2", "/code/*")
				// 以上的请求都不需要认证
				.permitAll().and()
				// 关闭csrf拦截
				.csrf().disable();
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		return tokenRepository;
	}
}
