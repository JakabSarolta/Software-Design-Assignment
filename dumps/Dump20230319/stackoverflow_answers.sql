-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: stackoverflow
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `answers`
--

DROP TABLE IF EXISTS `answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `question_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5bp3d5loftq2vjn683ephn75a` (`user_id`),
  KEY `FK3erw1a3t0r78st8ty27x6v3g1` (`question_id`),
  CONSTRAINT `FK3erw1a3t0r78st8ty27x6v3g1` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`),
  CONSTRAINT `FK5bp3d5loftq2vjn683ephn75a` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` VALUES (1,'Firewalls are studied in computer networks.','2023-03-19 18:22:24.164000','heyyyhoooo',8,1),(2,'Machine learning is connected to deep learning. It is neural networks and generating models to predict stuff.','2023-03-19 18:24:33.659000','heyyyhoooo',7,5),(3,'I think it is one of the fastest growing fields at the moment.','2023-03-19 18:24:52.077000','heyyyhoooo',7,5),(4,'I have heard about Python and Java but I don\'t know where to start from.','2023-03-19 18:25:23.392000','heyyyhoooo',7,8),(5,'Any advice would be appreciated.','2023-03-19 18:25:34.755000','heyyyhoooo',7,8),(6,'One memory is volatile while the other one is not.','2023-03-19 18:26:02.077000','heyyyhoooo',7,2),(7,'I think this subject is way more complicated than that. You should read some of the related literature about the topic.','2023-03-19 18:30:00.435000','heyyyhoooo',14,5),(8,'It is the acronim for a Virtual Private Network that assures more security.','2023-03-19 18:31:04.500000','heyyyhoooo',14,7),(9,'Read only memory - random access memory.','2023-03-19 18:31:25.130000','heyyyhoooo',14,2);
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-19 18:39:32
