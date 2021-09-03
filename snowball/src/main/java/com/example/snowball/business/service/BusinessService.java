package com.example.snowball.business.service;

import java.util.List;

import com.example.snowball.business.service.vo.BusinessSearchVo;
import com.example.snowball.business.service.vo.BusinessVo;

public interface BusinessService {

	List<BusinessVo> listContent(BusinessSearchVo businessSearchVo) throws Exception;
	List<BusinessVo> listContentYear(BusinessSearchVo businessSearchVo) throws Exception;
	List<BusinessVo> listReportTypeList(BusinessSearchVo businessSearchVo) throws Exception;
	int insertContent(BusinessVo businessVo) throws Exception;
}
