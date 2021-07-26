package com.summer.frame.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Swagger2 配置</p>
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-23
 */
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        // 请求头中添加token
        List<Parameter> parameters = new ArrayList<Parameter>() {{
            Parameter parameter = new ParameterBuilder()
                    .name("token")
                    .modelRef(new ModelRef("string"))
                    .parameterType("header")
                    .required(false)
                    .build();
            this.add(parameter);
        }};
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Summer API")
                .description("This is a restful api document of Swagger.")
                .version("1.0.0")
                .build();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters);
    }

}