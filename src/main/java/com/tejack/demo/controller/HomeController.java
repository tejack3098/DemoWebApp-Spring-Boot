package com.tejack.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.tejack.demo.service.OTP_Service;
import com.tejack.demo.service.UserDetailsService;

@Controller
@SessionAttributes("name")
public class HomeController {
	
	@Autowired
	UserDetailsService userDetails;
	
	@Autowired
	OTP_Service otpService;
	
	 @RequestMapping(value="/verifyOTP", method = RequestMethod.POST)
	    public ModelAndView VerifyOTP(ModelMap model, @RequestParam int user_otp , @RequestParam String userEmail){
		 
		 ModelAndView mv = new ModelAndView();
		 int org_otp = otpService.getOtp(userEmail);
		 
		 if(org_otp == user_otp) {
			 otpService.clearOTP(userEmail);
			 mv.addObject("name", userDetails.getDetail(userEmail));
			 mv.setViewName("redirect:welcome");
			 return mv;
	 
		 }else {
			 mv.addObject("email",userEmail);
			 mv.addObject("errorMessage","OTP doesn't Match");
			 mv.setViewName("redirect:getotp");
			 return mv;
		 }
	    }
	 
	 
	 @RequestMapping(value="/getotp")
	    public ModelAndView GetOTP(@RequestParam String email,@RequestParam String errorMessage){
		 ModelAndView mv = new ModelAndView();
		 	mv.addObject("email",email);
		    mv.addObject("errorMessage", errorMessage);
		    mv.setViewName("getotp");
	        return mv;
	    }
	 
	 @RequestMapping(value="/welcome")
	    public ModelAndView showDashBoard(ModelMap model, @RequestParam String name ){
		    ModelAndView mv = new ModelAndView();
		    mv.addObject("name", name);
		    mv.setViewName("welcome");
	        return mv;
	    }


}