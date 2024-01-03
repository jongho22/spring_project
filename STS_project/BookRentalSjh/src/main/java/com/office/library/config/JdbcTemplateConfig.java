package com.office.library.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class JdbcTemplateConfig {

	@Value("#{infoProperty['db.driver']}")
	private String dbDriver;
	
	@Value("#{infoProperty['db.url']}")
	private String dbUrl;
	
	@Value("#{infoProperty['db.username']}")
	private String dbUsername;
	
	@Value("#{infoProperty['db.password']}")
	private String dbPassword;
	
	@Bean
	public DataSource dataSource() {
		System.out.println("[JdbcTemplateConfig] dataSource()");
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(dbDriver);
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);
		
		return dataSource;
		
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		System.out.println("[JdbcTemplateConfig] jdbcTemplate()");
		
		return  new JdbcTemplate(dataSource());
		
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		System.out.println("[JdbcTemplateConfig] transactionManager()");
		
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource());
		
		return transactionManager;
		
	}
	
}
