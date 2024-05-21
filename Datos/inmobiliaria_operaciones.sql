-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: inmobiliaria
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `operaciones`
--

DROP TABLE IF EXISTS `operaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operaciones` (
  `id_operaciones` int NOT NULL AUTO_INCREMENT,
  `nombre_operaciones` varchar(50) DEFAULT NULL,
  `id_modulo` int NOT NULL,
  PRIMARY KEY (`id_operaciones`),
  KEY `fk_id_mod` (`id_modulo`),
  CONSTRAINT `fk_id_mod` FOREIGN KEY (`id_modulo`) REFERENCES `modulo` (`id_modulo`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operaciones`
--

LOCK TABLES `operaciones` WRITE;
/*!40000 ALTER TABLE `operaciones` DISABLE KEYS */;
INSERT INTO `operaciones` VALUES (1,'Select',1),(2,'Select',2),(3,'Select',3),(4,'Select',4),(5,'Select',5),(6,'Select',6),(7,'Select',7),(8,'Select',8),(9,'Select',9),(10,'Select',10),(11,'Select',11),(12,'Select',12),(13,'Select',13),(14,'Select',14),(15,'Select',15),(16,'Delete',1),(17,'Delete',2),(18,'Delete',3),(19,'Delete',4),(20,'Delete',5),(21,'Delete',6),(22,'Delete',7),(23,'Delete',8),(24,'Delete',9),(25,'Delete',10),(26,'Delete',11),(27,'Delete',12),(28,'Delete',13),(29,'Delete',14),(30,'Insert',15),(31,'Insert',1),(32,'Insert',2),(33,'Insert',3),(34,'Insert',4),(35,'Insert',5),(36,'Insert',6),(37,'Insert',7),(38,'Insert',8),(39,'Insert',9),(40,'Insert',10),(41,'Insert',11),(42,'Insert',12),(43,'Insert',13),(44,'Insert',14),(45,'Insert',15),(46,'Update',1),(47,'Update',2),(48,'Update',3),(49,'Update',4),(50,'Update',5),(51,'Update',6),(52,'Update',7),(53,'Update',8),(54,'Update',9),(55,'Update',10),(56,'Update',11),(57,'Update',12),(58,'Update',13),(59,'Update',14),(60,'Update',15);
/*!40000 ALTER TABLE `operaciones` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-20  8:12:36
