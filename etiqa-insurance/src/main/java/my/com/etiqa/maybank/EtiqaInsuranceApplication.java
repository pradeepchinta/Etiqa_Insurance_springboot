package my.com.etiqa.maybank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EtiqaInsuranceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtiqaInsuranceApplication.class, args);
	}

}
