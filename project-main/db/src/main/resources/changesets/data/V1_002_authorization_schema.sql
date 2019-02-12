--liquibase formatted sql logicalFilePath:V1_002_authorization_schema.sql
--changeset akhudoleev:1.2 runOnChange:false context:prod

CREATE SCHEMA IF NOT EXISTS auth;