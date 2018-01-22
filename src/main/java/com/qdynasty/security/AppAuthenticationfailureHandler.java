/**
 * 
 */
package com.qdynasty.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qdynasty.enums.ResultEnum;
import com.qdynasty.util.ResultUtil;

/**
 * @author fei.qin
 *
 */
@Component("appAuthenticationfailureHandler")
public class AppAuthenticationfailureHandler extends SimpleUrlAuthenticationFailureHandler {
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());// 服务器内部异常
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper
				.writeValueAsString(ResultUtil.error(ResultEnum.CODE_ERROR.getCode(), exception.getMessage())));
	}
}
