package tacos;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TacoCloudApplication implements WebMvcConfigurer {
 public static void main(String[] args) {
 SpringApplication.run(TacoCloudApplication.class, args);
 }
 @Override
 public void addViewControllers(ViewControllerRegistry registry) {
 registry.addViewController("/").setViewName("home");
 }
}

