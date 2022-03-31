package com.company.star.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class EmailTemplateConfiguration {

	@Bean
	public ITemplateResolver templateResolver()
	{
	    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	    templateResolver.setPrefix("templates/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode(TemplateMode.HTML);
	    //templateResolver.setCharacterEncoding("UTF-8");

	    return templateResolver;
	}

	@Bean
	public TemplateEngine templateEngineConfig()
	{
	    TemplateEngine templateEngine = new TemplateEngine();
	    templateEngine.setTemplateResolver(this.templateResolver());
	    return templateEngine;
	}
}
