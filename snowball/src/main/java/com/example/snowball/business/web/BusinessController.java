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
	@RequestMapping("/getContentYearList")
	public Map<String, Object> getContentYearList(BusinessSearchVo businessSearchVo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("contentYearList", businessService.listContentYear(businessSearchVo));
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/getReportTypeList")
	public Map<String, Object> getReportTypeList(BusinessSearchVo businessSearchVo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("reportTypeList", businessService.listReportTypeList(businessSearchVo));
		
		return map;
	}
}
