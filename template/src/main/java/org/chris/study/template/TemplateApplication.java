package org.chris.study.template;

import java.util.Arrays;

import org.chris.study.template.entity.TemplateUser;
import org.chris.study.template.repository.TemplateUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TemplateApplication {

    private static final Logger log = LoggerFactory.getLogger(TemplateApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TemplateApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ApplicationContext ctx) {
        return (args) -> {

            // inspect beans
            System.out.println("Let's inspect the beans provided by Spring Boot:");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

            // test database functions
            TemplateUserRepository repository = ctx.getBean(TemplateUserRepository.class);

            // save a couple of user
            repository.save(new TemplateUser("Jack", "Bauer"));
            repository.save(new TemplateUser("Kim", "Bauer"));
            repository.save(new TemplateUser("Michelle", "Dessler"));

            // fetch all user
            log.info("Users found with findAll():");
            log.info("-------------------------------");
            for (TemplateUser user : repository.findAll()) {
                log.info(user.toString());
            }
            log.info("");

            // fetch an individual user by ID
            repository.findById(1L).ifPresent(user -> {
                log.info("User found with findById(1L):");
                log.info("--------------------------------");
                log.info(user.toString());
                log.info("");
            });

            // fetch user by last name
            log.info("User found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });
            log.info("");
        };
    }
}
