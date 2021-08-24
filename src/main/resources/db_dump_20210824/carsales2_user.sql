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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `login` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `pass` varchar(60) COLLATE utf8mb4_general_ci NOT NULL,
  `role_id` int NOT NULL,
  `state_id` int NOT NULL,
  `email` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(15) COLLATE utf8mb4_general_ci NOT NULL,
  `registration_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `phone_UNIQUE` (`phone`),
  KEY `state_id_idx` (`state_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`user_role_id`),
  CONSTRAINT `state_id` FOREIGN KEY (`state_id`) REFERENCES `user_state` (`user_state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'root','$2a$10$Z3uY.j8N2sIVEgRhzUYplOHgcswj4vM3kIFKje.IkRCCnDV7wyWJu',1,2,'admin.in.da.house@gmail.com','+375292772455','2021-08-12 14:27:32'),(2,'moder1','$2a$10$b5fDYPTq.YAOkqJ39Z1OBOuAovoVj35n9DrmGZDJnV1SPC/GC8CGq',2,2,'punisher7000@gmail.com','+380845133546','2021-08-17 03:15:28'),(3,'moder2','$2a$10$0u4spkvsG9XmG2Gj8EsnKO8IJ.IYjeC9lqd8p4tP5OG15jAD1Y86K',2,2,'fairness@gmail.com','+370868646755','2021-08-16 01:06:19'),(4,'moder3','$2a$10$hU.e7hTx9C.JIxxKcFlEUelmaGVGfpv13/gMnEG3L.h6/s03Atjkq',2,2,'stupidity@mail.ru','+74951514455','2021-07-29 03:31:08'),(5,'user1','$2a$10$aBC6S8SUCeLERO8iVrRHlO8sgy7xJnApt5RcZwhdZQooPTwEf6ERm',3,4,'user1@mymail.com','+375331478523','2021-08-24 15:33:12'),(6,'user2','$2a$10$7iLTCDy2VAHCMLgsXmEhXeppPEL1zktpuSPN.z/MC.aPZ9npdGFWO',3,2,'user2@mymail.com','+375448974125','2021-08-16 01:06:22'),(7,'user3','$2a$10$3Gw0vTn.K7F49TQ3m8itNOYAW1e/dWWbH4o315i9PLiEjdWXDTBSe',3,2,'user3@gmail.com','+275172010923','2021-08-14 03:49:40'),(9,'usamobinladen','$2a$10$I9h2J71Hyt/xIngkcLP/8ue/FBRMBKPHjilybhRGmb0V4/ljaBYym',3,2,'usamobinladen@gmail.com','+379875415478','2021-08-20 23:11:41'),(10,'abdulahman','$2a$10$vM4crnCFyJjHFwqgqbDaTuj7fY1bRBXGeCb5c4RfVrQ.KliPF6aKa',3,2,'abdulali@tut.by','+375296666999','2021-08-07 13:32:18'),(11,'curiosity','$2a$10$lCxBOeLbFIKK8E7EEBEWp.EdoC7jMExIuYz0QIJCO3IWliWM6fnD6',3,2,'curiosity@tut.by','+375296666111','2021-08-13 00:42:32'),(12,'pokemon','$2a$10$LBEzX0BdSN3lW.mCrrobzuTBIkgqcg6A2Kd1spJKsYFcgbcxUMmTW',3,2,'pokemon@mail.ru','+375296666112','2021-08-16 01:06:24'),(13,'labasritas','$2a$10$fC/ENbTm9avfdqc8VAozueccwfqAqa5XUGIsQu5QMj61fxKbOE3sa',3,2,'labasritas@gmail.com','+370865686545','2021-08-17 18:05:38'),(15,'ermoshina','$2a$10$rOGWsMaUHGO52r9qAnOOd.wUWDrN60TcfdltHtk2M50.vOupFrqOm',3,2,'ermoshina@belstat.by','+370171031031','2021-08-16 01:06:30'),(16,'labuten','$2a$10$j4MCnpAFp5xM5L3kzunnJOh0us1mIr7zN9WsxjLoRsQbfk11ot7PG',3,2,'labuten@gmail.com','+375178745689','2021-08-16 01:06:32'),(17,'mutant','$2a$10$OoVuleHnUuuGCOTL71SCJ.qBV.Rb8Yg/bIhEMDb8GmhRiW3C/vxWO',3,2,'mutant@mail.ru','+378487987541','2021-07-29 03:31:08'),(18,'barabashka','$2a$10$edXTZzxiG4hkd.xQUHgudeoDInzaJ2xlWKzQtjvuuMUkzmmm76xti',3,2,'barabashka@tut.by','+375178741983','2021-08-13 00:52:07'),(19,'colombo','$2a$10$kP4QL6iD2jek2y/i2Q3I3OEasd79p3pkp3JHLj.EMHsdAqUVwNhyq',3,2,'colombo@gmail.com','+375172587413','2021-08-16 01:06:34'),(23,'user4','$2a$10$C5mA.yHkvqKneiimeIjeTeoWIHSofZWRgzVVVGSxNs.jJ9BNf2my6',3,2,'user4@gmail.com','+375296666123','2021-08-16 01:06:36'),(28,'trump','$2a$10$xrmTil0cEfBz5UEZfPhEB.27lAl1.iIVu.75O9tc0ijdPiOdaLmzK',3,2,'trump@tut.by','+371457896523','2021-08-23 17:25:25');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-24 16:23:59
