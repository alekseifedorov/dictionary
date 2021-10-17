package my.assignment.configuration;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import my.assignment.controller.DictionaryController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class JerseyConfig extends ResourceConfig {

    @PostConstruct
    public void init() {
        this.configureSwagger();
    }

    private void configureSwagger() {
        this.packages("my.assignment");
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);
        this.register(DictionaryController.class);

        BeanConfig config = new BeanConfig();
        config.setSchemes(new String[]{"http", "https"});
        config.setBasePath("/");
        config.setResourcePackage("my.assignment");
        config.setPrettyPrint(true);
        config.setScan(true);
    }
}
