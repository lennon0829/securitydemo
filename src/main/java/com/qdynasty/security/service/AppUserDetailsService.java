package com.qdynasty.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

@Service("appUserDetailsService")
public class AppUserDetailsService implements UserDetailsService, SocialUserDetailsService {

	Log log = LogFactory.get();

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info(" loadUserByUsername  username={}", username);

		String password = passwordEncoder.encode("123456");
		log.info("password={}", password);

		return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		return new SocialUser(userId, "123456", AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
	}

}
