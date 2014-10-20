USE `ssurvey`;


DROP TABLE IF EXISTS `update_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `update_ticket` (
  	`ticket_id` bigint(20) NOT NULL,
  	 PRIMARY KEY (`ticket_id`),
	 CONSTRAINT `FK_ticket_base` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `update_profile_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `update_profile_ticket` (
  	`ticket_id` bigint(20) NOT NULL,
  	 PRIMARY KEY (`ticket_id`),
	 CONSTRAINT `FK_ticket_base2` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;