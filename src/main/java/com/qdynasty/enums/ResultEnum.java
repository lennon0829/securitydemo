package com.qdynasty.enums;

import com.qdynasty.constants.SystemConfigProperties;

/**
 * Created on 2017/11/7.
 *
 * @author zlf
 * @since 1.0
 */
public enum ResultEnum {
	UNKONW_ERROR(-1, SystemConfigProperties.UNKONW_ERROR), 
	SUCCESS(0, SystemConfigProperties.SUCCESS), 
	PRIMARY_SCHOOL(100, SystemConfigProperties.PRIMARY_SCHOOL), 
	MIDDLE_SCHOOL(101, SystemConfigProperties.MIDDLE_SCHOOL), 
	SECKILL_OVER(102, SystemConfigProperties.SECKILL_OVER), 
	CODE_ERROR(103, SystemConfigProperties.CODE_ERROR);

	private Integer code;

	private String msg;

	ResultEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
