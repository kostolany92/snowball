package com.example.snowball.corp.service;

import java.util.List;

import com.example.snowball.corp.service.vo.CorpSearchVo;
import com.example.snowball.corp.service.vo.CorpVo;

public interface CorpService {

	int insert(CorpVo corpVo) throws Exception;

	int delete() throws Exception;

	int txInsert() throws Exception;

	List<CorpVo> list(CorpSearchVo corpSearchVo) throws Exception;
}
