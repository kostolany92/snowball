package com.example.snowball.business.service;

import java.util.List;

import com.example.snowball.business.service.vo.BusinessSearchVo;
import com.example.snowball.business.service.vo.BusinessVo;

public interface BusinessService {

	List<BusinessVo> listStatus(BusinessSearchVo businessSearchVo) throws Exception;
}
