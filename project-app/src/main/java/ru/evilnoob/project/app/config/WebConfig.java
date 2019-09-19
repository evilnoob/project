package ru.evilnoob.project.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.evilnoob.project.auth.config.SecurityConfig;

@Configuration
@Import({
        SecurityConfig.class})
public class WebConfig {
}
