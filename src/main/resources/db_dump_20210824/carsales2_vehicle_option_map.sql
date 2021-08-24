CREATE DATABASE  IF NOT EXISTS `carsales2` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `carsales2`;
-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: carsales2
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `vehicle_option_map`
--

DROP TABLE IF EXISTS `vehicle_option_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_option_map` (
  `option_map_id` bigint NOT NULL AUTO_INCREMENT,
  `vehicle_id` bigint NOT NULL,
  `option_id` bigint NOT NULL,
  PRIMARY KEY (`option_map_id`),
  UNIQUE KEY `option_map_id_UNIQUE` (`option_map_id`),
  KEY `vehicle_id_idx` (`vehicle_id`),
  KEY `option_id_idx` (`option_id`),
  CONSTRAINT `option_id_key` FOREIGN KEY (`option_id`) REFERENCES `vehicle_option` (`vehicle_option_id`),
  CONSTRAINT `vehicle_option_id_key` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`vehicle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=319 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_option_map`
--

LOCK TABLES `vehicle_option_map` WRITE;
/*!40000 ALTER TABLE `vehicle_option_map` DISABLE KEYS */;
INSERT INTO `vehicle_option_map` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(18,2,1),(19,2,2),(20,2,3),(21,2,4),(22,2,5),(23,2,6),(24,2,7),(25,2,8),(26,2,9),(27,2,10),(86,20,1),(87,20,2),(88,20,3),(89,20,4),(107,23,1),(108,23,2),(109,23,3),(110,23,4),(111,23,5),(112,23,6),(113,23,7),(114,23,8),(115,23,9),(116,23,10),(117,23,11),(118,23,12),(119,23,13),(120,23,14),(121,23,15),(122,23,16),(123,23,24),(124,24,1),(125,24,2),(126,24,3),(127,24,4),(128,24,5),(129,24,8),(130,24,9),(131,24,12),(132,24,13),(133,24,14),(134,25,1),(135,25,3),(136,25,4),(145,27,1),(146,27,2),(147,27,3),(148,27,4),(149,27,9),(150,27,12),(151,27,13),(152,27,14),(153,28,8),(154,28,17),(155,29,1),(156,29,2),(157,29,3),(158,29,4),(159,29,5),(160,29,6),(161,29,7),(162,29,10),(163,29,11),(164,29,12),(165,29,13),(166,29,14),(167,29,16),(168,29,24),(169,30,1),(170,30,3),(171,31,1),(172,31,2),(173,31,3),(174,31,4),(175,31,12),(176,31,13),(177,32,1),(178,32,2),(179,32,3),(180,32,4),(181,33,1),(182,33,3),(183,33,4),(184,34,1),(185,34,2),(186,34,3),(187,34,4),(188,34,5),(189,34,6),(190,34,7),(191,34,10),(192,34,11),(193,34,12),(194,34,13),(195,34,14),(196,34,24),(197,35,10),(198,35,13),(201,37,1),(202,37,2),(203,37,3),(204,37,5),(205,37,6),(206,37,7),(207,37,8),(208,37,9),(209,37,10),(210,37,11),(211,37,12),(212,37,13),(213,37,14),(214,37,15),(215,37,16),(216,37,17),(217,37,24),(252,40,1),(253,40,2),(254,40,3),(255,40,4),(256,40,5),(257,40,6),(258,40,7),(259,40,8),(260,40,9),(261,40,10),(262,40,11),(263,40,12),(264,40,13),(265,40,14),(266,40,15),(267,40,16),(268,40,17),(269,40,18),(270,40,24),(271,40,25),(272,40,26),(315,43,1),(316,43,2),(317,43,4),(318,43,11);
/*!40000 ALTER TABLE `vehicle_option_map` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-24 16:23:56
