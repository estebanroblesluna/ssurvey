DROP DATABASE IF EXISTS `ssurvey`;

CREATE DATABASE `ssurvey`;

USE `ssurvey`

CREATE TABLE `survey` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `question` (
  `question_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `multiple_choice_question` (
  `question_id` bigint(20) NOT NULL,
  `upper_bound` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `FK_jea258sfslh5f5wlw9cekptdx` (`question_id`),
  CONSTRAINT `FK_jea258sfslh5f5wlw9cekptdx` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `multiple_choice_question_options` (
  `question_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  KEY `FK_2qgrwr3y8h4fgw26bemi6benk` (`question_id`),
  CONSTRAINT `FK_2qgrwr3y8h4fgw26bemi6benk` FOREIGN KEY (`question_id`) REFERENCES `multiple_choice_question` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `numeric_answer_question` (
  `question_id` bigint(20) NOT NULL,
  `upper_bound` bigint(20) DEFAULT NULL,
  `lower_bound` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `FK_5d5b6vf16kwyhgd60p09i9yo9` (`question_id`),
  CONSTRAINT `FK_5d5b6vf16kwyhgd60p09i9yo9` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `open_answer_question` (
  `question_id` bigint(20) NOT NULL,
  `upper_bound` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `FK_lq7rbgk9ukk7jr7okw0bkcx75` (`question_id`),
  CONSTRAINT `FK_lq7rbgk9ukk7jr7okw0bkcx75` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `rank_answer_question` (
  `question_id` bigint(20) NOT NULL,
  `upper_bound` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `FK_l7s67s6bbypv2oj6amc6v0p9i` (`question_id`),
  CONSTRAINT `FK_l7s67s6bbypv2oj6amc6v0p9i` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `single_choice_question` (
  `question_id` bigint(20) NOT NULL,
  PRIMARY KEY (`question_id`),
  KEY `FK_tgb9d1ewqpj1irxsrgkyokgpl` (`question_id`),
  CONSTRAINT `FK_tgb9d1ewqpj1irxsrgkyokgpl` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `single_choice_question_options` (
  `question_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  KEY `FK_gbm205nqmdcv9i8ksms9d6nnl` (`question_id`),
  CONSTRAINT `FK_gbm205nqmdcv9i8ksms9d6nnl` FOREIGN KEY (`question_id`) REFERENCES `single_choice_question` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

