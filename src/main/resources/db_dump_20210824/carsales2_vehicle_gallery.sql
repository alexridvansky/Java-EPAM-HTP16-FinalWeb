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
-- Table structure for table `vehicle_gallery`
--

DROP TABLE IF EXISTS `vehicle_gallery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_gallery` (
  `vehicle_gallery_id` bigint NOT NULL AUTO_INCREMENT,
  `vehicle_id` bigint NOT NULL,
  `img_path` varchar(90) COLLATE utf8mb4_general_ci NOT NULL,
  `is_preview` char(1) COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'n',
  PRIMARY KEY (`vehicle_gallery_id`),
  UNIQUE KEY `gallery_id_UNIQUE` (`vehicle_gallery_id`),
  KEY `vehicle_id_idx` (`vehicle_id`),
  CONSTRAINT `vehicle_gallery_id_key` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`vehicle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_gallery`
--

LOCK TABLES `vehicle_gallery` WRITE;
/*!40000 ALTER TABLE `vehicle_gallery` DISABLE KEYS */;
INSERT INTO `vehicle_gallery` VALUES (7,2,'fcgisesxnfasmrqlviqc','n'),(8,2,'ep0jllc83nyhjyfw1p6q','n'),(9,2,'c6qtqlcyfitlyhuhtfob','n'),(10,1,'lyyt6zmsvmexhlnwyoih','n'),(11,3,'sifzoqkg52kdztlag5io','n'),(15,4,'lhcrsomz3rwtupguvppn','n'),(16,5,'ayhj3yygdxtmthopvy2d','n'),(17,6,'bogwqknyswcmho9ytd3w','n'),(18,6,'oluojfnfqdcwegn5xmon','n'),(21,7,'uufgzgchky0n8tvtkl7r','n'),(22,7,'thegqryuuctk5ueyithg','n'),(23,8,'lcl3vdori6pcsz7euiu9','n'),(24,8,'qxfpkhpci3cikfpeoruj','n'),(26,9,'qi8ul6bu0l6qosywiqsy','n'),(27,9,'tx6y5w2asy6ywc8hlfzd','n'),(28,11,'njdn05ukhxcvk5nxammc','n'),(29,11,'fefwmbua4d8vpkbbmaxl','n'),(31,23,'fweoxhoktfumzyr4uzzd','n'),(32,23,'kis3bk8tyhpfj8t6hshs','n'),(33,13,'miqgks1ff5ustcjut5ux','n'),(36,20,'gmk8cqpry9rjo29by2uj','n'),(39,24,'ygmwaakwz3uat0kjzpjd','n'),(42,27,'lhaaidjbe7slgntjc1co','n'),(45,29,'etr0mwwkkqsdfbyu5vyi','n'),(46,28,'gninb1bfnknowfwvcvgb','n'),(47,28,'ldm2tz8xd8cjw3qfmplb','n'),(48,30,'osvegvhtchnwtzhgnzxc','n'),(49,31,'njj9itosd3darimr0zoo','n'),(50,32,'ctptvlxloxb9aihhtruz','n'),(51,33,'igio1ai6ein3mszmemgl','n'),(52,34,'zr5mwkanabagdpuigpkx','n'),(53,35,'eetwlnrfs0wue0ep0mtx','n'),(55,37,'pbef30z2c1gevwembhb0','n'),(58,40,'dqqxpih0hmxo5uzcolpo','n'),(59,40,'o02pzwquaj27j7v3dtez','n'),(64,43,'azaua3r5xkgttleux0ph','n'),(68,12,'cbwfwmu7wqr0iuqts4m6','n'),(70,12,'rfj55rwepr2ujxh2vjsd','n'),(72,30,'uyh5o9qehl7lgaxrltvt','n'),(73,13,'s7hhujscmyn1xou1idyw','n'),(74,25,'jikuwrpkqoubfcsskgqx','n');
/*!40000 ALTER TABLE `vehicle_gallery` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-24 16:23:52
