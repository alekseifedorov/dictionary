package my.assignment.configuration;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import my.assignment.controller.DictionaryController;
import my.assignment.controller.DictionaryExceptionHandler;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class JerseyConfig extends ResourceConfig {

    JerseyConfig() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setResourcePackage("my.assignment");

        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);
        this.register(DictionaryController.class);
        this.register(DictionaryExceptionHandler.class);

        BeanConfig config = new BeanConfig();
        config.setSchemes(new String[]{"http", "https"});
        config.setBasePath("/");
        config.setResourcePackage("my.assignment");
        config.setPrettyPrint(true);
        config.setScan(true);
    }
}
