/**
 * 
 */
package com.qdynasty.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author fei.qin
 *
 */
@Controller
public class IndexController {

	@GetMapping(value = { "index", "/" })
	public String index() {
		return "index";
	}

	@GetMapping(value = "login")
	public ModelAndView login(Map<String, String> map) {
		return new ModelAndView("login", map);
	}

	@GetMapping(value = "register")
	public ModelAndView register(Map<String, String> map) {
		return new ModelAndView("register", map);
	}
}
