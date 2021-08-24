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
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle` (
  `vehicle_id` bigint NOT NULL AUTO_INCREMENT,
  `state_id` int NOT NULL,
  `owner_id` bigint NOT NULL,
  `model_id` int NOT NULL,
  `modelyear` year NOT NULL,
  `mileage` int NOT NULL,
  `color_id` int NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `powertrain_id` int NOT NULL,
  `transmission_id` int NOT NULL,
  `drive_id` int NOT NULL,
  `displacement` int NOT NULL,
  `power` int NOT NULL,
  `comment` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `creation_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`vehicle_id`),
  UNIQUE KEY `id_UNIQUE` (`vehicle_id`),
  KEY `owner_id_idx` (`owner_id`),
  KEY `model_id_idx` (`model_id`),
  KEY `transmission_id_idx` (`transmission_id`),
  KEY `powertrain_id_idx` (`powertrain_id`),
  KEY `ad_state_idx` (`state_id`),
  KEY `fk_vehicle_vehicle_drive1_idx` (`drive_id`),
  KEY `vehicle_color_key_idx` (`color_id`),
  CONSTRAINT `ad_state` FOREIGN KEY (`state_id`) REFERENCES `vehicle_state` (`vehicle_state_id`),
  CONSTRAINT `fk_vehicle_vehicle_drive1` FOREIGN KEY (`drive_id`) REFERENCES `vehicle_drive` (`vehicle_drive_id`),
  CONSTRAINT `model_id` FOREIGN KEY (`model_id`) REFERENCES `vehicle_model` (`vehicle_model_id`),
  CONSTRAINT `powertrain_id` FOREIGN KEY (`powertrain_id`) REFERENCES `vehicle_powertrain` (`vehicle_powertrain_id`),
  CONSTRAINT `transmission_id` FOREIGN KEY (`transmission_id`) REFERENCES `vehicle_transmission` (`vehicle_transmission_id`),
  CONSTRAINT `vehicle_color_key` FOREIGN KEY (`color_id`) REFERENCES `vehicle_color` (`vehicle_color_id`),
  CONSTRAINT `vehicle_owner_id` FOREIGN KEY (`owner_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (1,2,5,1,2017,54000,6,12500,2,2,1,1400,215,'Харощий машина, жена ездила, почти не ездила, в гараже стояла, только на мойку возили на эвакуаторе','2021-08-23 23:31:10'),(2,2,7,5,2019,17500,5,32000,3,2,2,0,450,'Себе привез из США, хотел сам на ней ездить, но потом понял что негде заряжать, вот решил продать и купить дизельного гольфа','2021-08-23 16:16:07'),(3,2,6,6,2015,125000,2,6100,1,1,1,1600,145,'Ну типа чё, ничё! Нормальная машина, езжу 3 года, менял только масло и то один раз, вот сейчас нужно менять второй раз, поэтому продаю','2021-08-24 11:45:50'),(4,2,6,3,2017,74350,6,14500,1,1,1,2000,105,'NBT Evo, полная-приполная','2021-08-21 01:04:01'),(5,2,9,7,2008,215500,3,4300,2,1,1,1400,115,'расход 3.5л\\100км','2021-08-21 01:04:02'),(6,2,10,8,2001,95050,6,3500,2,1,1,1900,105,'ЭЭэ, вааа! Нормалный мащина да! Регенальный прабег, вчера из германии своим ходом приехал, ничего кроме семьи не возила, ни разу не была в ДТП, 20 мешком штукатурки влазит','2021-08-21 01:09:10'),(7,2,11,9,1997,315800,7,2200,1,1,1,1400,75,'Вж-вж-вж, JDB типа, тачка на стиле, кепка плоскокозырка в придачу с машиной','2021-08-23 16:16:11'),(8,2,12,10,2011,187450,8,10300,1,2,3,2000,145,'Ваааа-па-па-па-па, валасы назад!!!','2021-08-18 18:02:46'),(9,2,13,11,2019,29500,1,11500,1,2,1,1600,138,'Вааще валит-стелит, валасы назад, всех БМВ на перекрестках обгоняю, даже пятилитровые','2021-08-21 01:04:03'),(11,2,15,12,2010,198120,2,15000,1,2,2,5000,389,'Японский премиум, надежность и комфорт, кто ездил тот знает','2021-08-16 21:57:34'),(12,2,5,19,2007,375000,1,6500,2,1,1,2000,140,'крутая тачка','2021-08-23 16:16:23'),(13,2,5,2,2008,175000,1,6500,1,2,2,2000,175,'Очень аккуратная, ездила девушка, троит','2021-08-17 10:57:35'),(20,2,7,4,2000,250000,1,5500,1,1,2,3000,245,'Валит на все бабки','2021-08-20 23:13:52'),(23,2,5,16,2010,150000,1,14100,1,2,2,4400,405,'approx consumption - 7L\\100km (of Oil)','2021-08-17 10:57:41'),(24,2,5,52,2005,225000,2,6500,1,2,3,3500,300,'pretty old, but really reliable car','2021-08-17 10:57:45'),(25,2,5,54,1997,185000,5,600,1,1,1,1600,90,'rusty as f**k','2021-08-23 23:39:52'),(27,2,6,58,1997,165000,1,6500,1,2,1,3500,245,'Japanese luxury','2021-08-17 18:05:30'),(28,2,5,61,2020,5500,6,165000,1,2,3,5200,620,'Buy me, please','2021-08-23 23:41:56'),(29,2,6,38,2014,7500,1,24500,1,2,3,2500,315,'really fast car with phenomenal low fuel consumption','2021-08-17 18:05:29'),(30,2,5,43,2010,450000,6,4500,1,1,1,1600,105,'just working stuff, \"nothing extra\"','2021-08-18 14:36:48'),(31,2,7,62,2014,170000,6,5800,1,1,1,1600,125,'Almost new :) Simple and reliable car, will never get broken :)','2021-08-18 14:42:03'),(32,2,7,63,2010,280000,4,14500,2,2,2,2500,186,'9 seats, not from Taxi ','2021-08-18 14:46:58'),(33,2,7,64,2000,475000,4,4200,2,1,1,2000,110,'Vehicle in a unexpectedly good state, can drive itself','2021-08-18 14:51:27'),(34,2,23,25,2014,57000,16,22000,1,2,3,2000,225,'I\'m the first and only owner, can prove mileage and maintaining with documents.','2021-08-18 15:39:31'),(35,2,7,20,2007,150000,1,11500,1,2,1,2000,386,'cool veh','2021-08-19 18:45:53'),(37,2,7,65,2020,5,5,3000000,1,2,3,8000,1500,'New car','2021-08-19 19:46:24'),(40,2,7,66,2021,0,2,8900000,1,2,3,8000,1600,'brand new','2021-08-19 19:56:11'),(43,2,7,67,2020,45000,4,15000,1,1,1,660,63,'small but cool','2021-08-19 20:21:59');
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-24 16:23:57
