package com.ace.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class IndexController {
	
	@RequestMapping("/")
	public String index(Model model){
		return "index";
	}
	
	/*
	@RequestMapping("/error")
	public String error(Model model){
		return "error";
	}*/
	
	/*@RequestMapping("/index")
	public String index(Model model){
		return "index";
	}
	
	@RequestMapping("/login")
    public String login() {
        return "login";
    }*/
}
