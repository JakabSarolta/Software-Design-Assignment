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
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjoo8hp6d3gfwctr68dl2iaemj` (`user_id`),
  CONSTRAINT `FKjoo8hp6d3gfwctr68dl2iaemj` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,'What is a firewall and how does it protect your computer or network from malicious attacks?','2023-03-19 18:19:31.490000','ioasudbcia','What is a firewall?',15),(2,'What is the difference between RAM and ROM, and how do they affect the performance of a computer?','2023-03-19 18:20:01.244000','ioasudbcia','What is the difference between RAM and ROM?',15),(3,'What is cloud computing and how does it work? What are the benefits and potential drawbacks of using cloud services?','2023-03-19 18:20:28.394000','ioasudbcia','What is cloud computing?',15),(4,'What is artificial intelligence and how is it currently being used in various industries? What are the ethical considerations surrounding AI?','2023-03-19 18:21:22.392000','ioasudbcia','What is artificial intelligence?',8),(5,'What is machine learning and how does it differ from traditional programming? What are some applications of machine learning?','2023-03-19 18:21:40.605000','ioasudbcia','What is machine learning?',8),(6,'What is the internet of things and how does it work? What are some potential benefits and risks associated with IoT?','2023-03-19 18:23:05.528000','ioasudbcia','What is the internet of things?',7),(7,'What is a VPN and how does it work? How can you use a VPN to protect your online privacy and security?','2023-03-19 18:23:26.255000','ioasudbcia','What is a VPN?',7),(8,'What is coding and why is it important in the IT industry? What are some common programming languages and their uses?','2023-03-19 18:23:45.981000','ioasudbcia','What is coding?',7),(9,'What is cybersecurity and why is it important? What are some common types of cyber attacks and how can you protect yourself and your data?','2023-03-19 18:26:53.303000','ioasudbcia','What is cybersecurity?',1),(10,'What is data mining and how is it used in business and research? What are some common techniques for data mining?','2023-03-19 18:27:13.973000','ioasudbcia','What is data mining?',1),(11,'What is virtual reality and how is it used in gaming, education, and other industries? What are some potential applications for VR technology?','2023-03-19 18:28:10.987000','ioasudbcia','What is virtual reality?',14),(12,'What is blockchain and how does it work? What are some potential uses of blockchain technology beyond cryptocurrencies?','2023-03-19 18:28:34.090000','ioasudbcia','What is blockchain?',14),(13,'What is open source software and how does it differ from proprietary software? What are some popular open source programs?','2023-03-19 18:28:58.258000','ioasudbcia','What is open source software?',14),(14,'What is agile methodology and how is it used in software development? What are some benefits and drawbacks of using agile methodology?','2023-03-19 18:32:16.079000','ioasudbcia','What is agile methodology?',13),(15,'What is big data and how is it used in various industries? What are some challenges and opportunities associated with big data?','2023-03-19 18:32:35.020000','ioasudbcia','What is big data?',13);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-19 18:39:31
