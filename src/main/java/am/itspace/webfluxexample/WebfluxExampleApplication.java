package am.itspace.webfluxexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class WebfluxExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxExampleApplication.class, args);
    }

}
