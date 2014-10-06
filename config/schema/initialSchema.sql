-- MySQL dump 10.13  Distrib 5.5.38, for Linux (x86_64)
--
-- Host: localhost    Database: ssurvey
-- ------------------------------------------------------
-- Server version	5.5.38-log

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

--
-- Current Database: `ssurvey`
--

/*!40000 DROP DATABASE IF EXISTS `ssurvey`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ssurvey` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ssurvey`;

--
-- Table structure for table `UserConnection`
--

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

--
-- Table structure for table `account`
--

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

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `answer_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `question` bigint(20) DEFAULT NULL,
  `id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`answer_id`),
  KEY `FK_lc3dqr6kqx7ntw3kbf822ipwh` (`question`),
  KEY `FK_sjp76skxv4ua47ltsa23yb5yk` (`id`),
  CONSTRAINT `FK_sjp76skxv4ua47ltsa23yb5yk` FOREIGN KEY (`id`) REFERENCES `answered_survey` (`id`),
  CONSTRAINT `FK_lc3dqr6kqx7ntw3kbf822ipwh` FOREIGN KEY (`question`) REFERENCES `question` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `answered_survey`
--

DROP TABLE IF EXISTS `answered_survey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answered_survey` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `survey` bigint(20) DEFAULT NULL,
  `linkedInUserProfile` varchar(255) DEFAULT NULL,
  `accountId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_97bohjbmhqbb0u80wi7jr3c3m` (`survey`),
  KEY `FK_7id6gx2u77scvfidgfaofir8` (`linkedInUserProfile`),
  CONSTRAINT `FK_7id6gx2u77scvfidgfaofir8` FOREIGN KEY (`linkedInUserProfile`) REFERENCES `linkedin_profile` (`linkedin_profile_id`),
  CONSTRAINT `FK_97bohjbmhqbb0u80wi7jr3c3m` FOREIGN KEY (`survey`) REFERENCES `survey` (`survey_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `connection_ticket`
--

DROP TABLE IF EXISTS `connection_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `connection_ticket` (
  `ticket_id` bigint(20) NOT NULL,
  `connection` varchar(255) DEFAULT NULL,
  `connectionOf` varchar(255) DEFAULT NULL,
  `depth` int(11) DEFAULT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `FK_nle4vd8up61qse8s3g5qrg6ki` (`ticket_id`),
  CONSTRAINT `FK_nle4vd8up61qse8s3g5qrg6ki` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `linkedin_company`
--

DROP TABLE IF EXISTS `linkedin_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `linkedin_company` (
  `company_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `linkedin_connections`
--

DROP TABLE IF EXISTS `linkedin_connections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `linkedin_connections` (
  `linkedin_profile_id` varchar(255) NOT NULL,
  `connection_id` varchar(255) NOT NULL,
  PRIMARY KEY (`linkedin_profile_id`,`connection_id`),
  KEY `FK_bvrrfho974q44jgwyypui8x7b` (`connection_id`),
  KEY `FK_3n10l21o8qf5khimclmxu1r0y` (`linkedin_profile_id`),
  CONSTRAINT `FK_3n10l21o8qf5khimclmxu1r0y` FOREIGN KEY (`linkedin_profile_id`) REFERENCES `linkedin_profile` (`linkedin_profile_id`),
  CONSTRAINT `FK_bvrrfho974q44jgwyypui8x7b` FOREIGN KEY (`connection_id`) REFERENCES `linkedin_profile` (`linkedin_profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `linkedin_position`
--

DROP TABLE IF EXISTS `linkedin_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `linkedin_position` (
  `position_id` varchar(255) NOT NULL,
  `company` int(11) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `linkedin_profile_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`position_id`),
  KEY `FK_g2f3ddpxgl6hnpxxchlf7a3cs` (`company`),
  KEY `FK_5a5t66y4csqjpr0157hghwc1q` (`linkedin_profile_id`),
  CONSTRAINT `FK_5a5t66y4csqjpr0157hghwc1q` FOREIGN KEY (`linkedin_profile_id`) REFERENCES `linkedin_profile` (`linkedin_profile_id`),
  CONSTRAINT `FK_g2f3ddpxgl6hnpxxchlf7a3cs` FOREIGN KEY (`company`) REFERENCES `linkedin_company` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `linkedin_profile`
--

DROP TABLE IF EXISTS `linkedin_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `linkedin_profile` (
  `linkedin_profile_id` varchar(255) NOT NULL,
  PRIMARY KEY (`linkedin_profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `multiple_choice_answer`
--

DROP TABLE IF EXISTS `multiple_choice_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `multiple_choice_answer` (
  `answer_id` bigint(20) NOT NULL,
  PRIMARY KEY (`answer_id`),
  KEY `FK_prvk9r1va3xxp2j0rqwp5d1m0` (`answer_id`),
  CONSTRAINT `FK_prvk9r1va3xxp2j0rqwp5d1m0` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`answer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `multiple_choice_answer_answers`
--

DROP TABLE IF EXISTS `multiple_choice_answer_answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `multiple_choice_answer_answers` (
  `answer_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  KEY `FK_f10vb5sbdiv3t38syosnle5h8` (`answer_id`),
  CONSTRAINT `FK_f10vb5sbdiv3t38syosnle5h8` FOREIGN KEY (`answer_id`) REFERENCES `multiple_choice_answer` (`answer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `multiple_choice_question`
--

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

--
-- Table structure for table `multiple_choice_question_options`
--

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

--
-- Table structure for table `numeric_answer_answer`
--

DROP TABLE IF EXISTS `numeric_answer_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `numeric_answer_answer` (
  `answer_id` bigint(20) NOT NULL,
  `answer` int(11) DEFAULT NULL,
  PRIMARY KEY (`answer_id`),
  KEY `FK_dpj5yxlv6eio8bor15woa8tdn` (`answer_id`),
  CONSTRAINT `FK_dpj5yxlv6eio8bor15woa8tdn` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`answer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `numeric_answer_question`
--

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

--
-- Table structure for table `open_answer_answer`
--

DROP TABLE IF EXISTS `open_answer_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `open_answer_answer` (
  `answer_id` bigint(20) NOT NULL,
  `answer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`answer_id`),
  KEY `FK_ge0k4vyudxpkdtigndqqdbbqh` (`answer_id`),
  CONSTRAINT `FK_ge0k4vyudxpkdtigndqqdbbqh` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`answer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `open_answer_question`
--

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

--
-- Table structure for table `question`
--

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rank_answer_answer`
--

DROP TABLE IF EXISTS `rank_answer_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rank_answer_answer` (
  `answer_id` bigint(20) NOT NULL,
  PRIMARY KEY (`answer_id`),
  KEY `FK_55fb3d0mhxwst54meoru4gkba` (`answer_id`),
  CONSTRAINT `FK_55fb3d0mhxwst54meoru4gkba` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`answer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rank_answer_answer_answers`
--

DROP TABLE IF EXISTS `rank_answer_answer_answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rank_answer_answer_answers` (
  `answer_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `ord` int(11) NOT NULL,
  PRIMARY KEY (`answer_id`,`ord`),
  KEY `FK_qenronfe7o4798cxbpmjreiuo` (`answer_id`),
  CONSTRAINT `FK_qenronfe7o4798cxbpmjreiuo` FOREIGN KEY (`answer_id`) REFERENCES `rank_answer_answer` (`answer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rank_answer_question`
--

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

--
-- Table structure for table `rank_answer_question_options`
--

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

--
-- Table structure for table `recommendation_ticket`
--

DROP TABLE IF EXISTS `recommendation_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recommendation_ticket` (
  `ticket_id` bigint(20) NOT NULL,
  `recommender` varchar(255) DEFAULT NULL,
  `recommendee` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `FK_3snv5tbl4d1cfr9ll8fan23pj` (`ticket_id`),
  CONSTRAINT `FK_3snv5tbl4d1cfr9ll8fan23pj` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `respondent_information_ticket`
--

DROP TABLE IF EXISTS `respondent_information_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `respondent_information_ticket` (
  `ticket_id` bigint(20) NOT NULL,
  `answered_survey` bigint(20) NOT NULL,
  PRIMARY KEY (`ticket_id`),
  UNIQUE KEY `UK_hadwdplx54gsjh5w15392hhlp` (`answered_survey`),
  KEY `FK_d59d2pwohe4s7v4vtq5tfuvhv` (`ticket_id`),
  KEY `FK_hadwdplx54gsjh5w15392hhlp` (`answered_survey`),
  CONSTRAINT `FK_hadwdplx54gsjh5w15392hhlp` FOREIGN KEY (`answered_survey`) REFERENCES `answered_survey` (`id`),
  CONSTRAINT `FK_d59d2pwohe4s7v4vtq5tfuvhv` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `single_choice_answer`
--

DROP TABLE IF EXISTS `single_choice_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `single_choice_answer` (
  `answer_id` bigint(20) NOT NULL,
  `answer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`answer_id`),
  KEY `FK_29fa4qa2kaqixr95bphnvhjor` (`answer_id`),
  CONSTRAINT `FK_29fa4qa2kaqixr95bphnvhjor` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`answer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `single_choice_question`
--

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

--
-- Table structure for table `single_choice_question_options`
--

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

--
-- Table structure for table `survey`
--

DROP TABLE IF EXISTS `survey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `survey` (
  `survey_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `permalink` bigint(20) NOT NULL,
  PRIMARY KEY (`survey_id`),
  UNIQUE KEY `UK_mkmf2dsl1wxemqk5lks3al752` (`permalink`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `ticket_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `owner` bigint(20) DEFAULT NULL,
  `processed` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-10-06 20:28:30
