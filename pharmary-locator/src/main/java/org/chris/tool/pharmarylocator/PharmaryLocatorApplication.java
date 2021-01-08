package org.chris.tool.pharmarylocator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages="org.chris.tool.pharmarylocator.repository")
public class PharmaryLocatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PharmaryLocatorApplication.class, args);
    }

    
}
