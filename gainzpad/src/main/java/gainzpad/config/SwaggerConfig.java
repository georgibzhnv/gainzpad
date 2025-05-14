package gainzpad.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI GainzPadOpenAPI(){
        return new OpenAPI().info(new Info()
                .title("GainzPad API")
                .version("v0.1")
                .description("API documentation for GainzPad"));
    }
}
