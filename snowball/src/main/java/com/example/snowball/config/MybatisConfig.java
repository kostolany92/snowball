package com.example.snowball.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MybatisConfig {

		@Autowired
		ApplicationContext applicationContext;

		@Bean
		public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
			SqlSessionFactoryBean sessFactory = new SqlSessionFactoryBean();
			sessFactory.setDataSource(dataSource);
			
			Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*Mapper.xml");
			
			sessFactory.setMapperLocations(res);

			return sessFactory.getObject();
		}
		
		@Bean
		public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
			return new SqlSessionTemplate(sqlSessionFactory);
		}
}
