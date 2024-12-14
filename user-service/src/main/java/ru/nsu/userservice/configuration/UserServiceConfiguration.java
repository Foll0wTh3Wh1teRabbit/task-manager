package ru.nsu.userservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.nsu.common.configuration.EmailConfiguration;

@Configuration
@Import(
    value = {
        EmailConfiguration.class,
        SecurityException.class
    }
)
public class UserServiceConfiguration {
}
