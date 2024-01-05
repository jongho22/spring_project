package com.office.library.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class MultipartResolverConfig {
	
	@Value("#{commInfoProperty['uploadfile.size']}")
	private long fileSize;
	
	@Value("#{commInfoProperty['uploadfile.encoding']}")
	private String fileEncoding;
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		System.out.println("[MutipartResolverConfig] multipartResolver()");
		
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(fileSize);
		multipartResolver.setDefaultEncoding(fileEncoding);
		
		return multipartResolver;
	}
	
}
