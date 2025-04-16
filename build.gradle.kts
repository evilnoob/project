group = "ru.evilnoob"
version = "1.0"

//apply(from = "buildProjectApp.gradle.kts")
apply(from = "build-project.gradle.kts")
//apply(from = "buildProjectDb.gradle.kts")

val springBootVersion by extra("3.4.4")
val dependecyManagementPluginVersion by extra("1.1.7")
val postgresDriverVersion by extra("42.7.5")
val commonsLangVersion by extra("3.17.0")
val commonsCollectionsVersion by extra("4.4")

plugins {
    id("io.spring.dependency-management")
    id("java")
    id("org.springframework.boot")
}

subprojects {
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")

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

    dependencyManagement{
        dependencies {
            dependency("org.postgresql:postgresql:${postgresDriverVersion}")
            dependency("org.apache.commons:commons-collections:${commonsCollectionsVersion}")
            dependency("org.apache.commons:commons-lang3:${commonsLangVersion}")
            //dependency("org.springframework.cloud:spring-cloud-dependencies:${extra["springCloudVersion"]}")
            //dependency("org.hibernate:hibernate-bom:${extra["hibernateBomVersion"]}")
            //dependency("com.zaxxer:HikariCP:${extra["hikariCPVersion"]}")
            //dependency("org.springframework:spring-framework-bom:${extra["springVersion"]}")
            //dependency("org.junit:junit-bom:${extra["junitVersion"]}")("org.postgresql:postgresql:${postgresDriverVersion}")
        }

    }

    dependencies {
        //Spring Boot
        //implementation("org.springframework.boot:spring-boot-starter-actuator:${springBootVersion}")
        //implementation("org.springframework.boot:spring-boot-starter-data-jpa:${springBootVersion}")
        //implementation("org.springframework.boot:spring-boot-starter-security:${springBootVersion}")
        //implementation("org.springframework.boot:spring-boot-starter-web:${springBootVersion}")
        //implementation("org.springframework.boot:spring-boot-devtools:${springBootVersion}")
        //implementation("org.springframework.boot:spring-boot-starter-webflux:${springBootVersion}")

        //Spring Cloud
        //dependency("org.springframework.cloud:spring-cloud-starter-oauth2:${extra["springCloudVersion"]}")

        //Hibernate
        //dependency("org.hibernate:hibernate-core:${extra["hibernateVersion"]}")
        //dependency("org.hibernate:hibernate-entitymanager:${extra["hibernateVersion"]}")

        //Datasource
        //dependency("com.zaxxer:HikariCP:${extra["hikariCPVersion"]}")

        //PostgreSQL
        //implementation("org.postgresql:postgresql:${postgresDriverVersion}")
        //Other
        //implementation("org.apache.commons:commons-lang3:${commonsLangVersion}")
        //implementation("org.apache.commons:commons-collections:${commonsCollectionsVersion}")

        //Test
        //dependency("org.springframework:spring-test:${extra["springVersion"]}")
        //dependency("junit:junit:${extra["junitVersion"]}")

        //Lombok
        //dependency("org.projectlombok:lombok:${extra["lombokVersion"]}")
    }
}

tasks.bootJar {
    enabled = false
}