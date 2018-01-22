/**
 * 
 */
package com.qdynasty.security.validate.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author fei.qin
 *
 */
@Data
@AllArgsConstructor
public class ValidateCode {

	// 验证码
	private String code;

	// 验证码的过期时间，在这个时间之前可以使用，之后不能使用
	private LocalDateTime expireTime;
	
	public ValidateCode(String code, int expireIn){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

	public boolean isExpired() {
		return LocalDateTime.now().isAfter(expireTime);
	}
}
