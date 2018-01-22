/**
 * 
 */
package com.qdynasty.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author fei.qin
 *
 */
@Component
@Data
@PropertySource({ "classpath:security_config.properties" })
public class SecurityConfigProperties {

	@Value("${com.qdynasty.security.validate.image.width}")
	private int imageCodePicWidth;

	@Value("${com.qdynasty.security.validate.image.height}")
	private int imageCodePicHeight;

	@Value("${com.qdynasty.security.validate.code.length}")
	private int validateCodeLength;

	@Value("${com.qdynasty.security.validate.code.expiretime}")
	private int expireTime;

	@Value("${com.qdynasty.security.validate.rememberMeTime}")
	private int rememberMeTime;

	/**
	 * 当请求需要身份验证时跳转URL
	 */
	@Value("${com.qdynasty.security.DEFAULT_UNAUTHENTICATION_URL}")
	private String defaultUnauthenticationUrl;

	/**
	 * 默认的用户名密码登录请求处理url
	 */
	@Value("${com.qdynasty.security.DEFAULT_SIGN_IN_PROCESSING_URL_FORM}")
	private String defaultSignInProcessingUrlForm;

	/**
	 * 默认的手机验证码登录请求处理url
	 */
	@Value("${com.qdynasty.security.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE}")
	private String defaultSignInProcessingUrlMobile;

	/**
	 * 默认的手机验证码登录请求处理url
	 */
	@Value("${com.qdynasty.security.DEFAULT_SIGN_IN_URL_MOBILE_PAGE}")
	private String defaultSignInUrlMobilePage;

	/**
	 * 默认的用户注册请求处理url
	 */
	@Value("${com.qdynasty.security.DEFAULT_REGISTER_URL}")
	private String defaultRegisterUrl;

	/**
	 * weixin appID
	 */
	@Value("${com.qdynasty.security.DEFAULT_SOCIAL_WEIXIN_APP_ID}")
	private String defaultSocialWeixinAppId;

	/**
	 * weixin appsECRET
	 */
	@Value("${com.qdynasty.security.DEFAULT_SOCIAL_WEIXIN_APP_SECRET}")
	private String defaultSocialWeixinAppSecret;

	/**
	 * qq appID
	 */
	@Value("${com.qdynasty.security.DEFAULT_SOCIAL_QQ_APP_ID}")
	private String defaultSocialQQAppId;

	/**
	 * qq appsECRET
	 */
	@Value("${com.qdynasty.security.DEFAULT_SOCIAL_QQ_APP_SECRET}")
	private String defaultSocialQQAppSecret;

	/**
	 * weibo appID
	 */
	@Value("${com.qdynasty.security.DEFAULT_SOCIAL_WEIBO_APP_ID}")
	private String defaultSocialWeiboAppId;

	/**
	 * weibo appsECRET
	 */
	@Value("${com.qdynasty.security.DEFAULT_SOCIAL_WEIBO_APP_SECRET}")
	private String defaultSocialWeiboAppSecret;

	/**
	 * 自定义社交social拦截地址 默认/auth (SocialAuthenticationFilter)
	 */
	@Value("${com.qdynasty.security.DEFAULT_SOCIAL_PROCESS_URL}")
	private String defaultSocialProcessUrl;

	/**
	 * 提供商的ID
	 */
	@Value("${com.qdynasty.security.DEFAULT_SOCIAL_QQ_PROVIDER_ID}")
	private String defaultSocialQQProviderId;

	/**
	 * 提供商的ID
	 */
	@Value("${com.qdynasty.security.DEFAULT_SOCIAL_WEIXIN_PROVIDER_ID}")
	private String defaultSocialWeixinProviderId;

	/**
	 * 默认的注册页面
	 */
	@Value("${com.qdynasty.security.DEFAULT_SIGNUP_URL}")
	private String defaultSignupUrl;
}
