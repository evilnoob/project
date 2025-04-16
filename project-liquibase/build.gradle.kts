buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.liquibase:liquibase-core:4.31.1")
    }
}

plugins {
    id("java")
    id("org.liquibase.gradle") version "3.0.2"
}

/*dependencies {
    liquibaseRuntime("org.liquibase:liquibase-core:4.31.1")
    liquibaseRuntime("info.picocli:picocli:4.6.1")
}*/

/*liquibase {
    activities {
        *//*all {
            properties {
            }
        }*//*
        register("main") {
            driver.set("org.postgresql.Driver")
            changeLogFile = "liquibase/changelog.xml"
            url.set("jdbc:postgresql://localhost:5432/project_db")
            username.set("postgres")
            password.set("postgres")
        }
        *//* register("local") {
             properties {
             }
         }*//*
    }
    runList = "main"
}*/

/*liquibase {
    activities {
        register("main") {
            changeLogFile = "liquibase/changelog.xml"
            url.set("jdbc:postgresql://localhost:5432/project_db")
            username = "postgres"
            password = "postgres"
        }
    }
}*/

liquibase {
    activities.register("main") {
        this.arguments = mapOf(
            "changeLogFile" to "liquibase/changelog.xml",
            "url" to "jdbc:postgresql://localhost:5432/project_db",
            "username" to "postgres",
            "password" to "postgres")
    }
    runList = "main"
}

tasks.bootJar {
    enabled = false
}