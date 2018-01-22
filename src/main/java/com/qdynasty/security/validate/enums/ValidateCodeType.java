/**
 * 
 */
package com.qdynasty.security.validate.enums;

import com.qdynasty.constants.AppConstants;

/**
 * @author fei.qin
 *
 */
public enum ValidateCodeType {

	IMAGE {

		@Override
		public String getParamNameOnValidate() {
			return AppConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
		}

	},

	SMS {
		@Override
		public String getParamNameOnValidate() {
			return AppConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
		}
	};

	/**
	 * 校验时从请求中获取的参数的名字
	 * 
	 * @return
	 */
	public abstract String getParamNameOnValidate();
}
