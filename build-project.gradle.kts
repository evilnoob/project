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


tasks.register<Exec>("dockerComposeAll") {
    val isWindows = System.getProperty("os.name").lowercase().contains("windows")
    val runningEnvironment = if (isWindows) "cmd" else "bash"
    val parameter = if (isWindows) "/c" else "-c"

    commandLine(runningEnvironment, parameter, "docker-compose -f ${rootDir}/project.yml up -d")
}

tasks.register<GradleBuild>("dockerCompose") {
    group = "deploy"
    tasks = listOf("dockerComposeAll")
}

tasks.register<GradleBuild>("liquibaseUpdate") {
    group = "deploy"
    tasks = listOf("project-liquibase:update")
}