package noelspring.helloboot;

import noelspring.config.MySpringBootAnnotation;
import org.springframework.boot.SpringApplication;

@MySpringBootAnnotation
public class HellobootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HellobootApplication.class, args);
	}


}
