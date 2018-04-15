package com.revature.hydra.curriculum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
public class HydraCurriculumServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HydraCurriculumServiceApplication.class, args);
    }

    /**
     * Produces a bean used by the Springfox framework to produce Swagger 2
     * documentation.
     * 
     * <br>
     * <br>
     * <b>Last Modified:</b>
     * <pre style="margin:0;border:0;padding:0;">    15 April 2018</pre>
     * 
     * @return A bean to be used to build Swagger 2 documentation.
     * 
     * @author Jennifer Fox (1802-Matt)
     */
    @Bean
    public Docket generateSwaggerBuilder() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }
}
