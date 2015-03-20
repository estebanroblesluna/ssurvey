USE `ssurvey`

ALTER TABLE `linkedin_profile` 
ADD COLUMN `confidence` FLOAT,
ADD COLUMN `validity` FLOAT,
ADD COLUMN `profile_picture_url` varchar(255) DEFAULT NULL;
