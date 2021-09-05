package com.example.snowball;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.snowball.business.service.BusinessService;
import com.example.snowball.business.service.vo.BusinessSearchVo;
import com.example.snowball.corp.service.CorpService;

@SpringBootTest
class SnowballApplicationTests {

	@Autowired
	private BusinessService businessService;

	@Autowired
	private CorpService corpService;

	@Test
	void contextLoads() throws Exception {
		businessService.listContent(new BusinessSearchVo());
	}

	@Test
	void testCorpTxInsert() throws Exception {
		corpService.txInsert();
	}
}
