FROM project-postgres-java AS project-db
ENV TZ Europe/Moscow
COPY liquibase /opt/liquibase_postgres/
COPY postgresql-42.2.5.jar /opt/jdbc/
COPY run_liquibase.sh /usr/local/bin/
RUN chmod 777 /usr/local/bin/run_liquibase.sh