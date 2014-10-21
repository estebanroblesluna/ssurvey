USE `ssurvey`;

ALTER TABLE `account`
ADD COLUMN `middle_name` varchar(255) DEFAULT NULL,
ADD COLUMN `last_confidence_update` TIMESTAMP;