/**
 * 
 */
package com.qdynasty.controller.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.ServletWebRequest;

import com.qdynasty.constants.AppConstants;
import com.qdynasty.model.User;

/**
 * @author fei.qin
 *
 */
@Controller
public class UserController {

	@Autowired
	private ProviderSignInUtils providerSignInUtils;

	@GetMapping(value = AppConstants.DEFAULT_UNAUTHENTICATION_URL)
	public String require() {
		return "login";
	}

	@PostMapping(value = AppConstants.DEFAULT_REGISTER_URL)
	public String register(User user, HttpServletRequest request) {

		providerSignInUtils.doPostSignUp(user.getUsername(), new ServletWebRequest(request));
		return "login";
	}
}
