group = "ru.evilnoob"
version = "1.0"

//apply(from = "buildProjectApp.gradle.kts")
//apply(from = "buildProjectCommon.gradle.kts")
//apply(from = "buildProjectDb.gradle.kts")

//extra["springBootVersion"] = "3.12.0"
val springBootVersion = "3.4.4"
val dependecyManagementPluginVersion = "1.1.7"
val liquibaseGradlePluginVersion = "3.0.2"
val postgresDriverVersion = "42.7.5"

buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
            isAllowInsecureProtocol = true
        }
        maven {
            url = uri("https://repo.spring.io/milestone/")
            isAllowInsecureProtocol = true
        }
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
        classpath("io.spring.gradle:dependency-management-plugin:${dependecyManagementPluginVersion}")
        classpath("org.liquibase:liquibase-gradle-plugin:${liquibaseGradlePluginVersion}")
    }
}

extra["commonsLangVersion"] = "3.12.0"
extra["liquibaseVersion"] = "4.3.5"
extra["liquibaseGradleVersion"] = "2.0.4"


/*plugins {
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.0"
    id("java")
}*/

subprojects {
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")

    repositories {
        mavenLocal()
        mavenCentral()
        maven(url = "https://maven.atlassian.com/3rdparty/")
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "https://repo.spring.io/milestone/")
        maven(url = "http://jaspersoft.artifactoryonline.com/jaspersoft/third-party-ce-artifacts/")
    }

    dependencyManagement {
        dependencies {
            //Spring Boot
            dependency("org.springframework.boot:spring-boot-starter-actuator:${springBootVersion}")
            dependency("org.springframework.boot:spring-boot-starter-data-jpa:${springBootVersion}")
            dependency("org.springframework.boot:spring-boot-starter-data-mongodb:${springBootVersion}")
            dependency("org.springframework.boot:spring-boot-starter-jersey:${springBootVersion}")
            dependency("org.springframework.boot:spring-boot-starter-security:${springBootVersion}")
            dependency("org.springframework.boot:spring-boot-starter-web:${springBootVersion}")
            dependency("org.springframework.boot:spring-boot-devtools:${springBootVersion}")
            dependency("org.springframework.boot:spring-boot-starter-webflux:${springBootVersion}")

            //Spring Cloud
            //dependency("org.springframework.cloud:spring-cloud-starter-oauth2:${extra["springCloudVersion"]}")

            //Hibernate
            //dependency("org.hibernate:hibernate-core:${extra["hibernateVersion"]}")
            //dependency("org.hibernate:hibernate-entitymanager:${extra["hibernateVersion"]}")

            //Datasource
            //dependency("com.zaxxer:HikariCP:${extra["hikariCPVersion"]}")

            //PostgreSQL
            dependency("org.postgresql:postgresql:$ХpostgresDriverVersion}")

            dependency("org.apache.commons:commons-lang3:${extra["commonsLangVersion"]}")

            //Test
            //dependency("org.springframework:spring-test:${extra["springVersion"]}")
            //dependency("junit:junit:${extra["junitVersion"]}")

            //Lombok
            //dependency("org.projectlombok:lombok:${extra["lombokVersion"]}")
        }
    }
}
