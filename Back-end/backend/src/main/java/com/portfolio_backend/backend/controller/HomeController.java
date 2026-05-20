package com.portfolio_backend.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {

	 @GetMapping("/")
	    public String home() {
	        return "index";  // this refers to templates/index.html
	    }
}
