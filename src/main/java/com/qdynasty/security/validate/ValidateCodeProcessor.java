/**
 * 
 */
package com.qdynasty.security.validate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author fei.qin
 *
 */
public interface ValidateCodeProcessor {

	/**
	 * 1、生成验证码 2、在服务器端保存验证码 3、将验证码发送到浏览器
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void create(HttpServletRequest request, HttpServletResponse response) throws Exception;

	/**
	 * 根据客户端填的验证码，与session中保存的验证码进行对比
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void validate(HttpServletRequest request) throws Exception;
}
