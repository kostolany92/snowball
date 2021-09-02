package com.example.snowball.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.example.snowball.business.service.vo.BusinessSearchVo;
import com.example.snowball.business.service.vo.BusinessVo;

@Repository
public class BusinessDao {

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	public List<BusinessVo> listStatus(BusinessSearchVo businessSearchVo) {
		return sqlSessionTemplate.selectList("businessDao.list", businessSearchVo);
	}
}
