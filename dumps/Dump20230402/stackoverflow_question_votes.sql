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
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_votes`
--

LOCK TABLES `question_votes` WRITE;
/*!40000 ALTER TABLE `question_votes` DISABLE KEYS */;
INSERT INTO `question_votes` VALUES (1,'up',4,1),(2,'down',4,3),(3,'up',9,4),(4,'down',9,3),(5,'up',9,2),(6,'up',9,1),(7,'up',9,5),(8,'up',11,9),(9,'up',11,8),(10,'up',11,7),(11,'up',11,5),(12,'up',11,4),(13,'up',11,3),(14,'down',11,2),(15,'down',11,1),(16,'up',12,10),(17,'up',12,11),(18,'up',12,8),(19,'down',12,9),(20,'down',12,7),(21,'up',12,5),(22,'up',12,4),(23,'up',12,2),(24,'up',12,1),(25,'up',12,3),(26,'up',15,14),(27,'up',15,13),(28,'up',15,11),(29,'up',15,5),(30,'up',15,3),(31,'down',15,1),(32,'down',15,8),(33,'down',15,9),(34,'up',15,12),(35,'up',15,10),(36,'up',15,2),(37,'up',14,16),(38,'down',14,15),(39,'up',14,14),(40,'down',14,13),(41,'up',14,12),(42,'up',14,11),(43,'up',14,9),(44,'down',14,10),(45,'up',14,8),(46,'up',14,7),(47,'up',14,5),(48,'up',14,2),(49,'up',14,1),(50,'up',14,4),(51,'down',14,3),(52,'up',7,15),(53,'up',7,16),(54,'up',7,13),(55,'down',7,9),(56,'down',7,3);
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

-- Dump completed on 2023-04-02 22:52:38
