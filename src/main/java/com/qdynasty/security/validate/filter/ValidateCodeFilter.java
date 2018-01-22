package com.qdynasty.security.validate.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.qdynasty.constants.SecurityConfigProperties;
import com.qdynasty.exception.ValidateCodeException;
import com.qdynasty.security.validate.ValidateCodeProcessorManager;
import com.qdynasty.security.validate.enums.ValidateCodeType;

import cn.hutool.core.util.ReUtil;

@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

	/**
	 * 存放所有需要检验验证码的url
	 */
	private static Map<String, ValidateCodeType> urlMap = new HashMap<>();

	/**
	 * 验证码校验失败处理器
	 */
	@Autowired
	private AuthenticationFailureHandler appAuthenticationfailureHandler;

	@Autowired
	private SecurityConfigProperties securityConfigProperties;

	@Autowired
	private ValidateCodeProcessorManager validateCoderProcessManager;

	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();

		urlMap.put(securityConfigProperties.getDefaultSignInProcessingUrlForm(), ValidateCodeType.IMAGE);
		urlMap.put(securityConfigProperties.getDefaultSignInProcessingUrlMobile(), ValidateCodeType.SMS);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		ValidateCodeType validateCodeType = getValidateCodeType(request);

		try {
			if (validateCodeType != null) {
				validateCoderProcessManager
						.findValidateCodeProcessor(StringUtils.lowerCase(validateCodeType.toString()))
						.validate(request);
			}
		} catch (Exception e) {

			if (e instanceof ValidateCodeException) {
				appAuthenticationfailureHandler.onAuthenticationFailure(request, response, (ValidateCodeException) e);
			} else {
				appAuthenticationfailureHandler.onAuthenticationFailure(request, response,
						new ValidateCodeException(e.getMessage()));
			}
			return;
		}

		filterChain.doFilter(request, response);
	}

	/**
	 * 获取校验码的类型，如果当前请求不需要校验，则返回null
	 *
	 * @param request
	 * @return
	 */
	private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
		ValidateCodeType result = null;
		if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
			Set<String> urls = urlMap.keySet();
			for (String url : urls) {
				if (ReUtil.isMatch(url, request.getRequestURI())) {
					result = urlMap.get(url);
				}
			}
		}
		return result;
	}
}
