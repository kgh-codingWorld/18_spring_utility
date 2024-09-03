package com.application.utility.chapter06_swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
    public OpenAPI customOpenAPI() {
    	
        return new OpenAPI()
                .info(new Info()
                .title("Swagger 학습")
                .description("문서화 학습 튜토리얼")
                .version("1.0"));
        
    }
}
