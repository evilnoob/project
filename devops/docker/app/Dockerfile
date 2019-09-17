FROM openjdk:8u171-jdk AS project_app
ENV TZ Europe/Moscow
WORKDIR /app
COPY project-app.jar application.yml ./
RUN sh -c 'touch /app/project-app.jar'
EXPOSE 8080 8000
ENTRYPOINT ["java", \
    "-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n", \
    "-Djava.security.egd=file:/dev/./urandom", \
    "-jar","/app/project-app.jar"]