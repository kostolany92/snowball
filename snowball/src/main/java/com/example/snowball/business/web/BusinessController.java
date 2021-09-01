package com.example.snowball.business.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/business")
public class BusinessController {

	@GetMapping("/status")
	public ModelAndView status(ModelAndView mv) {
		mv.setViewName("business/status");
		return mv;
	}

	@GetMapping("/content")
	public ModelAndView content(ModelAndView mv) {
		mv.setViewName("business/content");
		return mv;
	}

	@GetMapping("/search")
	public ModelAndView search(ModelAndView mv) {
		mv.setViewName("business/search");
		return mv;
	}
}
