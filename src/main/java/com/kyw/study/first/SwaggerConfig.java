package com.kyw.study.first;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	 @Bean
	    public Docket newsApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .groupName("restnfeel")
	                .apiInfo(apiInfo())
	                .select()
	                .paths(regex("/api/.*"))
	                .build();
	    }
	     
	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("Spring REST API with Swagger")
	                .description("SJH API with Swagger")
	               // .termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
	                .contact("Seo Jeong Hwan")
	                .license("MIT")
	               // .licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
	                .version("1.0")
	                .build();
	    }	     
	    
	   
}