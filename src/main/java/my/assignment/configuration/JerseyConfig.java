package my.assignment.configuration;

import my.assignment.controller.DictionaryController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        this.registerEndpoints();
    }

    private void registerEndpoints() {
        this.register(DictionaryController.class);
    }

}
