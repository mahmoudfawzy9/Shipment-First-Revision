package com.company.star.application;

import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import com.company.star.exception.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
public class HttpRequestFactoryConfig {
	@Value("${http.timeout}")
	private Integer httpTimeout;

	@Bean
	public RestTemplate restTemplate(HttpComponentsClientHttpRequestFactory httpRequestFactory) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(httpRequestFactory);
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		return restTemplate;
	}

	@Bean
	public HttpComponentsClientHttpRequestFactory httpRequestFactory() {
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectionRequestTimeout(httpTimeout);
		httpRequestFactory.setConnectTimeout(httpTimeout);
		httpRequestFactory.setReadTimeout(httpTimeout);
		return httpRequestFactory;
	}
}
