/**
 * 
 */
package com.qdynasty.exception;

import org.springframework.security.core.AuthenticationException;

import cn.hutool.core.util.StrUtil;

/**
 * @author fei.qin
 *
 */
public class ValidateCodeException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3845059667422253908L;

	public ValidateCodeException(String msgFormat, Object value) {
		super(StrUtil.format(msgFormat, value));
	}

	public ValidateCodeException(String msg) {
		super(msg);
	}
}
