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

	public List<BusinessVo> listContent(BusinessSearchVo businessSearchVo) {
		return sqlSessionTemplate.selectList("businessDao.listContent", businessSearchVo);
	}

	public List<BusinessVo> listContentYear(BusinessSearchVo businessSearchVo) {
		return sqlSessionTemplate.selectList("businessDao.listContentYear", businessSearchVo);
	}

	public int insertContent(BusinessVo businessVo) {
		return sqlSessionTemplate.insert("businessDao.insertContent", businessVo);
	}

	public List<BusinessVo> listReportTypeList(BusinessSearchVo businessSearchVo) {
		return sqlSessionTemplate.selectList("businessDao.listReportType", businessSearchVo);
	}

	public String selectContent(BusinessSearchVo businessSearchVo) {
		return sqlSessionTemplate.selectOne("businessDao.selectContent", businessSearchVo);
	}
}
