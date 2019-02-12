--liquibase formatted sql logicalFilePath:V1_003_user.sql
--changeset akhudoleev:1.3 runOnChange:false context:prod

CREATE TABLE auth.user (
  user_id     SERIAL PRIMARY KEY,
  login       VARCHAR(50) UNIQUE     NOT NULL,
  password    VARCHAR(50)            NOT NULL,
  enabled     BOOLEAN                NOT NULL DEFAULT TRUE,
  create_date TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  modify_date TIMESTAMP WITHOUT TIME ZONE
);