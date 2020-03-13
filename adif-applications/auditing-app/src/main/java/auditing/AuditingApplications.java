package auditing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"auditing"})
@EntityScan(basePackages = {"auditing"})
public class AuditingApplications {
    public static void main(String[] args) {
        SpringApplication.run(AuditingApplications.class);
    }
}
