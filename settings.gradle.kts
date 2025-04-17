pluginManagement {
    val springBootVersion by extra("3.4.4")
    val dependecyManagementPluginVersion by extra("1.1.7")
    repositories {
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
            isAllowInsecureProtocol = true
        }
    }
    plugins {
        id("io.spring.dependency-management") version dependecyManagementPluginVersion
        id("java")
        id("org.springframework.boot") version springBootVersion
    }
}

rootProject.name = "project"
include("project-app", "project-auth", "project-common", "project-liquibase", "project-oauth2-auth")
include("project-oauth2-resource-server")
