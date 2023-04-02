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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,'What is a firewall and how does it protect your computer or network from malicious attacks?','2023-04-02 22:03:03.531000','/pictures/upload_1c3e3969f078839f29615e1edcd52a77','What is a firewall?',2),(2,'What is the difference between RAM and ROM, and how do they affect the performance of a computer?','2023-04-02 22:04:34.631000','/pictures/upload_31c997bb748ebc5d473dbd333624d4f8','What is the difference between RAM and ROM?',2),(3,'What is cloud computing and how does it work? What are the benefits and potential drawbacks of using cloud services?','2023-04-02 22:05:12.062000','/pictures/upload_e86a20aafc5938217075042e84aa4def','What is cloud computing?',2),(4,'What is artificial intelligence and how is it currently being used in various industries? What are the ethical considerations surrounding AI?','2023-04-02 22:06:06.771000','/pictures/upload_3d9f723313d05076628d7e348bcd263a','What is artificial intelligence?',4),(5,'What is machine learning and how does it differ from traditional programming? What are some applications of machine learning?','2023-04-02 22:06:38.765000','/pictures/upload_3dae07bf43464b041d730d2effa8ad42','What is machine learning?',4),(7,'What is a VPN and how does it work? How can you use a VPN to protect your online privacy and security?','2023-04-02 22:23:53.291000','/pictures/upload_74488e02789f2f7c199a06b8f3a24384','What is a VPN?',9),(8,'What is coding and why is it important in the IT industry? What are some common programming languages and their uses?','2023-04-02 22:24:47.367000','/pictures/upload_216e3f8515f3284e3caeea080e8c85e2','What is coding?',9),(9,'What is the internet of things and how does it work? What are some potential benefits and risks associated with IoT?','2023-04-02 22:28:07.613000','/pictures/upload_daa7d678029f9794f4635e91284b4b6e','What is the internet of things?',9),(10,'What is cybersecurity and why is it important? What are some common types of cyber attacks and how can you protect yourself and your data?','2023-04-02 22:28:58.638000','/pictures/upload_fcf5884cf5f63a1ddbfe0a1eb7f635ea','What is cybersecurity?',11),(11,'What is data mining and how is it used in business and research? What are some common techniques for data mining?','2023-04-02 22:29:26.841000','/pictures/upload_49f13f4839d1c8efe1f10920ffa06f1b','What is data mining?',11),(12,'What is virtual reality and how is it used in gaming, education, and other industries? What are some potential applications for VR technology?','2023-04-02 22:33:35.949000','/pictures/upload_ebadef8228359e1ec96bc48cd7db139b','What is virtual reality?',12),(13,'What is blockchain and how does it work? What are some potential uses of blockchain technology beyond cryptocurrencies?','2023-04-02 22:34:22.390000','/pictures/upload_040def7a0d0e1da42ec70a668fa14c2e','What is blockchain?',12),(14,'What is open source software and how does it differ from proprietary software? What are some popular open source programs?','2023-04-02 22:34:52.064000','/pictures/upload_4d779e5002024b47a89cca0752f74d80','What is open source software?',12),(15,'What is agile methodology and how is it used in software development? What are some benefits and drawbacks of using agile methodology?','2023-04-02 22:36:52.686000','/pictures/upload_159a2e9667dada99f8a69aced87ddc00','What is agile methodology?',15),(16,'What is big data and how is it used in various industries? What are some challenges and opportunities associated with big data?','2023-04-02 22:37:19.564000','null','What is big data?',15);
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

-- Dump completed on 2023-04-02 22:52:38
