package kcanmin.com.nocean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class NoceanApplication {

  public static void main(String[] args) {
    SpringApplication.run(NoceanApplication.class, args);
  }

}
