package com.bamboo.config;

import com.bamboo.annotation.ApiController;
import com.bamboo.annotation.WebApiController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @data 2019/9/5 9:54
 * @since JDK1.8
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket defaultApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("default")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(ApiController.class))
                .paths(PathSelectors.any()).build();
    }

    @Bean
    public Docket webApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("webApi")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(WebApiController.class))
                .paths(PathSelectors.any()).build();
    }

    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个    // 预览地址:swagger-ui.html
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger api文档")
                .description("接口访问地址：http://localhost:8080/")
                .termsOfServiceUrl("http://localhost:8080/")
                .version("1.0")
                .build();
    }
}
