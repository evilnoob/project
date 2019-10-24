--liquibase formatted sql logicalFilePath:s1_authorization_schema.sql
--changeset akhudoleev:authorization-schema-01 runOnChange:false context:prod

CREATE SCHEMA IF NOT EXISTS auth;