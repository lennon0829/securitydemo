/**
 * 
 */
package com.qdynasty.security.validate;

import javax.servlet.http.HttpServletRequest;

import com.qdynasty.security.validate.entity.ValidateCode;

/**
 * @author fei.qin
 *
 */
public interface ValidateCodeGenerator {

	/**
	 * 生成验证码
	 * 
	 * @return
	 */
	ValidateCode generate(HttpServletRequest request);
}
