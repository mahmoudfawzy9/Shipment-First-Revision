package com.company.star.application;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
@ConditionalOnProperty(name = "com.company.star.logging.enabled", havingValue = "true", matchIfMissing = true)
public class RequestLoggingFilterConfig {
	@Bean
	public CommonsRequestLoggingFilter logFilter(HttpServletRequest request) throws ServletException {
		CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();

		filter.setIncludeQueryString(true);
		filter.setIncludePayload(true);
		filter.setMaxPayloadLength(10000);
		filter.setIncludeHeaders(false);
		filter.setBeforeMessagePrefix("Incoming Request: ");

		return filter;
	}
}
