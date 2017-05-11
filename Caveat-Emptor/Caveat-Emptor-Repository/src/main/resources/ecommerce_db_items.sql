-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ecommerce_db
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `description` longtext NOT NULL,
  `image_path` varchar(45) DEFAULT NULL,
  `initial_price` bigint(20) NOT NULL,
  `opening_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `expiring_date` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `best_bid_value` bigint(20) DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id_idx` (`category_id`),
  KEY `user` (`user_id`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'i7 6700kk','description',NULL,800,'2017-05-10 08:36:37','2017-05-06 11:57:47',800,'open',8,15),(2,'fhgj','fghfhfhhg',NULL,200,'2017-05-11 12:51:25','2017-05-12 10:07:58',800,'abandoned',2,15),(5,'name','gdfgdfg',NULL,56,'2017-05-11 14:46:49','2017-05-18 10:44:05',800,'not yet open',2,15),(6,'new Item','dsfsfs',NULL,123,'2017-05-11 14:46:49','2017-05-20 10:57:40',800,'open',9,15),(7,'drtrdet','retrry',NULL,4567,'2017-05-11 14:46:49','2017-05-25 05:41:26',800,'abandoned',2,15),(8,'dfgdg','dfgdf',NULL,678,'2017-05-11 14:46:49','2017-06-23 01:43:34',800,'abandoned',1,15),(9,'erew','reer',NULL,456,'2017-05-11 14:46:49','2017-05-10 09:58:54',800,'open',2,15),(10,'edit1','dfgd',NULL,346,'2017-05-11 06:33:34','2017-05-16 01:25:19',800,'abandoned',1,15),(11,'dfgdg','dfgdfg',NULL,456,'2017-05-12 05:26:26','2017-05-13 02:26:26',800,'abandoned',13,15),(12,'dfgdg','vghgh',NULL,456,'2017-05-19 05:26:26','2017-05-16 02:26:26',800,'open',1,15),(13,'esfd','dfgdfg',NULL,456,'2017-05-11 12:25:02','2017-05-11 12:25:02',800,'not yet open',1,15);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-11 18:47:51
