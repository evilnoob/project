package ru.evilnoob.project.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories({
        "ru.evilnoob.project.auth.repository"})
/*@PropertySource(
        factory = YamlPropertySourceFactory.class,
        value = {"classpath:application.yml"*//*, "file:${catalina.home}/conf/wcs-app/wrn-app.yml"*//*},
        ignoreResourceNotFound = true)*/
@Slf4j
public class RepositoryConfig {

    @Autowired
    private ConfigurableEnvironment env;

    private String[] getPackagesToScan() {
        return new String[]{
                "ru.evilnoob.project.auth.domain"
        };
    }
}
