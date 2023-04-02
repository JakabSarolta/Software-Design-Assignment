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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` VALUES (1,'Hmm..great question','2023-04-02 22:07:24.065000','/pictures/upload_5021d517d84446289574f6133f4163c1',4,1),(2,'And my additional question is how is it connected to ai?','2023-04-02 22:08:10.717000','null',4,5),(3,'random access memory - read only memory','2023-04-02 22:25:52.886000','/pictures/upload_e4b538f2eed82bb59ee63630daf031d2',9,2),(4,'AI is highly unethical','2023-04-02 22:26:42.557000','/pictures/upload_497055836858fc38038399d9f7755cab',9,4),(5,'No idea, man.','2023-04-02 22:27:14.186000','/pictures/upload_42232b6a77c4147e785336396a49748d',9,3),(6,'Y\'all stupid...','2023-04-02 22:30:20.272000','null',11,3),(7,'Go girl, give us nothing.','2023-04-02 22:30:54.530000','null',11,1),(8,'You should read the famous network book: A top-down aproach.','2023-04-02 22:31:51.279000','null',11,7),(9,'It means that all devices are connected through the Internet nowadays.','2023-04-02 22:32:26.069000','null',11,9),(10,'Machine learning is connected to deep learning. It is neural networks and generating models to predict stuff.','2023-04-02 22:32:45.742000','null',11,5),(11,'I have tried the new VR headset that just came out. It was super cool!!','2023-04-02 22:38:44.758000','null',15,12),(12,'Open source basically means that anyone can contribute to the development. It is improved by the users and those who invest the time in it.','2023-04-02 22:39:41.026000','null',15,14),(13,'I have heard about Python and Java but I don\'t know where to start from.','2023-04-02 22:39:56.647000','null',15,8),(14,'One memory is volatile while the other one is not.','2023-04-02 22:40:23.322000','null',15,2);
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

-- Dump completed on 2023-04-02 22:52:38
