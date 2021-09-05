package com.example.snowball.corp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.example.snowball.corp.service.vo.CorpSearchVo;
import com.example.snowball.corp.service.vo.CorpVo;

@Repository
public class CorpDao {

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	public int insert(CorpVo corpVo) {
		return sqlSessionTemplate.insert("corpDao.insert", corpVo);
	}

	public int delete() {
		return sqlSessionTemplate.delete("corpDao.delete");
	}

	public List<CorpVo> list(CorpSearchVo corpSearchVo) {
		return sqlSessionTemplate.selectList("corpDao.list", corpSearchVo);
	}

}
