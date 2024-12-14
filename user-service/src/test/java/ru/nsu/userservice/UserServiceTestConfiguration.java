package ru.nsu.userservice;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EntityScan(basePackages = "ru.nsu")
@EnableJpaRepositories(basePackages = "ru.nsu")
@ComponentScan(basePackages = "ru.nsu")
@EnableAutoConfiguration
public class UserServiceTestConfiguration {
}
