package ru.evilnob.project.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.StreamSupport;

public class PropertyUtil {
    public static Properties getProperties(String file) {
        if (file.contains("file:///")) {
            try {
                File resource = new File(file.replace("file://", ""));
                Properties properties = new Properties();
                properties.load(new FileInputStream(resource));
                return properties;
            } catch (IOException e) {
                throw new RuntimeException("Error read property file", e);
            }
        } else {
            try {
                ClassPathResource resource = new ClassPathResource(file);
                Properties properties = new Properties();
                properties.load(resource.getInputStream());
                return properties;
            } catch (IOException e) {
                throw new RuntimeException("Error read property file", e);
            }
        }
    }

    public static Properties getPropertiesByPrefix(ConfigurableEnvironment env, String prefixToFind, String prefixToTrim) {
        Properties properties = new Properties();
        MutablePropertySources propertySources = env.getPropertySources();
        StreamSupport.stream(propertySources.spliterator(), false)
                .filter(ps -> ps instanceof EnumerablePropertySource)
                .map(ps -> ((EnumerablePropertySource) ps).getPropertyNames())
                .flatMap(Arrays::<String>stream)
                .filter(propName -> propName.startsWith(prefixToFind))
                .forEach(propName -> {
                    String key = propName;
                    if (!StringUtils.isEmpty(prefixToTrim)) {
                        key = key.replaceFirst(prefixToTrim, "");
                    }
                    properties.setProperty(key, env.getProperty(propName));
                });

        return properties;
    }

    public static Properties getPropertiesByPrefix(ConfigurableEnvironment env, String prefix) {
        return getPropertiesByPrefix(env, prefix, null);
    }
}
