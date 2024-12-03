package ru.nsu.userservice.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.nsu.common.configuration.EmailConfiguration;
import ru.nsu.common.configuration.SecurityConfiguration;

@Configuration
@Import(
    {
        EmailConfiguration.class,
        SecurityConfiguration.class
    }
)
@ComponentScan(basePackages = "ru.nsu")
@EntityScan(basePackages = "ru.nsu")
@EnableJpaRepositories(basePackages = "ru.nsu")
public class UserServiceConfiguration {
}
