DROP DATABASE IF EXISTS `ssurvey`;

CREATE DATABASE `ssurvey`;

USE `ssurvey`
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
DROP TABLE IF EXISTS `UserConnection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserConnection` (
  `userId` varchar(255) NOT NULL,
  `providerId` varchar(255) NOT NULL,
  `providerUserId` varchar(255) NOT NULL DEFAULT '',
  `rank` int(11) NOT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `profileUrl` varchar(512) DEFAULT NULL,
  `imageUrl` varchar(512) DEFAULT NULL,
  `accessToken` varchar(255) NOT NULL,
  `secret` varchar(255) DEFAULT NULL,
  `refreshToken` varchar(255) DEFAULT NULL,
  `expireTime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userId`,`providerId`,`providerUserId`),
  UNIQUE KEY `UserConnectionRank` (`userId`,`providerId`,`rank`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `UserConnection` DISABLE KEYS */;
/*!40000 ALTER TABLE `UserConnection` ENABLE KEYS */;
DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
DROP TABLE IF EXISTS `multiple_choice_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `multiple_choice_question` (
  `question_id` bigint(20) NOT NULL,
  `upper_bound` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `FK_jea258sfslh5f5wlw9cekptdx` (`question_id`),
  CONSTRAINT `FK_jea258sfslh5f5wlw9cekptdx` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `multiple_choice_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `multiple_choice_question` ENABLE KEYS */;
DROP TABLE IF EXISTS `multiple_choice_question_options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `multiple_choice_question_options` (
  `question_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  KEY `FK_2qgrwr3y8h4fgw26bemi6benk` (`question_id`),
  CONSTRAINT `FK_2qgrwr3y8h4fgw26bemi6benk` FOREIGN KEY (`question_id`) REFERENCES `multiple_choice_question` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `multiple_choice_question_options` DISABLE KEYS */;
/*!40000 ALTER TABLE `multiple_choice_question_options` ENABLE KEYS */;
DROP TABLE IF EXISTS `numeric_answer_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `numeric_answer_question` (
  `question_id` bigint(20) NOT NULL,
  `upper_bound` bigint(20) DEFAULT NULL,
  `lower_bound` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `FK_5d5b6vf16kwyhgd60p09i9yo9` (`question_id`),
  CONSTRAINT `FK_5d5b6vf16kwyhgd60p09i9yo9` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `numeric_answer_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `numeric_answer_question` ENABLE KEYS */;
DROP TABLE IF EXISTS `open_answer_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `open_answer_question` (
  `question_id` bigint(20) NOT NULL,
  `upper_bound` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `FK_lq7rbgk9ukk7jr7okw0bkcx75` (`question_id`),
  CONSTRAINT `FK_lq7rbgk9ukk7jr7okw0bkcx75` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `open_answer_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `open_answer_question` ENABLE KEYS */;
DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `question_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `survey_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `FK_o0e3abfvubder61gwvom4rrw5` (`survey_id`),
  CONSTRAINT `FK_o0e3abfvubder61gwvom4rrw5` FOREIGN KEY (`survey_id`) REFERENCES `survey` (`survey_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `question` DISABLE KEYS */;
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
DROP TABLE IF EXISTS `rank_answer_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rank_answer_question` (
  `question_id` bigint(20) NOT NULL,
  PRIMARY KEY (`question_id`),
  KEY `FK_l7s67s6bbypv2oj6amc6v0p9i` (`question_id`),
  CONSTRAINT `FK_l7s67s6bbypv2oj6amc6v0p9i` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `rank_answer_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `rank_answer_question` ENABLE KEYS */;
DROP TABLE IF EXISTS `rank_answer_question_options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rank_answer_question_options` (
  `question_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  KEY `FK_fxy2atkomub4yioitgbef51g9` (`question_id`),
  CONSTRAINT `FK_fxy2atkomub4yioitgbef51g9` FOREIGN KEY (`question_id`) REFERENCES `rank_answer_question` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `rank_answer_question_options` DISABLE KEYS */;
/*!40000 ALTER TABLE `rank_answer_question_options` ENABLE KEYS */;
DROP TABLE IF EXISTS `single_choice_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `single_choice_question` (
  `question_id` bigint(20) NOT NULL,
  PRIMARY KEY (`question_id`),
  KEY `FK_tgb9d1ewqpj1irxsrgkyokgpl` (`question_id`),
  CONSTRAINT `FK_tgb9d1ewqpj1irxsrgkyokgpl` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `single_choice_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `single_choice_question` ENABLE KEYS */;
DROP TABLE IF EXISTS `single_choice_question_options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `single_choice_question_options` (
  `question_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  KEY `FK_gbm205nqmdcv9i8ksms9d6nnl` (`question_id`),
  CONSTRAINT `FK_gbm205nqmdcv9i8ksms9d6nnl` FOREIGN KEY (`question_id`) REFERENCES `single_choice_question` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `single_choice_question_options` DISABLE KEYS */;
/*!40000 ALTER TABLE `single_choice_question_options` ENABLE KEYS */;
DROP TABLE IF EXISTS `survey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `survey` (
  `survey_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`survey_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `survey` DISABLE KEYS */;
/*!40000 ALTER TABLE `survey` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

