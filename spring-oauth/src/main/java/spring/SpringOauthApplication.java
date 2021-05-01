package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//수정
@EnableJpaAuditing
public class SpringOauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringOauthApplication.class, args);
	}

}
