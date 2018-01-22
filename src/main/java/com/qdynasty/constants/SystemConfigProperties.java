/**
 * 
 */
package com.qdynasty.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author fei.qin
 *
 */
@Component
@PropertySource({ "classpath:system_config.properties" })
public class SystemConfigProperties {

	@Value("${com.qdynasty.system.unkonw_error}")
	private String unKnowError;

	@Value("${com.qdynasty.system.success}")
	private String success;

	@Value("${com.qdynasty.system.primarySchool}")
	private String primarySchool;

	@Value("${com.qdynasty.system.middleSchool}")
	private String middleSchool;

	@Value("${com.qdynasty.system.seckillOver}")
	private String seckillOver;

	@Value("${com.qdynasty.system.codeError}")
	private String codeError;

	public static String UNKONW_ERROR;

	public static String SUCCESS;

	public static String PRIMARY_SCHOOL;

	public static String MIDDLE_SCHOOL;

	public static String SECKILL_OVER;

	public static String CODE_ERROR;

	public SystemConfigProperties() {
		super();
		reLoad();
	}

	public void reLoad() {
		UNKONW_ERROR = unKnowError;
		success = SUCCESS;
		primarySchool = PRIMARY_SCHOOL;
		middleSchool = MIDDLE_SCHOOL;
		seckillOver = SECKILL_OVER;
		codeError = CODE_ERROR;
	}
}
