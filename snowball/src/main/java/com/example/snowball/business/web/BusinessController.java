package com.example.snowball.business.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.snowball.business.service.BusinessService;
import com.example.snowball.business.service.vo.BusinessSearchVo;

@Controller
@RequestMapping("/business")
public class BusinessController {

	@Autowired
	private BusinessService businessService;

	@RequestMapping("/status")
	public ModelAndView status(ModelAndView mv) {
		mv.setViewName("business/status");
		return mv;
	}

	@RequestMapping("/content")
	public ModelAndView content(ModelAndView mv) {
		mv.setViewName("business/content");
		return mv;
	}

	@RequestMapping("/search")
	public ModelAndView search(ModelAndView mv) {
		mv.setViewName("business/search");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/getStatusList")
	public Map<String, Object> getStatusList(BusinessSearchVo businessSearchVo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("statusList", businessService.listStatus(businessSearchVo));
		
		return map;
	}
}
