#!/bin/bash
set -e -o pipefail

until nc -z -v -w 10 localhost 5432; do
  echo "Waiting for database connection..."
  sleep 3
done

echo "Applying changelog"
liquibase --changeLogFile=/opt/liquibase_postgres/changelog.xml --url=${POSTGRES_URL} --username=${POSTGRES_USER} --password=${POSTGRES_USER} --classpath=/opt/jdbc/postgresql-42.2.5.jar --driver=org.postgresql.Driver update

#--url=${LIQUIBASE_URL} --username=${LIQUIBASE_USER} --password=${LIQUIBASE_PASS} --changeLogFile=master.xml --classpath=mysql-connector-java-6.0.5-bin.jar --driver=com.mysql.cj.jdbc.Driver --logLevel=info update
#CMD sleep infinity
