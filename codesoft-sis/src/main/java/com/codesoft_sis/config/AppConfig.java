package com.codesoft_sis.config;

import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

public class AppConfig {

	@Bean
	public SpringTemplateEngine templateEngine() {
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();    
	    templateEngine.addTemplateResolver(new UrlTemplateResolver());
	    return templateEngine;
	}
}
