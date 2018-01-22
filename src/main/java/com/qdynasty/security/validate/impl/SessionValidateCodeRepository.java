/**
 * 
 */
package com.qdynasty.security.validate.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.qdynasty.constants.AppConstants;
import com.qdynasty.security.validate.ValidateCodeRepository;
import com.qdynasty.security.validate.entity.ValidateCode;
import com.qdynasty.security.validate.enums.ValidateCodeType;

/**
 * @author fei.qin
 *
 */
@Component("sessionValidateCodeRepository")
public class SessionValidateCodeRepository implements ValidateCodeRepository {

	/**
	 * spring-social 操作session得工具类
	 */
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	/**
	 * 根据验证码类型，将验证码保存到session中不同的KEY中
	 */
	@Override
	public void save(HttpServletRequest request, ValidateCode validateCode, ValidateCodeType validateCodeType) {
		sessionStrategy.setAttribute(new ServletWebRequest(request), getSessionKey(validateCodeType),
				validateCode.getCode());
	}

	/**
	 * 根据验证码类型，到session中获取对应的验证码
	 */
	@Override
	public ValidateCode get(HttpServletRequest request, ValidateCodeType validateCodeType) {

		return (ValidateCode) sessionStrategy.getAttribute(new ServletWebRequest(request),
				getSessionKey(validateCodeType));
	}

	/**
	 * 根据验证码类型，删除session中对应的验证码
	 */
	@Override
	public void remove(HttpServletRequest request, ValidateCodeType validateCodeType) {
		sessionStrategy.removeAttribute(new ServletWebRequest(request), getSessionKey(validateCodeType));
	}

	/**
	 * 构建验证码翻入session得可以
	 *
	 * @return
	 */
	private String getSessionKey(ValidateCodeType validateCodeType) {
		return AppConstants.SESSION_VALIDATE_KEY_PREFIX + StringUtils.upperCase(validateCodeType.toString());
	}
}
