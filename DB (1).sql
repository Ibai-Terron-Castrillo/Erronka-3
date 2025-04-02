-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: erronka3
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `aretoa`
--

DROP TABLE IF EXISTS `aretoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aretoa` (
  `id` int NOT NULL AUTO_INCREMENT,
  `izena` varchar(20) NOT NULL,
  `edukiera` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aretoa`
--

LOCK TABLES `aretoa` WRITE;
/*!40000 ALTER TABLE `aretoa` DISABLE KEYS */;
INSERT INTO `aretoa` VALUES (1,'Areto 1',30),(2,'Areto 2',30),(3,'Areto 3',30),(4,'Areto 4',30);
/*!40000 ALTER TABLE `aretoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bezeroa`
--

DROP TABLE IF EXISTS `bezeroa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bezeroa` (
  `id` int NOT NULL AUTO_INCREMENT,
  `izena` varchar(20) NOT NULL,
  `abizena1` varchar(20) NOT NULL,
  `abizena2` varchar(20) NOT NULL,
  `nan` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `pasahitza` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nan` (`nan`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bezeroa`
--

LOCK TABLES `bezeroa` WRITE;
/*!40000 ALTER TABLE `bezeroa` DISABLE KEYS */;
INSERT INTO `bezeroa` VALUES (1,'John','Doe','Mendoza','12345678A','jon@doe.com','1234'),(122,'Jon','Perez','Lopez','13786698R','jon1@example.com','pass123'),(123,'Ane','Gomez','Martinez','91255729N','ane2@example.com','pass123'),(124,'Iker','Fernandez','Garcia','75058765Z','iker3@example.com','pass123'),(125,'Nora','Lopez','Sanchez','14861473L','nora4@example.com','pass123'),(126,'Markel','Diaz','Jimenez','35288582Z','markel5@example.com','pass123'),(127,'Leire','Alvarez','Fernandez','96802769O','leire6@example.com','pass123'),(128,'Unai','Gutierrez','Ruiz','31146515B','unai7@example.com','pass123'),(129,'Miren','Santos','Hernandez','78328373M','miren8@example.com','pass123'),(130,'Aitor','Calle','Ortiz','69894753K','aitor9@example.com','pass123'),(131,'June','Martin','Castro','45984111X','june10@example.com','pass123'),(132,'Ekain','Iglesias','Moreno','64182764F','ekain11@example.com','pass123'),(133,'Oier','Serrano','Navarro','19017678Z','oier12@example.com','pass123'),(134,'Uxue','Torres','Ramos','27818575J','uxue13@example.com','pass123'),(135,'Julen','Vega','Reyes','92635301X','julen14@example.com','pass123'),(136,'Irati','Castillo','Romero','78053193L','irati15@example.com','pass123'),(137,'Ander','Delgado','Molina','61299157H','ander16@example.com','pass123'),(138,'Laia','Ortiz','Suarez','53626581B','laia17@example.com','pass123'),(139,'Gorka','Jimenez','Ortega','51289998M','gorka18@example.com','pass123'),(140,'Maialen','Marquez','Lorenzo','49029668F','maialen19@example.com','pass123'),(141,'Xabi','Morales','Parra','56553610S','xabi20@example.com','pass123'),(142,'Nahia','Herrera','Peña','50826719U','nahia21@example.com','pass123'),(143,'Luken','Cortes','Leon','19436381Q','luken22@example.com','pass123'),(144,'Eider','Cabrera','Mendez','17518369R','eider23@example.com','pass123'),(145,'Danel','Santiago','Flores','72704266H','danel24@example.com','pass123'),(146,'Odei','Bautista','Herrero','52658367Z','odei25@example.com','pass123'),(147,'Nahikari','Escudero','Medina','72854983I','nahikari26@example.com','pass123'),(148,'Hodei','Esteban','Pascual','54164017J','hodei27@example.com','pass123'),(149,'Alaia','Saez','Bravo','48363539D','alaia28@example.com','pass123'),(150,'Jonander','Vidal','Gallardo','89146481M','jonander29@example.com','pass123'),(151,'Amaia','Rey','Santos','23172285I','amaia30@example.com','pass123'),(153,'mmhtngrbf','mthnrgbf','nhfgb','12345678X','e@e.com','1234'),(154,'fufy','guffy','fufy','123456789','fuffy@go','123'),(156,'Paco','Fernandez','','12345678G','paco@gmail.com','1234');
/*!40000 ALTER TABLE `bezeroa` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `bezeroa_email_xehe` BEFORE INSERT ON `bezeroa` FOR EACH ROW BEGIN
    SET NEW.email = LOWER(NEW.email);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `erreserba`
--

DROP TABLE IF EXISTS `erreserba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `erreserba` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_bezero` int NOT NULL,
  `id_ordutegi` int NOT NULL,
  `kopurua` int NOT NULL,
  `egoera` enum('Prozesatzen','Ordaindua') DEFAULT 'Prozesatzen',
  PRIMARY KEY (`id`),
  KEY `id_bezero` (`id_bezero`),
  KEY `id_ordutegi` (`id_ordutegi`),
  CONSTRAINT `erreserba_ibfk_1` FOREIGN KEY (`id_bezero`) REFERENCES `bezeroa` (`id`) ON DELETE CASCADE,
  CONSTRAINT `erreserba_ibfk_2` FOREIGN KEY (`id_ordutegi`) REFERENCES `ordutegia` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erreserba`
--

LOCK TABLES `erreserba` WRITE;
/*!40000 ALTER TABLE `erreserba` DISABLE KEYS */;
INSERT INTO `erreserba` VALUES (1,1,1,2,'Ordaindua'),(5,1,31,1,'Ordaindua'),(6,154,11,2,'Ordaindua');
/*!40000 ALTER TABLE `erreserba` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `ezabatu_sarrerak_erreserba_ezabatzean` BEFORE DELETE ON `erreserba` FOR EACH ROW BEGIN
    DELETE FROM sarrera WHERE id_erreserba = OLD.id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `erreserbak_historikoa`
--

DROP TABLE IF EXISTS `erreserbak_historikoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `erreserbak_historikoa` (
  `id` int NOT NULL,
  `id_bezero` int NOT NULL,
  `id_ordutegi` int NOT NULL,
  `kopurua` int NOT NULL,
  `amaiera_data` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_bezero` (`id_bezero`),
  KEY `id_ordutegi` (`id_ordutegi`),
  CONSTRAINT `erreserbak_historikoa_ibfk_1` FOREIGN KEY (`id_bezero`) REFERENCES `bezeroa` (`id`) ON DELETE CASCADE,
  CONSTRAINT `erreserbak_historikoa_ibfk_2` FOREIGN KEY (`id_ordutegi`) REFERENCES `ordutegia` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erreserbak_historikoa`
--

LOCK TABLES `erreserbak_historikoa` WRITE;
/*!40000 ALTER TABLE `erreserbak_historikoa` DISABLE KEYS */;
INSERT INTO `erreserbak_historikoa` VALUES (2,1,2,1,'2025-03-19 16:49:48'),(7,1,33,3,'2025-04-02 09:22:55'),(8,156,33,3,'2025-04-02 09:22:55'),(9,154,33,4,'2025-04-02 09:22:55');
/*!40000 ALTER TABLE `erreserbak_historikoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eserlekua`
--

DROP TABLE IF EXISTS `eserlekua`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eserlekua` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_areto` int NOT NULL,
  `zenbakia` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_areto` (`id_areto`),
  CONSTRAINT `eserlekua_ibfk_1` FOREIGN KEY (`id_areto`) REFERENCES `aretoa` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eserlekua`
--

LOCK TABLES `eserlekua` WRITE;
/*!40000 ALTER TABLE `eserlekua` DISABLE KEYS */;
INSERT INTO `eserlekua` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,1,11),(12,1,12),(13,1,13),(14,1,14),(15,1,15),(16,1,16),(17,1,17),(18,1,18),(19,1,19),(20,1,20),(21,1,21),(22,1,22),(23,1,23),(24,1,24),(25,1,25),(26,1,26),(27,1,27),(28,1,28),(29,1,29),(30,1,30),(31,2,1),(32,2,2),(33,2,3),(34,2,4),(35,2,5),(36,2,6),(37,2,7),(38,2,8),(39,2,9),(40,2,10),(41,2,11),(42,2,12),(43,2,13),(44,2,14),(45,2,15),(46,2,16),(47,2,17),(48,2,18),(49,2,19),(50,2,20),(51,2,21),(52,2,22),(53,2,23),(54,2,24),(55,2,25),(56,2,26),(57,2,27),(58,2,28),(59,2,29),(60,2,30),(61,3,1),(62,3,2),(63,3,3),(64,3,4),(65,3,5),(66,3,6),(67,3,7),(68,3,8),(69,3,9),(70,3,10),(71,3,11),(72,3,12),(73,3,13),(74,3,14),(75,3,15),(76,3,16),(77,3,17),(78,3,18),(79,3,19),(80,3,20),(81,3,21),(82,3,22),(83,3,23),(84,3,24),(85,3,25),(86,3,26),(87,3,27),(88,3,28),(89,3,29),(90,3,30),(91,4,1),(92,4,2),(93,4,3),(94,4,4),(95,4,5),(96,4,6),(97,4,7),(98,4,8),(99,4,9),(100,4,10),(101,4,11),(102,4,12),(103,4,13),(104,4,14),(105,4,15),(106,4,16),(107,4,17),(108,4,18),(109,4,19),(110,4,20),(111,4,21),(112,4,22),(113,4,23),(114,4,24),(115,4,25),(116,4,26),(117,4,27),(118,4,28),(119,4,29),(120,4,30);
/*!40000 ALTER TABLE `eserlekua` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eserlekua_ordutegian`
--

DROP TABLE IF EXISTS `eserlekua_ordutegian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eserlekua_ordutegian` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_eserleku` int DEFAULT NULL,
  `id_ordutegi` int DEFAULT NULL,
  `beteta` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id_eserleku` (`id_eserleku`),
  KEY `id_ordutegi` (`id_ordutegi`),
  CONSTRAINT `eserlekua_ordutegian_ibfk_1` FOREIGN KEY (`id_eserleku`) REFERENCES `eserlekua` (`id`),
  CONSTRAINT `eserlekua_ordutegian_ibfk_2` FOREIGN KEY (`id_ordutegi`) REFERENCES `ordutegia` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=992 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eserlekua_ordutegian`
--

LOCK TABLES `eserlekua_ordutegian` WRITE;
/*!40000 ALTER TABLE `eserlekua_ordutegian` DISABLE KEYS */;
INSERT INTO `eserlekua_ordutegian` VALUES (1,1,1,0),(2,2,1,1),(3,3,1,1),(4,4,1,0),(5,5,1,0),(6,6,1,0),(7,7,1,0),(8,8,1,0),(9,9,1,0),(10,10,1,0),(11,11,1,0),(12,12,1,0),(13,13,1,0),(14,14,1,0),(15,15,1,0),(16,16,1,0),(17,17,1,0),(18,18,1,0),(19,19,1,0),(20,20,1,0),(21,21,1,0),(22,22,1,0),(23,23,1,0),(24,24,1,0),(25,25,1,0),(26,26,1,0),(27,27,1,0),(28,28,1,0),(29,29,1,0),(30,30,1,0),(31,1,2,1),(32,2,2,0),(33,3,2,0),(34,4,2,0),(35,5,2,0),(36,6,2,0),(37,7,2,0),(38,8,2,0),(39,9,2,0),(40,10,2,0),(41,11,2,0),(42,12,2,0),(43,13,2,0),(44,14,2,0),(45,15,2,0),(46,16,2,0),(47,17,2,0),(48,18,2,0),(49,19,2,0),(50,20,2,0),(51,21,2,0),(52,22,2,0),(53,23,2,0),(54,24,2,0),(55,25,2,0),(56,26,2,0),(57,27,2,0),(58,28,2,0),(59,29,2,0),(60,30,2,0),(61,1,3,0),(62,2,3,0),(63,3,3,0),(64,4,3,0),(65,5,3,0),(66,6,3,0),(67,7,3,0),(68,8,3,0),(69,9,3,0),(70,10,3,0),(71,11,3,0),(72,12,3,0),(73,13,3,0),(74,14,3,0),(75,15,3,0),(76,16,3,0),(77,17,3,0),(78,18,3,0),(79,19,3,0),(80,20,3,0),(81,21,3,0),(82,22,3,0),(83,23,3,0),(84,24,3,0),(85,25,3,0),(86,26,3,0),(87,27,3,0),(88,28,3,0),(89,29,3,0),(90,30,3,0),(91,1,4,0),(92,2,4,0),(93,3,4,0),(94,4,4,0),(95,5,4,0),(96,6,4,0),(97,7,4,0),(98,8,4,0),(99,9,4,0),(100,10,4,0),(101,11,4,0),(102,12,4,0),(103,13,4,0),(104,14,4,0),(105,15,4,0),(106,16,4,0),(107,17,4,0),(108,18,4,0),(109,19,4,0),(110,20,4,0),(111,21,4,0),(112,22,4,0),(113,23,4,0),(114,24,4,0),(115,25,4,0),(116,26,4,0),(117,27,4,0),(118,28,4,0),(119,29,4,0),(120,30,4,0),(121,1,5,0),(122,2,5,0),(123,3,5,0),(124,4,5,0),(125,5,5,0),(126,6,5,0),(127,7,5,0),(128,8,5,0),(129,9,5,0),(130,10,5,0),(131,11,5,0),(132,12,5,0),(133,13,5,0),(134,14,5,0),(135,15,5,0),(136,16,5,0),(137,17,5,0),(138,18,5,0),(139,19,5,0),(140,20,5,0),(141,21,5,0),(142,22,5,0),(143,23,5,0),(144,24,5,0),(145,25,5,0),(146,26,5,0),(147,27,5,0),(148,28,5,0),(149,29,5,0),(150,30,5,0),(151,1,6,0),(152,2,6,0),(153,3,6,0),(154,4,6,0),(155,5,6,0),(156,6,6,0),(157,7,6,0),(158,8,6,0),(159,9,6,0),(160,10,6,0),(161,11,6,0),(162,12,6,0),(163,13,6,0),(164,14,6,0),(165,15,6,0),(166,16,6,0),(167,17,6,0),(168,18,6,0),(169,19,6,0),(170,20,6,0),(171,21,6,0),(172,22,6,0),(173,23,6,0),(174,24,6,0),(175,25,6,0),(176,26,6,0),(177,27,6,0),(178,28,6,0),(179,29,6,0),(180,30,6,0),(181,1,7,0),(182,2,7,0),(183,3,7,0),(184,4,7,0),(185,5,7,0),(186,6,7,0),(187,7,7,0),(188,8,7,0),(189,9,7,0),(190,10,7,0),(191,11,7,0),(192,12,7,0),(193,13,7,0),(194,14,7,0),(195,15,7,0),(196,16,7,0),(197,17,7,0),(198,18,7,0),(199,19,7,0),(200,20,7,0),(201,21,7,0),(202,22,7,0),(203,23,7,0),(204,24,7,0),(205,25,7,0),(206,26,7,0),(207,27,7,0),(208,28,7,0),(209,29,7,0),(210,30,7,0),(211,1,8,0),(212,2,8,0),(213,3,8,0),(214,4,8,0),(215,5,8,0),(216,6,8,0),(217,7,8,0),(218,8,8,0),(219,9,8,0),(220,10,8,0),(221,11,8,0),(222,12,8,0),(223,13,8,0),(224,14,8,0),(225,15,8,0),(226,16,8,0),(227,17,8,0),(228,18,8,0),(229,19,8,0),(230,20,8,0),(231,21,8,0),(232,22,8,0),(233,23,8,0),(234,24,8,0),(235,25,8,0),(236,26,8,0),(237,27,8,0),(238,28,8,0),(239,29,8,0),(240,30,8,0),(241,31,9,0),(242,32,9,0),(243,33,9,0),(244,34,9,0),(245,35,9,0),(246,36,9,0),(247,37,9,0),(248,38,9,0),(249,39,9,0),(250,40,9,1),(251,41,9,1),(252,42,9,0),(253,43,9,0),(254,44,9,0),(255,45,9,0),(256,46,9,0),(257,47,9,0),(258,48,9,0),(259,49,9,0),(260,50,9,0),(261,51,9,0),(262,52,9,0),(263,53,9,0),(264,54,9,0),(265,55,9,0),(266,56,9,0),(267,57,9,0),(268,58,9,0),(269,59,9,0),(270,60,9,0),(271,31,10,0),(272,32,10,0),(273,33,10,0),(274,34,10,0),(275,35,10,0),(276,36,10,0),(277,37,10,0),(278,38,10,0),(279,39,10,0),(280,40,10,0),(281,41,10,0),(282,42,10,0),(283,43,10,0),(284,44,10,0),(285,45,10,0),(286,46,10,0),(287,47,10,0),(288,48,10,0),(289,49,10,0),(290,50,10,0),(291,51,10,0),(292,52,10,0),(293,53,10,0),(294,54,10,0),(295,55,10,0),(296,56,10,0),(297,57,10,0),(298,58,10,0),(299,59,10,0),(300,60,10,0),(301,31,11,0),(302,32,11,0),(303,33,11,0),(304,34,11,0),(305,35,11,0),(306,36,11,0),(307,37,11,0),(308,38,11,0),(309,39,11,1),(310,40,11,1),(311,41,11,0),(312,42,11,0),(313,43,11,0),(314,44,11,0),(315,45,11,0),(316,46,11,0),(317,47,11,0),(318,48,11,0),(319,49,11,0),(320,50,11,0),(321,51,11,0),(322,52,11,0),(323,53,11,0),(324,54,11,0),(325,55,11,0),(326,56,11,0),(327,57,11,0),(328,58,11,0),(329,59,11,0),(330,60,11,0),(331,31,12,0),(332,32,12,0),(333,33,12,0),(334,34,12,0),(335,35,12,0),(336,36,12,0),(337,37,12,0),(338,38,12,0),(339,39,12,0),(340,40,12,0),(341,41,12,0),(342,42,12,0),(343,43,12,0),(344,44,12,0),(345,45,12,0),(346,46,12,0),(347,47,12,0),(348,48,12,0),(349,49,12,0),(350,50,12,0),(351,51,12,0),(352,52,12,0),(353,53,12,0),(354,54,12,0),(355,55,12,0),(356,56,12,0),(357,57,12,0),(358,58,12,0),(359,59,12,0),(360,60,12,0),(361,31,13,0),(362,32,13,0),(363,33,13,0),(364,34,13,0),(365,35,13,0),(366,36,13,0),(367,37,13,0),(368,38,13,0),(369,39,13,0),(370,40,13,0),(371,41,13,0),(372,42,13,0),(373,43,13,0),(374,44,13,0),(375,45,13,0),(376,46,13,0),(377,47,13,0),(378,48,13,0),(379,49,13,0),(380,50,13,0),(381,51,13,0),(382,52,13,0),(383,53,13,0),(384,54,13,0),(385,55,13,0),(386,56,13,0),(387,57,13,0),(388,58,13,0),(389,59,13,0),(390,60,13,0),(391,31,14,0),(392,32,14,0),(393,33,14,0),(394,34,14,0),(395,35,14,0),(396,36,14,0),(397,37,14,0),(398,38,14,0),(399,39,14,0),(400,40,14,0),(401,41,14,0),(402,42,14,0),(403,43,14,0),(404,44,14,0),(405,45,14,0),(406,46,14,0),(407,47,14,0),(408,48,14,0),(409,49,14,0),(410,50,14,0),(411,51,14,0),(412,52,14,0),(413,53,14,0),(414,54,14,0),(415,55,14,0),(416,56,14,0),(417,57,14,0),(418,58,14,0),(419,59,14,0),(420,60,14,0),(421,31,15,0),(422,32,15,0),(423,33,15,0),(424,34,15,0),(425,35,15,0),(426,36,15,0),(427,37,15,0),(428,38,15,0),(429,39,15,0),(430,40,15,0),(431,41,15,0),(432,42,15,0),(433,43,15,0),(434,44,15,0),(435,45,15,0),(436,46,15,0),(437,47,15,0),(438,48,15,0),(439,49,15,0),(440,50,15,0),(441,51,15,0),(442,52,15,0),(443,53,15,0),(444,54,15,0),(445,55,15,0),(446,56,15,0),(447,57,15,0),(448,58,15,0),(449,59,15,0),(450,60,15,0),(451,31,16,0),(452,32,16,0),(453,33,16,0),(454,34,16,0),(455,35,16,0),(456,36,16,0),(457,37,16,0),(458,38,16,0),(459,39,16,0),(460,40,16,0),(461,41,16,0),(462,42,16,0),(463,43,16,0),(464,44,16,0),(465,45,16,0),(466,46,16,0),(467,47,16,0),(468,48,16,0),(469,49,16,0),(470,50,16,0),(471,51,16,0),(472,52,16,0),(473,53,16,0),(474,54,16,0),(475,55,16,0),(476,56,16,0),(477,57,16,0),(478,58,16,0),(479,59,16,0),(480,60,16,0),(481,61,17,0),(482,62,17,0),(483,63,17,0),(484,64,17,0),(485,65,17,0),(486,66,17,0),(487,67,17,0),(488,68,17,0),(489,69,17,0),(490,70,17,0),(491,71,17,0),(492,72,17,0),(493,73,17,0),(494,74,17,0),(495,75,17,0),(496,76,17,0),(497,77,17,0),(498,78,17,0),(499,79,17,0),(500,80,17,0),(501,81,17,0),(502,82,17,0),(503,83,17,0),(504,84,17,0),(505,85,17,0),(506,86,17,0),(507,87,17,0),(508,88,17,0),(509,89,17,0),(510,90,17,0),(511,61,18,0),(512,62,18,0),(513,63,18,0),(514,64,18,0),(515,65,18,0),(516,66,18,0),(517,67,18,0),(518,68,18,0),(519,69,18,0),(520,70,18,0),(521,71,18,0),(522,72,18,0),(523,73,18,0),(524,74,18,0),(525,75,18,0),(526,76,18,0),(527,77,18,0),(528,78,18,0),(529,79,18,0),(530,80,18,0),(531,81,18,0),(532,82,18,0),(533,83,18,0),(534,84,18,0),(535,85,18,0),(536,86,18,0),(537,87,18,0),(538,88,18,0),(539,89,18,0),(540,90,18,0),(541,61,19,0),(542,62,19,0),(543,63,19,0),(544,64,19,0),(545,65,19,0),(546,66,19,0),(547,67,19,0),(548,68,19,0),(549,69,19,0),(550,70,19,0),(551,71,19,0),(552,72,19,0),(553,73,19,0),(554,74,19,0),(555,75,19,0),(556,76,19,0),(557,77,19,0),(558,78,19,0),(559,79,19,0),(560,80,19,0),(561,81,19,0),(562,82,19,0),(563,83,19,0),(564,84,19,0),(565,85,19,0),(566,86,19,0),(567,87,19,0),(568,88,19,0),(569,89,19,0),(570,90,19,0),(571,61,20,0),(572,62,20,0),(573,63,20,0),(574,64,20,0),(575,65,20,0),(576,66,20,0),(577,67,20,0),(578,68,20,0),(579,69,20,0),(580,70,20,0),(581,71,20,0),(582,72,20,0),(583,73,20,0),(584,74,20,0),(585,75,20,0),(586,76,20,0),(587,77,20,0),(588,78,20,0),(589,79,20,0),(590,80,20,0),(591,81,20,0),(592,82,20,0),(593,83,20,0),(594,84,20,0),(595,85,20,0),(596,86,20,0),(597,87,20,0),(598,88,20,0),(599,89,20,0),(600,90,20,0),(601,61,21,0),(602,62,21,0),(603,63,21,0),(604,64,21,0),(605,65,21,0),(606,66,21,0),(607,67,21,0),(608,68,21,0),(609,69,21,0),(610,70,21,0),(611,71,21,0),(612,72,21,0),(613,73,21,0),(614,74,21,0),(615,75,21,0),(616,76,21,0),(617,77,21,0),(618,78,21,0),(619,79,21,0),(620,80,21,0),(621,81,21,0),(622,82,21,0),(623,83,21,0),(624,84,21,0),(625,85,21,0),(626,86,21,0),(627,87,21,0),(628,88,21,0),(629,89,21,0),(630,90,21,0),(631,61,22,0),(632,62,22,0),(633,63,22,0),(634,64,22,0),(635,65,22,0),(636,66,22,0),(637,67,22,0),(638,68,22,0),(639,69,22,0),(640,70,22,0),(641,71,22,0),(642,72,22,0),(643,73,22,0),(644,74,22,0),(645,75,22,0),(646,76,22,0),(647,77,22,0),(648,78,22,0),(649,79,22,0),(650,80,22,0),(651,81,22,0),(652,82,22,0),(653,83,22,0),(654,84,22,0),(655,85,22,0),(656,86,22,0),(657,87,22,0),(658,88,22,0),(659,89,22,0),(660,90,22,0),(661,61,23,0),(662,62,23,0),(663,63,23,0),(664,64,23,0),(665,65,23,0),(666,66,23,0),(667,67,23,0),(668,68,23,0),(669,69,23,0),(670,70,23,0),(671,71,23,0),(672,72,23,0),(673,73,23,0),(674,74,23,0),(675,75,23,0),(676,76,23,0),(677,77,23,0),(678,78,23,0),(679,79,23,0),(680,80,23,0),(681,81,23,0),(682,82,23,0),(683,83,23,0),(684,84,23,0),(685,85,23,0),(686,86,23,0),(687,87,23,0),(688,88,23,0),(689,89,23,0),(690,90,23,0),(691,61,24,0),(692,62,24,0),(693,63,24,0),(694,64,24,0),(695,65,24,0),(696,66,24,0),(697,67,24,0),(698,68,24,0),(699,69,24,0),(700,70,24,0),(701,71,24,0),(702,72,24,0),(703,73,24,0),(704,74,24,0),(705,75,24,0),(706,76,24,0),(707,77,24,0),(708,78,24,0),(709,79,24,0),(710,80,24,0),(711,81,24,0),(712,82,24,0),(713,83,24,0),(714,84,24,0),(715,85,24,0),(716,86,24,0),(717,87,24,0),(718,88,24,0),(719,89,24,0),(720,90,24,0),(721,91,25,0),(722,92,25,0),(723,93,25,0),(724,94,25,0),(725,95,25,0),(726,96,25,0),(727,97,25,0),(728,98,25,0),(729,99,25,0),(730,100,25,0),(731,101,25,0),(732,102,25,0),(733,103,25,0),(734,104,25,0),(735,105,25,0),(736,106,25,0),(737,107,25,0),(738,108,25,0),(739,109,25,0),(740,110,25,0),(741,111,25,0),(742,112,25,0),(743,113,25,0),(744,114,25,0),(745,115,25,0),(746,116,25,0),(747,117,25,0),(748,118,25,0),(749,119,25,0),(750,120,25,0),(751,91,26,0),(752,92,26,0),(753,93,26,0),(754,94,26,0),(755,95,26,0),(756,96,26,0),(757,97,26,0),(758,98,26,0),(759,99,26,0),(760,100,26,0),(761,101,26,0),(762,102,26,0),(763,103,26,0),(764,104,26,0),(765,105,26,0),(766,106,26,0),(767,107,26,0),(768,108,26,0),(769,109,26,0),(770,110,26,0),(771,111,26,0),(772,112,26,0),(773,113,26,0),(774,114,26,0),(775,115,26,0),(776,116,26,0),(777,117,26,0),(778,118,26,0),(779,119,26,0),(780,120,26,0),(781,91,27,0),(782,92,27,0),(783,93,27,0),(784,94,27,0),(785,95,27,0),(786,96,27,0),(787,97,27,0),(788,98,27,0),(789,99,27,0),(790,100,27,0),(791,101,27,0),(792,102,27,0),(793,103,27,0),(794,104,27,0),(795,105,27,0),(796,106,27,0),(797,107,27,0),(798,108,27,0),(799,109,27,0),(800,110,27,0),(801,111,27,0),(802,112,27,0),(803,113,27,0),(804,114,27,0),(805,115,27,0),(806,116,27,0),(807,117,27,0),(808,118,27,0),(809,119,27,0),(810,120,27,0),(811,91,28,0),(812,92,28,0),(813,93,28,0),(814,94,28,0),(815,95,28,0),(816,96,28,0),(817,97,28,0),(818,98,28,0),(819,99,28,0),(820,100,28,0),(821,101,28,0),(822,102,28,0),(823,103,28,0),(824,104,28,0),(825,105,28,0),(826,106,28,0),(827,107,28,0),(828,108,28,0),(829,109,28,0),(830,110,28,0),(831,111,28,0),(832,112,28,0),(833,113,28,0),(834,114,28,0),(835,115,28,0),(836,116,28,0),(837,117,28,0),(838,118,28,0),(839,119,28,0),(840,120,28,0),(841,91,29,0),(842,92,29,0),(843,93,29,0),(844,94,29,0),(845,95,29,0),(846,96,29,0),(847,97,29,0),(848,98,29,0),(849,99,29,0),(850,100,29,0),(851,101,29,0),(852,102,29,0),(853,103,29,0),(854,104,29,0),(855,105,29,0),(856,106,29,0),(857,107,29,0),(858,108,29,0),(859,109,29,0),(860,110,29,1),(861,111,29,0),(862,112,29,0),(863,113,29,0),(864,114,29,0),(865,115,29,0),(866,116,29,0),(867,117,29,0),(868,118,29,0),(869,119,29,0),(870,120,29,0),(871,91,30,0),(872,92,30,0),(873,93,30,0),(874,94,30,0),(875,95,30,0),(876,96,30,0),(877,97,30,0),(878,98,30,0),(879,99,30,0),(880,100,30,0),(881,101,30,0),(882,102,30,0),(883,103,30,0),(884,104,30,0),(885,105,30,0),(886,106,30,0),(887,107,30,0),(888,108,30,0),(889,109,30,0),(890,110,30,0),(891,111,30,0),(892,112,30,0),(893,113,30,0),(894,114,30,0),(895,115,30,0),(896,116,30,0),(897,117,30,0),(898,118,30,0),(899,119,30,0),(900,120,30,0),(901,91,31,0),(902,92,31,0),(903,93,31,0),(904,94,31,0),(905,95,31,0),(906,96,31,0),(907,97,31,0),(908,98,31,0),(909,99,31,0),(910,100,31,0),(911,101,31,1),(912,102,31,0),(913,103,31,0),(914,104,31,0),(915,105,31,0),(916,106,31,0),(917,107,31,0),(918,108,31,0),(919,109,31,0),(920,110,31,0),(921,111,31,0),(922,112,31,0),(923,113,31,0),(924,114,31,0),(925,115,31,0),(926,116,31,0),(927,117,31,0),(928,118,31,0),(929,119,31,0),(930,120,31,0),(931,91,32,0),(932,92,32,0),(933,93,32,0),(934,94,32,0),(935,95,32,0),(936,96,32,0),(937,97,32,0),(938,98,32,0),(939,99,32,0),(940,100,32,0),(941,101,32,0),(942,102,32,0),(943,103,32,0),(944,104,32,0),(945,105,32,0),(946,106,32,0),(947,107,32,0),(948,108,32,0),(949,109,32,0),(950,110,32,0),(951,111,32,0),(952,112,32,0),(953,113,32,0),(954,114,32,0),(955,115,32,0),(956,116,32,0),(957,117,32,0),(958,118,32,0),(959,119,32,0),(960,120,32,0);
/*!40000 ALTER TABLE `eserlekua_ordutegian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `langilea`
--

DROP TABLE IF EXISTS `langilea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `langilea` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `izena` varchar(100) NOT NULL,
  `abizena1` varchar(45) NOT NULL,
  `abizena2` varchar(45) DEFAULT NULL,
  `erabiltzailea` varchar(100) NOT NULL,
  `pasahitza` varchar(100) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `telefonoa` varchar(45) DEFAULT NULL,
  `nan` varchar(20) DEFAULT NULL,
  `Helbidea` varchar(200) DEFAULT NULL,
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `langilea`
--

LOCK TABLES `langilea` WRITE;
/*!40000 ALTER TABLE `langilea` DISABLE KEYS */;
INSERT INTO `langilea` VALUES (1,'Mikel','Lopez','Perez','mikel1','pass123','mikel1@empresa.com','600000001','12345678Z','Calle 1',1),(2,'Eneko','Sanchez','Garcia','eneko2','pass123','eneko2@empresa.com','600000002','23456789Y','Calle 2',0),(3,'Ainhoa','Gomez','Lopez','ainhoa3','pass123','ainhoa3@empresa.com','600000003','34567890X','Calle 3',1),(4,'Jokin','Martinez','Fernandez','jokin4','pass123','jokin4@empresa.com','600000004','45678901W','Calle 4',0),(5,'Maddi','Fernandez','Gomez','maddi5','pass123','maddi5@empresa.com','600000005','56789012V','Calle 5',1),(6,'Izan','Ruiz','Santos','izan6','pass123','izan6@empresa.com','600000006','67890123U','Calle 6',0),(7,'Aratz','Jimenez','Diaz','aratz7','pass123','aratz7@empresa.com','600000007','78901234T','Calle 7',1),(8,'Malen','Torres','Navarro','malen8','pass123','malen8@empresa.com','600000008','89012345S','Calle 8',0),(9,'Ane','Alvarez','Serrano','ane9','pass123','ane9@empresa.com','600000009','90123456R','Calle 9',1),(10,'Eider','Hernandez','Ortiz','eider10','pass123','eider10@empresa.com','600000010','01234567Q','Calle 10',0),(11,'Xabier','Calle','Iglesias','xabier11','pass123','xabier11@empresa.com','600000011','11234567P','Calle 11',1),(12,'Aritz','Vega','Romero','aritz12','pass123','aritz12@empresa.com','600000012','21234567O','Calle 12',0),(13,'Olatz','Ortiz','Molina','olatz13','pass123','olatz13@empresa.com','600000013','31234567N','Calle 13',1);
/*!40000 ALTER TABLE `langilea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordutegia`
--

DROP TABLE IF EXISTS `ordutegia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordutegia` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_pelikula` int NOT NULL,
  `id_areto` int NOT NULL,
  `eguna` date NOT NULL,
  `ordua` time NOT NULL,
  `amaitua` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id_pelikula` (`id_pelikula`),
  KEY `id_areto` (`id_areto`),
  CONSTRAINT `ordutegia_ibfk_1` FOREIGN KEY (`id_pelikula`) REFERENCES `pelikula` (`id`) ON DELETE CASCADE,
  CONSTRAINT `ordutegia_ibfk_2` FOREIGN KEY (`id_areto`) REFERENCES `aretoa` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordutegia`
--

LOCK TABLES `ordutegia` WRITE;
/*!40000 ALTER TABLE `ordutegia` DISABLE KEYS */;
INSERT INTO `ordutegia` VALUES (1,1,1,'2025-03-19','16:00:00',0),(2,2,1,'2025-03-19','18:30:00',0),(3,3,1,'2025-03-19','21:00:00',0),(4,4,1,'2025-03-20','16:00:00',0),(5,5,1,'2025-03-20','18:30:00',0),(6,6,1,'2025-03-20','21:00:00',0),(7,7,1,'2025-03-21','16:00:00',0),(8,8,1,'2025-03-21','18:30:00',0),(9,9,2,'2025-03-19','16:00:00',0),(10,10,2,'2025-03-19','18:30:00',0),(11,11,2,'2025-03-19','21:00:00',0),(12,12,2,'2025-03-20','16:00:00',0),(13,13,2,'2025-03-20','18:30:00',0),(14,14,2,'2025-03-20','21:00:00',0),(15,15,2,'2025-03-21','16:00:00',0),(16,16,2,'2025-03-21','18:30:00',0),(17,17,3,'2025-03-19','16:00:00',0),(18,18,3,'2025-03-19','18:30:00',0),(19,19,3,'2025-03-19','21:00:00',0),(20,20,3,'2025-03-20','16:00:00',0),(21,21,3,'2025-03-20','18:30:00',0),(22,22,3,'2025-03-20','21:00:00',0),(23,23,3,'2025-03-21','16:00:00',0),(24,24,3,'2025-03-21','18:30:00',0),(25,25,4,'2025-03-19','16:00:00',0),(26,26,4,'2025-03-19','18:30:00',0),(27,27,4,'2025-03-19','21:00:00',0),(28,28,4,'2025-03-20','16:00:00',0),(29,1,4,'2025-03-20','18:30:00',0),(30,2,4,'2025-03-20','21:00:00',0),(31,3,4,'2025-03-21','16:00:00',0),(32,4,4,'2025-03-21','18:30:00',0),(33,1,1,'2025-04-02','17:00:00',1);
/*!40000 ALTER TABLE `ordutegia` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trg_after_ordutegia_insert` AFTER INSERT ON `ordutegia` FOR EACH ROW BEGIN
    INSERT INTO eserlekua_ordutegian (id_eserleku, id_ordutegi, beteta)
    SELECT id, NEW.id, 0
    FROM eserlekua
    WHERE id_areto = NEW.id_areto;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `ordutegia_amaituta` AFTER UPDATE ON `ordutegia` FOR EACH ROW BEGIN
    IF NEW.amaitua = TRUE THEN
        -- Erreserbak erreserbak_historikoa taulara eraman
        INSERT INTO erreserbak_historikoa (id, id_bezero, id_ordutegi, kopurua, amaiera_data)
        SELECT id, id_bezero, id_ordutegi, kopurua, NOW()
        FROM erreserba
        WHERE id_ordutegi = NEW.id;

        -- Erreserba zaharrak ezabatu
        DELETE FROM erreserba WHERE id_ordutegi = NEW.id;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trg_ordutegia_amaitua` AFTER UPDATE ON `ordutegia` FOR EACH ROW BEGIN
    IF NEW.amaitua = TRUE THEN
        DELETE FROM eserlekua_ordutegian
        WHERE id_ordutegi = NEW.id;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `pelikula`
--

DROP TABLE IF EXISTS `pelikula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pelikula` (
  `id` int NOT NULL AUTO_INCREMENT,
  `izena` varchar(50) NOT NULL,
  `iraunaldia` int NOT NULL,
  `generoa` varchar(50) DEFAULT NULL,
  `sailkapena` enum('Haurrentzat','Familiarra','Helduentzat') DEFAULT NULL,
  `sinopsia` text,
  `aktoreak` text,
  `zuzendaria` varchar(50) DEFAULT NULL,
  `kartela` varchar(255) DEFAULT NULL,
  `trailerra` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pelikula`
--

LOCK TABLES `pelikula` WRITE;
/*!40000 ALTER TABLE `pelikula` DISABLE KEYS */;
INSERT INTO `pelikula` VALUES (1,'Inception',148,'Zientzia-fikzioa','Helduentzat','Dom Cobb lapurreta bereziak egiten dituen aditu bat da: besteen ametsen barrura sartuz, beren sekreturik sakonenak lapurtzen ditu. Baina talentu honek haren bizitza suntsitu du, eta jada ezingo du inoiz bere familiarekin egon. Azken aukera bat du: ideia bat beste baten buruan sartzea, eta ez kentzea. Operazio honek arrisku izugarria du, eta ametsen barruko errealitate eta fikzioaren arteko mugak gero eta lausoagoak bihurtzen dira.','Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page','Christopher Nolan','https://m.media-amazon.com/images/I/51NbVEuw1HL.jpg','https://www.youtube.com/embed/YoHD9XEInc0'),(2,'The Matrix',136,'Zientzia-fikzioa','Helduentzat','Neo hacker gaztea da, eta beti sentitu du mundu honetan zerbait ez dabilela ondo. Morfeo izeneko gizon misteriotsu batek egia erakusten dio: gure errealitatea Matrix izeneko simulazio bat besterik ez da, eta gizateria makina adimentsuen esklabu bihurtu dute. Orain, Neo matxinadaren itxaropena da, baina errealitatean bizirauteko borroka izugarria egin beharko du.\n\n','Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss','Lana Wachowski, Lilly Wachowski','https://m.media-amazon.com/images/I/51EG732BV3L.jpg','https://www.youtube.com/embed/m8e-FF8MsqU'),(3,'Interstellar',169,'Zientzia-fikzioa','Helduentzat','Lurra hondamendira bidean dago, eta gizateriaren biziraupena arriskuan dago. Cooper izeneko astronauta ohi batek, bere familiari agur esan eta espazioan sartzeko arrisku handia onartu beharko du, beste planeta bizigarri bat aurkitzeko. Tartean, zulo beltzak, erlatibitatearen efektuak eta gizateriaren mugez haratago doan bidaia epikoa biziko dute.','Matthew McConaughey, Anne Hathaway, Jessica Chastain','Christopher Nolan','https://m.media-amazon.com/images/M/MV5BYzdjMDAxZGItMjI2My00ODA1LTlkNzItOWFjMDU5ZDJlYWY3XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg','https://www.youtube.com/embed/zSWdZVtXT7E'),(4,'The Dark Knight',152,'Akzioa','Helduentzat','Gotham hiria kaosean erortzen ari da, eta Batmanek justizia ezartzen jarraitu nahi du. Baina orduan, Joker izeneko gaizkile ero eta manipulatzailea agertzen da, hiria izuaren menpe jarriz. Batmanek bere printzipio moralak kolokan jarriko ditu, eta haren arteko borroka ez da soilik fisikoa izango, baita psikologikoa ere.','Christian Bale, Heath Ledger, Aaron Eckhart','Christopher Nolan','https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_FMjpg_UX1000_.jpg','https://www.youtube.com/embed/EXeTwQWrcwY'),(5,'Titanic',195,'Drama','Familiarra','Jack eta Rose bi mundu ezberdinetakoak dira: Jack artista xume bat da, eta Rose familia aberats batekoa. Biak Titanic ontzi erraldoian ezagutuko dira eta maitasun istorio ahaztezina biziko dute. Baina Titanic hondoratzen hasten denean, bien bizitzak betiko aldatuko dira.','Leonardo DiCaprio, Kate Winslet, Billy Zane','James Cameron','https://th.bing.com/th/id/R.68c12203b7e600cd1b1ed08ea5e9706e?rik=QaoppBMEt%2fSO8w&pid=ImgRaw&r=0','https://www.youtube.com/embed/kVrqfYjkTdQ'),(6,'The Godfather',175,'Drama','Helduentzat','Corleone familia New Yorkeko mafiaren boteretsuenetakoa da. Vito Corleone, \"Aitajauna\", negozioak kontrolatzen ditu, baina bere seme Michael, hasieran mafia mundutik urrun dagoena, familiaren negozioetan sartzera behartuta egongo da. Bere odol hotzak eta adimen estrategikoak familia boteretsuena bihurtuko dute, baina atzera bueltarik gabe.','Marlon Brando, Al Pacino, James Caan','Francis Ford Coppola','https://m.media-amazon.com/images/M/MV5BNGEwYjgwOGQtYjg5ZS00Njc1LTk2ZGEtM2QwZWQ2NjdhZTE5XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg','https://www.youtube.com/embed/sY1S34973zA'),(7,'The Shawshank Redemption',142,'Drama','Helduentzat','Andy Dufresne bankari arrakastatsua espetxeratzen dute bere emaztea eta maitalea hil izana leporatuta. Shawshank espetxean, Red izeneko preso batekin adiskidetasuna eraikiko du, eta biak askatasunaren bila joango dira. Urteetan zehar, Andyren jakituria eta azkartasuna espetxeko egitura ustelak apurtzeko baliatuko dira.','Tim Robbins, Morgan Freeman, Bob Gunton','Frank Darabont','https://m.media-amazon.com/images/I/51zUbui+gbL.jpg','https://www.youtube.com/embed/6hB3S9bIaco'),(8,'Pulp Fiction',154,'Krimena','Helduentzat','Jules eta Vincent mafiosoak dira, eta euren bizitza drogak, dirua eta liskar arriskutsuek betetzen dute. Filma hainbat istorio gurutzatuen mosaiko konplexua da, non indarkeria, umorea eta elkarrizketa zorrotzak nagusi diren. Quentin Tarantinoren maisulana, denboraren eta narratibaren aurka jokatzen duena.','John Travolta, Uma Thurman, Samuel L. Jackson','Quentin Tarantino','https://img00.deviantart.net/9a22/i/2015/112/7/e/pulp_fiction_poster_by_mikeele-d5zfobl.jpg','https://www.youtube.com/embed/s7EdQ4FqbhY'),(9,'Gladiator',155,'Akzioa','Helduentzat','Maximus Erromatar Inperioko jeneral ohoretsua zen, baina konspirazio baten ondorioz traizionatua eta esklabu bihurtua izan da. Orain gladiadore gisa borrokatu beharko du Erromaren koliseoan, eta mendekua bilatuko du bere familiaren hilketagatik.','Russell Crowe, Joaquin Phoenix, Connie Nielsen','Ridley Scott','https://m.media-amazon.com/images/I/51GA6V6VE1L._AC_UF894,1000_QL80_.jpg','https://www.youtube.com/embed/owK1qxDselE'),(10,'Avengers: Endgame',181,'Zientzia-fikzioa','Familiarra','Thanosen erasoak biztanleriaren erdia suntsitu du, eta Avengers taldeak dena galdu du. Baina oraindik itxaropena dago: denboran bidaiatuz, galdutako guztia berreskuratzeko aukera izango dute. Azken borroka epikoa izango da, non heroi bakoitzak bere onena emango duen.','Robert Downey Jr., Chris Evans, Scarlett Johansson','Anthony Russo, Joe Russo','https://m.media-amazon.com/images/I/81ExhpBEbHL._AC_SY679_.jpg','https://www.youtube.com/embed/TcMBFSGVi1c'),(11,'Forrest Gump',142,'Drama','Familiarra','Forrest Gump ez da pertsona arrunta, baina bihotz handia du. Bere bizitzako pasarte garrantzitsuetan parte hartuko du, AEBetako historiaren une klabeetan egonik, baina beti Jennyren maitasunaren bila. Bere istorioak itxaropena eta ausardia irakasten digu.','Tom Hanks, Robin Wright, Gary Sinise','Robert Zemeckis','https://soilsong.com/old/aster-min.png','https://www.youtube.com/embed/bLvqoHBptjg'),(12,'The Lion King',88,'Animazioa','Haurrentzat','Simba lehoi txikia jaio da bere erreinuaren oinordeko gisa, baina bere osaba Scarren traizioak erbesteratu egingo du. Urteak igaro ahala, bere iragana onartu eta bere lekuari dagokion erreinua berreskuratu beharko du.\n\n','Matthew Broderick, Jeremy Irons, James Earl Jones','Roger Allers, Rob Minkoff','https://lumiere-a.akamaihd.net/v1/images/p_thelionking_19752_1_0b9de87b.jpeg?region=0%2C0%2C540%2C810','https://www.youtube.com/embed/4sj1MT05lAA'),(13,'The Avengers',143,'Zientzia-fikzioa','Familiarra','Loki, Asgardeko jainkoa, Lurrari mehatxua egiten dionean, Nick Furyk heroi talde bat bilduko du: Iron Man, Thor, Hulk, Captain America, Hawkeye eta Black Widow. Elkarrekin, mundua salbatzeko borrokatuko dute.','Robert Downey Jr., Chris Evans, Mark Ruffalo','Joss Whedon','https://m.media-amazon.com/images/M/MV5BNmE5ZjNmZTQtODIwMy00YzQwLTk5MTUtN2Y5MDFmNjMzYjI4XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg','https://www.youtube.com/embed/eOrNdBpGMv8'),(14,'Joker',122,'Drama','Helduentzat','Arthur Fleck clown eta komiko frustratua da, eta gizarteak baztertuta sentitzen da. Bere bizitzako injustiziek eta indarkeriak bultzaturik, Gotham hiriko figura kaotiko eta beldurgarriena bihurtuko da: Joker.\n\n','Joaquin Phoenix, Robert De Niro, Zazie Beetz','Todd Phillips','https://hips.hearstapps.com/hmg-prod/images/joker-poster-1554245051.jpg','https://www.youtube.com/embed/zAGVQLHvwOY'),(15,'Frozen',102,'Animazioa','Haurrentzat','Elsak izotz botereak ditu, baina ezin ditu kontrolatu. Bere arrebak, Annak, bidaia bat egingo du Elza aurkitu eta negu amaigabea gelditzeko. Abentura, magia eta ahizpatasunaren istorio hunkigarria.','Kristen Bell, Idina Menzel, Josh Gad','Chris Buck, Jennifer Lee','https://images.cdn1.buscalibre.com/fit-in/360x360/92/56/9256b8e9e8b8aefb4b9664854d7f15bd.jpg','https://www.youtube.com/embed/TbQm5doF_Uc'),(16,'Harry Potter and the Sorcerer\'s Stone',152,'Fantasia','Familiarra','Harry Potter mutiko umezurtza da, eta egun batean jakiten du sorgina dela. Hogwarts eskola magikora joango da, non lagunak egingo dituen eta bere gurasoen heriotzaren atzean dagoen misterio beldurgarria deskubrituko duen.','Daniel Radcliffe, Emma Watson, Rupert Grint','Chris Columbus','https://m.media-amazon.com/images/I/51UoqRAxwEL._AC_SY679_.jpg','https://www.youtube.com/embed/VyHV0BRtdxo'),(17,'Star Wars: A New Hope',121,'Zientzia-fikzioa','Familiarra','Luke Skywalker gazteak Darth Vader eta Inperioa garaitu nahi duen erresistentzian parte hartuko du. Jedi zaldun bat bihurtzeko bidean, Han Solo eta Leia printzesa ezagutuko ditu, eta galaxia osoa salbatzeko borroka hasiko du.','Mark Hamill, Harrison Ford, Carrie Fisher','George Lucas','https://m.media-amazon.com/images/I/81CIXJxQ3TL._AC_UF1000,1000_QL80_.jpg','https://www.youtube.com/embed/vZ734NWnAHA'),(18,'Spider-Man: No Way Home',148,'Zientzia-fikzioa','Familiarra','Peter Parkerrek bere nortasuna argitaratu ostean, arazo izugarriak izango ditu. Doctor Strange-ren laguntzarekin, errealitateak haustuko dira, eta beste unibertsoetako Spidermanekin elkartuko da.','Tom Holland, Zendaya, Benedict Cumberbatch','Jon Watts','https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/a8b0fd132117385.61a2ad5d83d0f.png','https://www.youtube.com/embed/JfVOs4VSpmA'),(19,'The Lord of the Rings: The Fellowship of the Ring',178,'Fantasia','Familiarra','Frodo izeneko hobbit gazteak eraztun boteretsu bat suntsitu behar du, Sauron Jaun Ilunaren eskuetara ez erortzeko. Lurralde Arriskutsuetatik bidaiatuko du, lagun talde batekin batera.','Elijah Wood, Ian McKellen, Viggo Mortensen','Peter Jackson','https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p28828_p_v8_ao.jpg','https://www.youtube.com/embed/V75dMMIW2B4'),(20,'The Hunger Games',142,'Akzioa','Helduentzat','Katniss Everdeen Panemeko herrialde zapaldutako biztanlea da, eta borondatez aurkezten da bere ahizpa babesteko Urteko Ehiza Jokoetan. Joko hilgarri hauetan, parte-hartzaileek elkar hil behar dute bizirik irauteko, Capitolioaren gozamenerako. Baina Katnissek ez du jokoan soilik biziraun nahi, baizik eta sistemaren aurkako matxinadaren sinbolo bihurtuko da.','Jennifer Lawrence, Josh Hutcherson, Liam Hemsworth','Gary Ross','https://m.media-amazon.com/images/M/MV5BMWI1OGM4YjQtNmIxNi00YmE2LWJkNTAtY2Q0YjU4NTI5NWQyXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg','https://www.youtube.com/embed/mfmrPu43DF8'),(21,'Deadpool',108,'Akzioa','Helduentzat','Wade Wilson mertzenario lotsagabea da, baina minbizia diagnostikatzen diotenean, esperimentu batean parte hartzea erabakitzen du. Gorputza sendatu arren, esperimentuak aurpegia guztiz desitxuratu eta indar erreserbako gaitasunak ematen dizkio. Orain, Deadpool izeneko antiheroi bilakatuta, mendekua bilatuko du bere maiteari egin diotenagatik, umore beltza eta borroka eszena ikaragarriak tartekatuz.','Ryan Reynolds, Morena Baccarin, Ed Skrein','Tim Miller','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcREUau271pMT9B8THJu9KDjMJI-gAfsN7n0kw&s','https://www.youtube.com/embed/ONHBaC-pfsk'),(22,'The Batman',176,'Akzioa','Helduentzat','Gotham hiria ustelkeriak eta kriminalitateak janda dago. Batmanek, oraindik hasiberri den superheroi gisa, enigma batzuen atzean dagoen pertsonaia ilun bat aurkitu beharko du: Riddler. Istorio honetan, Bruce Waynek detektibe lana ere egin beharko du, eta ez bakarrik borrokalari bezala jardun.','Robert Pattinson, Zoë Kravitz, Paul Dano','Matt Reeves','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTAfBoUfuPaNX9Nit01u6UVexu2mzGzaIqotg&s','https://www.youtube.com/embed/mqqft2x_Aa4'),(23,'Doctor Strange',115,'Zientzia-fikzioa','Familiarra','Stephen Strange ebakuntza kirurgikoetan espezializatutako sendagile bikaina zen, baina istripu batek bere eskuak hondatu zituen. Mendekotasuna galdu ostean, osatzeko bide espiritual bat bilatzen du eta Ancient One maisuaren ikasle bihurtzen da. Magiaren bidez, dimentsioak ulertuko ditu eta mundua babesteko botere ikaragarriak garatuko ditu.','Benedict Cumberbatch, Chiwetel Ejiofor, Rachel McAdams','Scott Derrickson','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRpsvImT3AyrqYgHc6pp9Z_bEkUMEftRKX5Bg&s','https://www.youtube.com/embed/HSzx-zryEgM'),(24,'Black Panther',134,'Zientzia-fikzioa','Familiarra','Wakanda mundutik ezkutatuta dagoen nazio teknologikoki aurreratuena da. Bertako erregea, T’Challa, Black Panther izenez ezagutzen den gerlaria da, baina bere tronuaren aurkako mehatxu batek borroka bat piztuko du bere herrialdearen etorkizuna babesteko.','Chadwick Boseman, Michael B. Jordan, Lupita Nyong\'o','Ryan Coogler','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQXF5dBYKUki5asU_nKCbuhQqHy-uiU7uPzwg&s','https://www.youtube.com/embed/xjDjIWPwcPU'),(25,'Toy Story',81,'Animazioa','Haurrentzat','Andy mutikoaren jostailuak benetako bizitza dute, baina bera konturatu gabe. Woody sheriffa eta Buzz Lightyear espazioko heroia hasieran aurkariak izango dira, baina abentura zirraragarri baten ostean, adiskidetasuna eta leialtasuna zer diren ikasiko dute.\n\n','Tom Hanks, Tim Allen, Don Rickles','John Lasseter','https://m.media-amazon.com/images/M/MV5BZTA3OWVjOWItNjE1NS00NzZiLWE1MjgtZDZhMWI1ZTlkNzYwXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg','https://www.youtube.com/embed/KYz2wyBy3kc'),(26,'The Incredibles',115,'Animazioa','Haurrentzat','Bob Parr, edo Mr. Incredible, eta bere familia guztia superheroiak dira, baina gobernuak haien jarduera debekatu du. Bizitza normal bat eramaten saiatzen diren bitartean, mehatxu berri batek guztien bizitza arriskuan jarriko du, eta familiak berriro elkartu beharko du gaizkile bati aurre egiteko.','Craig T. Nelson, Holly Hunter, Samuel L. Jackson','Brad Bird','https://m.media-amazon.com/images/M/MV5BMTY5OTU0OTc2NV5BMl5BanBnXkFtZTcwMzU4MDcyMQ@@._V1_.jpg','https://www.youtube.com/embed/eZbzbC9285I'),(27,'Finding Nemo',100,'Animazioa','Haurrentzat','Marlin aita bakarti eta babeslea da, eta bere semea, Nemo, koral arrezifean bizi da. Baina egun batean, Nemo arrantzaleek harrapatzen dute eta akuario batean amaitzen du. Orain, Marlinek itsasoan zehar bidaia epiko bat egin beharko du, Dory izeneko arrain ahazkorra lagun duela, bere semea berreskuratzeko','Albert Brooks, Ellen DeGeneres, Alexander Gould','Andrew Stanton','https://i.etsystatic.com/10683147/r/il/326ba3/1716757566/il_1588xN.1716757566_avdl.jpg','https://www.youtube.com/embed/2zLkasScy7A'),(28,'Coco',105,'Animazioa','Haurrentzat','Miguel mutiko gaztea da, eta musika maite du, baina bere familian musika debekatuta dago. Hil zubien gauean, bizidunen mundutik hildakoen mundura pasako da, eta han bere arbasoen istorioak eta bere familiaren sekretuak ezagutuko ditu. Bidaia hunkigarri batean, bere pasioa eta familiaren istorioa uztartuko ditu.','Anthony Gonzalez, Gael García Bernal, Benjamin Bratt','Lee Unkrich','https://lumiere-a.akamaihd.net/v1/images/p_coco_19736_fd5fa537.jpeg?region=0,0,540,810','https://www.youtube.com/embed/Rvr68u6k5sI');
/*!40000 ALTER TABLE `pelikula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sarrera`
--

DROP TABLE IF EXISTS `sarrera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sarrera` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_erreserba` int NOT NULL,
  `prezioa` decimal(10,2) NOT NULL,
  `id_eserleku` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_erreserba` (`id_erreserba`),
  KEY `id_eserleku` (`id_eserleku`),
  CONSTRAINT `sarrera_ibfk_1` FOREIGN KEY (`id_erreserba`) REFERENCES `erreserba` (`id`) ON DELETE CASCADE,
  CONSTRAINT `sarrera_ibfk_2` FOREIGN KEY (`id_eserleku`) REFERENCES `eserlekua` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sarrera`
--

LOCK TABLES `sarrera` WRITE;
/*!40000 ALTER TABLE `sarrera` DISABLE KEYS */;
INSERT INTO `sarrera` VALUES (1,1,12.00,2),(2,1,12.00,3),(7,5,10.00,101),(8,6,10.00,40),(9,6,10.00,39);
/*!40000 ALTER TABLE `sarrera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'erronka3'
--

--
-- Dumping routines for database 'erronka3'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-02  9:36:05
