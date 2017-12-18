package ${root.basePackage};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author ucodegen
 * @copyright
 */
@SpringBootApplication
@ComponentScan(
        basePackages = {"${root.basePackage}"}
)
public class ApplicationRunner extends SpringBootServletInitializer {

    static ConfigurableApplicationContext ctx = null;

    public static void main(String[] args) {
        ctx = SpringApplication.run(ApplicationRunner.class, args);
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/cors/**");
            }
        };
    }
}