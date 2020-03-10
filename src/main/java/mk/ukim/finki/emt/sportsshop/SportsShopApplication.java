package mk.ukim.finki.emt.sportsshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SportsShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsShopApplication.class, args);
	}

}
