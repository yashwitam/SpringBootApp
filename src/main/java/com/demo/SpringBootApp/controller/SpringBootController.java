package com.demo.SpringBootApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpringBootController {
	
	@RequestMapping("/")String home(ModelMap modal) {
		System.out.println("In SpringBootController");
        modal.addAttribute("title", "Dear Customer");
        modal.addAttribute("message", "Welcome to your profile page");
        return "customer";
    }
	
	@RequestMapping("/partials/{page}")String partialHandler(@PathVariable("page") String page) {
		System.out.println("In SpringBootController partials");
        
        return page;
    }
	
}
