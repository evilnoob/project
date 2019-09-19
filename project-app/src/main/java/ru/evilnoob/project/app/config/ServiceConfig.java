package ru.evilnoob.project.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(
        basePackages = {"ru.evilnoob.project.*"},
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Repository.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Service.class)})
public class ServiceConfig {
}
