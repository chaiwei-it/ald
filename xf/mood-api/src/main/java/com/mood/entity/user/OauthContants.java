package com.mood.entity.user;

import org.springframework.boot.context.FileEncodingApplicationListener;

public class OauthContants {

	public static final String CLIENTID = "123456";//"40D429CEB3780CD317ED760043909F0C";//upp(md5("ART1001-ALADDIN-CLIENTID"))

	public static final String CLIENTKEY = "abcdef";//E56D97424ABC916EEBBE6498E04A3CC9//ART1001-ALADDIN-CLIENTKEY

	
//	public static final String CLIENTID = "40D429CEB3780CD317ED760043909F0C";//upp(md5("ART1001-ALADDIN-CLIENTID"))
	
//	public static final String CLIENTKEY = "E56D97424ABC916EEBBE6498E04A3CC9";//ART1001-ALADDIN-CLIENTKEY
	
	
	// 令牌过期时间,单位秒
	public static final int EXPIRESIN = 604800;

	public static final String MEMBER_ID = "memberId";

	public static final String USERNAME = "username";

	public static final String ACCESS_TOKEN = "otoken";//key为令牌，value为用户信息
																		
	public static final String REFRESH_TOKEN = "rtoken";//key为刷新令牌，value为用户信息
	
	public static final String ACCESS_TOKEN_ID = "otokenid";//key为令牌，value为用户信息
	
	public static final String REFRESH_TOKEN_ID = "rtokenid";//key为刷新令牌，value为用户信息
	
	public static final String USER_ACCESS_TOKEN = "user.otoken.";//key值为用户id，value为用户令牌
	
	public static final String USER_REFRESH_TOKEN = "user.rtoken.";//key为用户id，value为刷新令牌
	
	public static final String USER_ERROR_LOGIN="user.errorlogin.info.";//用户错误登录信息,key为用户登录名，value为错误信息
	
	

}
