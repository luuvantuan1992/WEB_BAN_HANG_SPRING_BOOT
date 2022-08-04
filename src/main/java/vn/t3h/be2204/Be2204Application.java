package vn.t3h.be2204;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class Be2204Application {

    public static void main(String[] args) {
        SpringApplication.run(Be2204Application.class, args);
    }

}
