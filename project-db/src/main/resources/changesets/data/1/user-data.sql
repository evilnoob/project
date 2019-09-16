--liquibase formatted sql logicalFilePath:s1_user_data.sql
--changeset akhudoleev:user-data-01 runOnChange:false context:prod

INSERT INTO auth.user VALUES (nextval(pg_get_serial_sequence('auth.user', 'user_id')), 'admin', 'qwerty', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);