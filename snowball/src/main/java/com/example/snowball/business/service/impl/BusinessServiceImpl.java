package com.example.snowball.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.snowball.business.service.BusinessService;
import com.example.snowball.business.service.vo.BusinessSearchVo;
import com.example.snowball.business.service.vo.BusinessVo;

@Service
public class BusinessServiceImpl implements BusinessService {

	@Autowired
	private BusinessDao businessDao;

	@Override
	public List<BusinessVo> list(BusinessSearchVo businessSearchVo) throws Exception {

		// 1. DB 조회
		List<BusinessVo> list = businessDao.listStatus(businessSearchVo);
		// 2. 없으면 새로 조회후 DB INSERT

		// 3. 다시 DB 조회

		// 4.

		return null;
	}
}
