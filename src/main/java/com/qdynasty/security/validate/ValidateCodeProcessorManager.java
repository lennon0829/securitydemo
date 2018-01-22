package com.qdynasty.security.validate;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qdynasty.exception.ValidateCodeException;

@Component("validateCoderProcessManager")
public class ValidateCodeProcessorManager {

	@Autowired
	private Map<String, ValidateCodeProcessor> validateCodeProcessors;

	/**
	 * 查找验证码验证处理类
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public ValidateCodeProcessor findValidateCodeProcessor(String type) throws ValidateCodeException {

		//imageValidateCodeProcessor
		String name = StringUtils.lowerCase(type) + ValidateCodeProcessor.class.getName();

		ValidateCodeProcessor validateCodeProcessor = validateCodeProcessors.get(name);

		if (validateCodeProcessor == null) {
			throw new ValidateCodeException("Validate Processor {} not exists.", type);
		}

		return validateCodeProcessor;
	}
}
