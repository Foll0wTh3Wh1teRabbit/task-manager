package ru.nsu.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.nsu.notificationservice.configuration.NotificationServiceConfiguration;
import ru.nsu.taskservice.configuration.TaskServiceConfiguration;
import ru.nsu.userservice.configuration.UserServiceConfiguration;

@Configuration
@Import(
    value = {
        UserServiceConfiguration.class,
        TaskServiceConfiguration.class,
        NotificationServiceConfiguration.class
    }
)
public class TaskManagerConfiguration {
}
