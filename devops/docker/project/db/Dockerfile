FROM openjdk:8u171-jdk AS project-main_liquibase
ENV TZ Europe/Moscow
WORKDIR /project_liquibase
COPY / /project_liquibase
RUN chmod -R 777 /project_liquibase
ENV LIQUIBASE_URL=${LIQUIBASE_URL}
ENV LIQUIBASE_USER=${LIQUIBASE_USER}
ENV LIQUIBASE_USER_PASSWORD=${LIQUIBASE_USER_PASSWORD}
HEALTHCHECK CMD java -jar /project_liquibase/liquibase.jar --url=${LIQUIBASE_URL} --username=${LIQUIBASE_USER} --password=${LIQUIBASE_USER_PASSWORD} --changeLogFile=master.xml --classpath=postgresql-42.2.5.jar --driver=org.postgresql.Driver --logLevel=info update
CMD sleep infinity