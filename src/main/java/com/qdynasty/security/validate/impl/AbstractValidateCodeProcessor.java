/**
 * 
 */
package com.qdynasty.security.validate.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

import com.qdynasty.exception.ValidateCodeException;
import com.qdynasty.security.validate.ValidateCodeGenerator;
import com.qdynasty.security.validate.ValidateCodeProcessor;
import com.qdynasty.security.validate.ValidateCodeRepository;
import com.qdynasty.security.validate.entity.ValidateCode;
import com.qdynasty.security.validate.enums.ValidateCodeType;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * @author fei.qin
 *
 */
public abstract class AbstractValidateCodeProcessor implements ValidateCodeProcessor {

	private static Log log = LogFactory.get();

	@Autowired
	private Map<String, ValidateCodeGenerator> validateCodeGenerators;

	@Autowired
	private ValidateCodeRepository sessionValidateCodeRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qdynasty.security.validate.ValidateCodeProcessor#create(javax.servlet.
	 * http.HttpServletRequest)
	 */
	@Override
	public void create(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String type = getType();

		// 1、 生成验证码
		ValidateCode validateCode = generate(request, type);

		// 2、保存验证码
		saveValidateCode(request, validateCode, type);

		// 3、发送验证码
		sendValidateCode(response, validateCode);
	}

	private String getType() {
		// imageValidateCodeProcessor
		String type = StringUtils.substringBefore(this.getClass().getSimpleName(),
				this.getClass().getInterfaces().getClass().getSimpleName());
		log.info("type=" + type);
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qdynasty.security.validate.ValidateCodeProcessor#validate(javax.servlet.
	 * http.HttpServletRequest)
	 */
	@Override
	public void validate(HttpServletRequest request) throws Exception {

		String type = getType();

		ValidateCodeType validateCodeType = ValidateCodeType.valueOf(StringUtils.upperCase(type));

		ValidateCode validateCode = sessionValidateCodeRepository.get(request, validateCodeType);

		String loginValidateCode = ServletRequestUtils.getStringParameter(request, type);

		if (StringUtils.isEmpty(loginValidateCode)) {
			throw new ValidateCodeException("loginpage {} validatecode is empty.", type);
		}

		if (validateCode == null) {
			throw new ValidateCodeException("{} validatecode is not exists.", type);
		}

		if (validateCode.isExpired()) {
			sessionValidateCodeRepository.remove(request, validateCodeType);
			throw new ValidateCodeException("{} validatecode is expire.", type);
		}

		if (!StringUtils.equals(validateCode.getCode(), loginValidateCode)) {
			throw new ValidateCodeException("loginpage {} validatecode is wrong.", type);
		}

		// 验证通过，方法未抛一场，则将验证码从session中删掉
		sessionValidateCodeRepository.remove(request, validateCodeType);
	}

	private ValidateCode generate(HttpServletRequest request, String type) throws Exception {

		String name = StringUtils.lowerCase(type) + ValidateCodeGenerator.class.getName();

		ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(name);

		if (validateCodeGenerator == null) {
			throw new ValidateCodeException("Validate Generator {} not exists.", type);
		}

		return validateCodeGenerator.generate(request);
	}

	/**
	 * 保存验证码：例如保存到session中。
	 * 
	 * @param validateCode
	 */
	private void saveValidateCode(HttpServletRequest request, ValidateCode validateCode, String type) throws Exception {
		sessionValidateCodeRepository.save(request, validateCode,
				ValidateCodeType.valueOf(StringUtils.upperCase(type)));
	}

	protected abstract void sendValidateCode(HttpServletResponse response, ValidateCode validateCode) throws Exception;
}
