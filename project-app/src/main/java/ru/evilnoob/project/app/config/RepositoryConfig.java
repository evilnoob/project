package ru.evilnoob.project.app.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.jpa.boot.spi.IntegratorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.evilnob.project.common.utils.PropertyUtil;
import ru.evilnob.project.common.utils.YamlPropertySourceFactory;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories({"ru.evilnoob.project.app.repository"})
@PropertySource(
        factory = YamlPropertySourceFactory.class,
        value = {"classpath:application.yml"/*, "file:${catalina.home}/conf/wcs-app/wrn-app.yml"*/},
        ignoreResourceNotFound = true)
@Slf4j
public class RepositoryConfig {

    @Autowired
    private ConfigurableEnvironment env;

    @Bean
    public DataSource dataSource() {
        Properties properties = PropertyUtil.getPropertiesByPrefix(env, "postgresql.", "postgresql.");
        HikariConfig config = new HikariConfig(properties);
        return new HikariDataSource(config);
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        DataSource ds = this.dataSource();
        em.setDataSource(ds);
        em.setPackagesToScan(getPackagesToScan());
        JpaVendorAdapter jva = this.hibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(jva);

        // Set Jpa Properties
        Properties properties = PropertyUtil.getPropertiesByPrefix(env, "hibernate.");
        Map<String, Object> propertyMap = new HashMap<>();
        for (final String name : properties.stringPropertyNames())
            propertyMap.put(name, properties.getProperty(name));
        em.setJpaPropertyMap(propertyMap);

        em.afterPropertiesSet();
        return em.getObject();
    }

    @Bean
    public JpaVendorAdapter hibernateJpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(this.entityManagerFactory());
        return tm;
    }

    private String[] getPackagesToScan() {
        return new String[]{
                "ru.evilnoob.projectt.app.entity"
        };
    }

}
