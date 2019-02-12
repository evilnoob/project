FROM openjdk:8u171-jdk AS project_liquibase
ENV TZ Europe/Moscow
WORKDIR /mocs_lb
COPY / /mocs_lb
RUN chmod -R 777 /mocs_lb
ENV LIQUIBASE_URL=${LIQUIBASE_URL}
ENV LIQUIBASE_USER=${LIQUIBASE_USER}
ENV LIQUIBASE_USER_PASSWORD=${LIQUIBASE_USER_PASSWORD}
HEALTHCHECK CMD java -jar /mocs_lb/liquibase.jar --url=${LIQUIBASE_URL} --username=${LIQUIBASE_USER} --password=${LIQUIBASE_USER_PASSWORD} --changeLogFile=master.xml --classpath=mysql-connector-java-6.0.5-bin.jar --driver=com.mysql.cj.jdbc.Driver --logLevel=info update
CMD sleep infinity