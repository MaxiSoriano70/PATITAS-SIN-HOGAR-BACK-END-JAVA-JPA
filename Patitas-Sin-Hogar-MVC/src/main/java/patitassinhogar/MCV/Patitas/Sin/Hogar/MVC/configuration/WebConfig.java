package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
@Configuration
public class WebConfig {
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5500/")
                .allowedMethods("GET", "POST", "PUT","DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
