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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `role` int DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `score` float DEFAULT NULL,
  `banned` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'oliverwright@gmail.com','Oliver','Wright','$2b$10$xLiY/qptla7rgWs7.l7TiuZzABS6NlW8Yj4VKuo0Fo8dXUuA00.iu','0751017097',0,'oliverwright',0,0),(2,'avamartinez@gmail.com','Ava','Martinez','$2b$10$iAtDX6X0hU.2dd7WQ2lzwunziQZ1Gst6DooHZ0m2p17NbyrSE8c.q','0751017097',0,'avamartinez',-1.5,0),(3,'jacobparker@gmail.com','Jacob','Parker','$2b$10$GFCcFnJtu1jP0TBckch21.4tA5bvdpzZIM8ZIuoM99AbtgQ7cvMwa','0751017097',0,'jacobparker',0,0),(4,'isabellachen@gmail.com','Isabella','Chen','$2b$10$7m.aClm/P2K2J6VOcfrqvuNWg21w.6016XLE.eT8OAN/1M99pqFUa','0751017097',0,'isabellachen',2.5,1),(5,'samuelrobinson@gmail.com','Samuel','Robinson','$2b$10$HH3zn7wLV7sJppUDviqwFuR45RcQRNOLZ3.LmSQGB1c0kDk1xlIHK','0751017097',0,'samuelrobinson',0,0),(6,'miagarcia@gmail.com','Mia','Garcia','$2b$10$QkpKUCwhmyMZ2rL1K5O14eseYe94llTvVxkxIhuHEb0UfXMFuCh4y','0751017097',0,'miagarcia',0,0),(7,'ethanclark@gmail.com','Ethan','Clark','$2b$10$gCWhUhm77Ioh.rMKXQu5UOQVSM6VfHeX/uzTO4w/0eZK2STx80iUG','0751017097',0,'ethanclark',0,0),(8,'emilywhite@gmail.com','Emily','White','$2b$10$aAS5DKCtrW99AGcDihamd.TfMJ8IYJrfdHn3CNMYp2BMmeDBhNOVO','0751017097',0,'emilywhite',0,0),(9,'williamperez@gmail.com','William','Perez','$2b$10$TRK7f3Di1KzTG7vsvhO6/uY6pdPRFY6/AoukBsycvmEGQGn7gNRe6','0751017097',0,'williamperez',0,0),(10,'sophialee@gmail.com','Sophia','Lee','$2b$10$71l2bKzF.3JDfQ3nCdF8uurqqrzuk9nBPEOVH1eDMmEKVYKv4yQhW','0751017097',0,'sophialee',0,0),(11,'alexanderdavis@gmail.com','Alexander','Davis','$2b$10$fRIMPBB8v9ElOBShh.zJr.TjP//PyoQJ7KiBXncinu82z1Scs.PdS','0751017097',0,'alexanderdavis',5,1),(12,'charlotteturner@gmail.com','Charlotte','Turner','$2b$10$OQWJo.ZaExNtr0MSCOTLNO9tbCzNcbmK1D/S/Z69FP7PcG5JtP6iS','0751017097',0,'charlotteturner',-1.5,1),(13,'jameshall@gmail.com','James','Hall','$2b$10$EcNnJwVDF.ITHFhsIgAkY.jEK7.lSQHlDTxD4bi/YTMs2o.60FJUm','0751017097',0,'jameshall',0,0),(14,'harperbrown@gmail.com','Harper','Brown','$2b$10$ZKxcz.hjyKX9QNWJ8JpEQuVAAs7WpqiYyRNtMXpvLWVQzgO/vjMI.','0751017097',0,'harperbrown',0,0),(15,'michaelwright@gmail.com','Michael','Wright','$2b$10$u8quEM7JV1RTdKPUSD5D0.wK3WpjLbA0kg9Z560vDeNHnU0Z2nffO','0751017097',0,'michaelwright',-1.5,0),(19,'oliverblack4@gmail.com','Black','Oliver','oliverblack','0751017097',0,'oliverblack4',0,0),(20,'oliverblack3@gmail.com','Black','Oliver','oliverblack','0751017097',0,'oliverblack3',0,0),(21,'jgysarolta@gmail.com','Sara','Sarolta','$2b$10$6vzIHUQqC5xosUEw6K1cO.MkYQTxK7Xfch4CafKyeo5wbJZjpANPS','0751017097',1,'jgysarolta',-1.5,0),(22,'vitusbalazs01@yahoo.com','Balazs','Vitus','$2b$10$74bxroyksl8WeVnyMQ4RxeuPDdVlrHVqfVxyVDWBS2Huv/JnI/hP.','0751017097',1,'balazs',0,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-23 20:58:29
