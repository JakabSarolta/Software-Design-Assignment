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
-- Table structure for table `question_votes`
--

DROP TABLE IF EXISTS `question_votes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question_votes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `vote_type` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `question_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9v02son0rrjww1wr11x5gl68u` (`user_id`),
  KEY `FK1xd1qk3vvt048vghi893xcpo9` (`question_id`),
  CONSTRAINT `FK1xd1qk3vvt048vghi893xcpo9` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`),
  CONSTRAINT `FK9v02son0rrjww1wr11x5gl68u` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_votes`
--

LOCK TABLES `question_votes` WRITE;
/*!40000 ALTER TABLE `question_votes` DISABLE KEYS */;
INSERT INTO `question_votes` VALUES (1,'up',8,2),(2,'down',8,1),(3,'up',7,5),(4,'up',7,2),(5,'up',7,1),(6,'up',1,7),(7,'down',1,2),(8,'up',1,1),(9,'up',14,4),(10,'down',14,3),(11,'down',14,1),(12,'up',14,10),(13,'down',14,9),(14,'up',14,6),(15,'up',13,11),(16,'down',13,13),(17,'up',13,12),(18,'up',13,10),(19,'up',13,1),(20,'down',13,2),(21,'up',13,6),(22,'up',13,9),(23,'up',13,7),(24,'up',13,8),(25,'up',13,5),(26,'up',13,4),(27,'up',13,3);
/*!40000 ALTER TABLE `question_votes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-19 18:39:33
