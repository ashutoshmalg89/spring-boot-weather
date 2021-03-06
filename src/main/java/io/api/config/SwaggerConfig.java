package io.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    @Bean
    public Docket api() {
    	return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build().apiInfo(apiInfo());
    			
    		
    }
    
    private ApiInfo apiInfo() {
    	
    	Contact contact = new Contact("Ashutosh Malgaonkar", "http://www.linkedin.com/in/ashutosh8970", "ashutoshmalg89@hotmail.com");
    	ApiInfo apiInfo = new ApiInfo("ClearSky SpringMVC", "REST API to access weather data", "1.0.0", "TnC", contact, "MIT", "https://opensource.org/licenses/MIT");
    	
    	
    	return apiInfo;
    }
    
   
}