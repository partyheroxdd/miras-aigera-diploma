package kz.iitu.miras_aigera_diploma.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI().info(
            new Info().title("Miras Aigera Diploma API").description("Lost found service application")
                .version("v0.0.1"))
        .externalDocs(new ExternalDocumentation().description("Lost found service Documentation"));
  }
}
