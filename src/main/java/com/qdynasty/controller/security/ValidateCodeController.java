/**
 * 
 */
package com.qdynasty.controller.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.qdynasty.constants.AppConstants;
import com.qdynasty.security.validate.ValidateCodeProcessor;
import com.qdynasty.security.validate.ValidateCodeProcessorManager;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author fei.qin
 *
 */
@RestController
public class ValidateCodeController {

	@Autowired
	private ValidateCodeProcessorManager validateCoderProcessManager;

	/*
	 * 创建验证码，可以支持不通的验证类型：图片验证码，断线验证码等。
	 */
	@ApiOperation(value = "创建验证码", notes = "")
	@ApiImplicitParam(name = "type", value = "验证码类型：image", required = false, dataType = "String")
	@GetMapping(value = AppConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
	public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
			throws Exception {

		ValidateCodeProcessor validateCodeProcessor = validateCoderProcessManager.findValidateCodeProcessor(type);

		validateCodeProcessor.create(request, response);
	}
}
