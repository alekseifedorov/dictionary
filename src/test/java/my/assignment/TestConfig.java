package my.assignment;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
@ComponentScan(basePackages = "my.assignment")
public class TestConfig {
}
