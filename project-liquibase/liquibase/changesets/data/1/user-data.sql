--liquibase formatted sql logicalFilePath:s1_user_data.sql
--changeset akhudoleev:user-data-01 runOnChange:false context:prod

INSERT INTO auth.user
VALUES (nextval(pg_get_serial_sequence('auth.user', 'id')), 'admin', '$2y$12$mTAyzkYTgxn/K4CaK6HRcu01h5rPivStZ5tvf9rr.s4e9xpLRhm1e', TRUE,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP); --password
