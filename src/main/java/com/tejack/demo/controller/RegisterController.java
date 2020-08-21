package com.tejack.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.tejack.demo.service.OTPmailService;
import com.tejack.demo.service.RegisterService;
import com.tejack.demo.service.UserDetailsService;

@Controller
@SessionAttributes("name")
public class RegisterController 
{
	@Autowired
	RegisterService service;
	
	@Autowired
	UserDetailsService userDetails;
	
	@Autowired 
	OTPmailService mailService;
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
    public ModelAndView RegisterUser(ModelAndView mv, @RequestParam String name,@RequestParam String email, @RequestParam String password, @RequestParam String c_pass){

    	if(!password.equals(c_pass)) {
    		
    		mv.addObject("errorMessage", "Passwords doesn't match. Please check again!");
    		mv.setViewName("login");
            return mv;	
    	}
    	
    	
    	boolean chk_exists = userDetails.chk_email(email);
    	
    	if(chk_exists) {
    		
    		mv.addObject("errorMessage", "Email Id Already Exists!");
    		mv.setViewName("login");
            return mv;	
    		
    	}
    	
    	
        boolean isUserRegistered = service.RegisterUser(name,email, password);

        if (isUserRegistered) {
        	mv.addObject("errorMessage", "Invalid Credentials");
        	mv.setViewName("login");
            return mv;
        }

		
		/* mv.addObject("errorMessage", "Registered Successfully!!!"); */
        
        boolean mailStatus =  mailService.MailOTP(email);
        
        mv.addObject("email", email);
        
        if(mailStatus) {
        	mv.addObject("errorMessage", "");
        	mv.setViewName("redirect:getotp");
            return mv;
        	
        }else {
        	mv.addObject("errorMessage", "OTP not Send");
        	mv.setViewName("login");
            return mv;
        }
        
    }

}
