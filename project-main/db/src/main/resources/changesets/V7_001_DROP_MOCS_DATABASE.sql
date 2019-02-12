--liquibase formatted sql logicalFilePath:V7_001_DROP_MOCS_DATABASE.sql
--changeset Sanasov:1.1 runOnChange:false context:prod

DROP DATABASE IF EXISTS `mocs`;
SET TIME_ZONE = '+00:00';