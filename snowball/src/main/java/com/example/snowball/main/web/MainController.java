package com.example.snowball.main.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/main")
public class MainController {

	@GetMapping("/main")
    public ModelAndView temp(ModelAndView mv) {
    	mv.setViewName("main/main");
        return mv;
    }

}
