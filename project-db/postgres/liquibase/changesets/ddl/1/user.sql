--liquibase formatted sql logicalFilePath:s1_user.sql
--changeset akhudoleev:user-01 runOnChange:false context:prod

CREATE TABLE auth.user
(
    user_id     SERIAL PRIMARY KEY,
    login       VARCHAR(50) UNIQUE NOT NULL,
    password    VARCHAR(100)       NOT NULL,
    enabled     BOOLEAN            NOT NULL DEFAULT TRUE,
    create_date TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    modify_date TIMESTAMP WITHOUT TIME ZONE
);