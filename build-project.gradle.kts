//DEPLOY
//task buildAndDeployAll(type: GradleBuild) {
//    group "deploy"
//    setTasks([
//            "buildLocalArtifacts",
//            "composeContainers"
//    ])
//}
//
//task redeployAll(type: GradleBuild) {
//    group "redeploy"
//    setTasks([
//            "killRmRmiAllContainersAndImages",
//            "buildAndDeployAll"
//    ])
//}
//
////BUILD
//task buildLocalArtifacts(type: GradleBuild) {
//    group "devenv"
//    setTasks([
//            "cleanArtifacts",
//            "buildAndCopyAppArtifacts",
//            "copyMainLiquibaseScripts",
//            "copyDockerFiles"
//    ])
//}
//
//task cleanArtifacts(type: Delete) {
//    group "deploy"
//    delete "${rootDir}/artifact"
//}
//
//task copyDockerFiles(type: Copy) {
//    from "${rootDir}/devops/docker/"
//    into "${rootDir}/artifact/project"
//}
//
//task composeContainers(type: GradleBuild) {
//    setTasks([
//            "dockerBuildDbImage",
//            "dockerComposeAll"
//    ])
//}
//
////DOCKER TECH TASKS
//task killRmRmiAllContainersAndImages(type: GradleBuild) {
//    setTasks([
//            "killRmRmiDB",
//            "killRmRmiApp"
//    ])
//}

val isWindows = System.getProperty("os.name").lowercase().contains("windows")
val runningEnvironment = if (isWindows) "cmd" else "bash"
val parameter = if (isWindows) "/c" else "-c"

tasks.register<Exec>("dockerComposeAll") {
    commandLine(runningEnvironment, parameter, "docker-compose -f ${rootDir}/project.yml up -d")
}

tasks.register<Exec>("execExportKeycloakRealm") {
    commandLine(runningEnvironment, parameter, "docker exec keycloak sh -c \"./opt/keycloak/bin/kc.sh export --dir /tmp/keycloak/ --users realm_file\"")
    isIgnoreExitValue = true
    }

tasks.register<Exec>("copyExportKeycloakRealmToProject") {
    commandLine(runningEnvironment, parameter, "docker cp keycloak:/tmp/keycloak ./")
}

tasks.register<GradleBuild>("dockerCompose") {
    group = "deploy"
    tasks = listOf("dockerComposeAll")
}

tasks.register<GradleBuild>("liquibaseUpdate") {
    group = "deploy"
    tasks = listOf("project-liquibase:update")
}

tasks.register<GradleBuild>("exportKeycloakRealm") {
    group = "deploy"
    tasks = listOf("execExportKeycloakRealm", "copyExportKeycloakRealmToProject")
}