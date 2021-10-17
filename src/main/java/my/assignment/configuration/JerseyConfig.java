package my.assignment.configuration;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig {

    @Bean
    public ResourceConfig jaxrsResourceConfig(){
        return new ResourceConfig().packages("my.assignment");
    }
}
