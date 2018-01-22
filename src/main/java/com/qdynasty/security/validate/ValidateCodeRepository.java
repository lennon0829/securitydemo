/**
 * 
 */
package com.qdynasty.security.validate;

import javax.servlet.http.HttpServletRequest;

import com.qdynasty.security.validate.entity.ValidateCode;
import com.qdynasty.security.validate.enums.ValidateCodeType;

/**
 * @author fei.qin
 *
 */
public interface ValidateCodeRepository {

	public void save(HttpServletRequest request, ValidateCode validateCode, ValidateCodeType validateCodeType);

	public ValidateCode get(HttpServletRequest request, ValidateCodeType validateCodeType);

	public void remove(HttpServletRequest request, ValidateCodeType validateCodeType);
}
