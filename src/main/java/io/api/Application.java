package io.api;
import io.api.config.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import io.api.config.SwaggerConfig;
import io.swagger.models.Swagger;

@SpringBootApplication
@Import({ SwaggerConfig.class, WebConfig.class })
public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SpringApplication.run(Application.class, args);
	}

}
