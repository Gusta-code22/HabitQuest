package br.com.Gusta_code22.habitquest.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "HabitQuest API",
                version = "1.5.0",
                description = """
                Gamified habit tracking API where real-life tasks turn into RPG quests,
                experience points, and rewards. Habit reminders are sent via webhooks
                when users fail to complete their daily quests.
                """,
                contact = @Contact(
                        name = "Gustavo Miranda",
                        url = "https://github.com/Gusta-code22"
                ),
                license = @License(
                        name = "MIT License"
                )
        )
)
public class OpenApiConfig {
}