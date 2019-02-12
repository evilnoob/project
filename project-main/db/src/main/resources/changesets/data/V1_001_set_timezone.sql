--liquibase formatted sql logicalFilePath:V1_001_set_timezone.sql
--changeset akhudoleev:1.1 runOnChange:false context:prod

SET TIME_ZONE = '+00:00';