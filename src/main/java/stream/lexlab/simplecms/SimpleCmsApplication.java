package stream.lexlab.simplecms;

import org.komamitsu.spring.data.sqlite.EnableSqliteRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSqliteRepositories("stream.lexlab.simplecms.repositories")
public class SimpleCmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleCmsApplication.class, args);
	}

}
