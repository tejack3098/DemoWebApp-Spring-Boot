package com.tejack.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.tejack.demo.model.User;
import com.tejack.demo.service.LoginService;
import com.tejack.demo.service.UserDetailsService;

@Controller
@SessionAttributes("name")
public class LoginController {

	@Autowired
	LoginService service;

	@Autowired
	UserDetailsService userService;
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView LoginUserPage(ModelAndView mv, @RequestParam String email, @ModelAttribute User user) {

		boolean isValidUser = service.validateUser(user);

		if (!isValidUser) {
			mv.addObject("errorMessage", "Invalid Credentials");
			mv.setViewName("login");
			return mv;
		}

		mv.addObject("name", userService.getusername(user));
		mv.setViewName("redirect:welcome");
		return mv;
	}

}
