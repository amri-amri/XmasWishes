package priv.amri.xmaswishes.northpole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "priv.amri.xmaswishes.model")
@EnableJpaRepositories(basePackages = "priv.amri.xmaswishes.model")
public class Northpole {

    public static void main(String[] args) {
        SpringApplication.run(Northpole.class, args);
    }
}
