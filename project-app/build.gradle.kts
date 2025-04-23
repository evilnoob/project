plugins {
    id("java")
    id("org.springframework.boot")
}

/*tasks.processResources {
    from(rootProject.projectDir.path + "/properties") {
        include("application.yml")
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }
}*/

tasks.processResources {
    filesMatching("application.yml") {
        expand(project.properties)
    }
}

tasks.bootJar {
    archiveFileName.set("ex.jar")
}

dependencies {
    implementation(project(":project-auth"))
    implementation(project(":project-common"))

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jersey")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    //implementation("org.springframework.boot:spring-boot-devtools")
    //implementation("org.apache.commons:commons-lang3:3.7")
    //implementation("org.apache.commons:commons-collections4:4.0")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}
