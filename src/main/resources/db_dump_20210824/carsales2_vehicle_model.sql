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
-- Table structure for table `vehicle_model`
--

DROP TABLE IF EXISTS `vehicle_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_model` (
  `vehicle_model_id` int NOT NULL AUTO_INCREMENT,
  `model` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `make_id` int NOT NULL,
  PRIMARY KEY (`vehicle_model_id`),
  UNIQUE KEY `id_UNIQUE` (`vehicle_model_id`),
  UNIQUE KEY `model_UNIQUE` (`model`),
  KEY `make_idx` (`make_id`),
  CONSTRAINT `vehicle_make` FOREIGN KEY (`make_id`) REFERENCES `vehicle_make` (`vehicle_make_id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_model`
--

LOCK TABLES `vehicle_model` WRITE;
/*!40000 ALTER TABLE `vehicle_model` DISABLE KEYS */;
INSERT INTO `vehicle_model` VALUES (1,'A1',2),(2,'1-series',3),(3,'2-series',3),(4,'3-series',3),(5,'Model S',22),(6,'Cooper One',13),(7,'C4',4),(8,'GALAXY',5),(9,'CIVIC',7),(10,'IX35',8),(11,'RIO',9),(12,'LS',10),(13,'4-series',3),(14,'5-series',3),(15,'6-series',3),(16,'7-series',3),(17,'A2',2),(18,'A3',2),(19,'A4',2),(20,'A5',2),(21,'A6',2),(22,'A7',2),(23,'A8',2),(24,'Q3',2),(25,'Q5',2),(26,'Q7',2),(27,'Q8',2),(28,'X1',3),(29,'X2',3),(30,'X3',3),(31,'X4',3),(32,'X5',3),(33,'X6',3),(34,'X7',3),(35,'i3',3),(36,'i8',3),(37,'X6M',3),(38,'RSQ3',2),(39,'RS3',2),(40,'X5M',3),(41,'M6',3),(42,'M5',3),(43,'LOGAN',30),(44,'RS6',2),(52,'MDX',1),(53,'TL',1),(54,'ESCORT',5),(55,'965',56),(56,'19',17),(57,'SAFRANE',17),(58,'LEGEND',7),(59,'21',17),(60,'25',17),(61,'R8',2),(62,'ACCENT',8),(63,'H1',8),(64,'Z',26),(65,'CHIRON',57),(66,'CENTODIECI',57),(67,'COPEN',54),(68,'IS',10),(69,'EX7',6);
/*!40000 ALTER TABLE `vehicle_model` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-24 16:23:47
