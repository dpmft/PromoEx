package com.somecomp;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

public class ServletInitializer extends SpringBootServletInitializer implements WebApplicationInitializer{
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PromoApplication.class);
	}
}
