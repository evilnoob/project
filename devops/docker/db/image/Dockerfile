FROM postgres:11.2
ENV TZ Europe/Moscow
COPY liquibase-3.6.3-bin.tar.gz /opt/liquibase/
#INSTALL
RUN tar -xzf /opt/liquibase/liquibase-3.6.3-bin.tar.gz -C /opt/liquibase \
    && chmod +x /opt/liquibase/liquibase \
    && ln -s /opt/liquibase/liquibase /usr/local/bin/ \
    && rm /opt/liquibase/liquibase-3.6.3-bin.tar.gz
#JAVA
RUN apt update && \
    apt install default-jdk -y && \
    apt install netcat -y