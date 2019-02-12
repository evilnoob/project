--liquibase formatted sql logicalFilePath:V1_003_user_data.sql
--changeset akhudoleev:1.4 runOnChange:false context:prod

INSERT INTO auth.user VALUES (NULL, 'admin', 'qwerty', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);