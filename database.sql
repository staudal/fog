CREATE DATABASE  IF NOT EXISTS `fog` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fog`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: fog
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'Jakob','Staudal','jakob@staudal.dk','12345'),(2,'Line','Olsen','line@olsen.dk','12345'),(3,'Mette','Staudal','mette@staudal.dk','12345');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `id` varchar(255) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES ('50c7777a-13a3-4d28-8bf6-65f02f9b1fa5','Niels','Christensen','employee@fog.dk','12345');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_line`
--

DROP TABLE IF EXISTS `order_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_line` (
  `id` int NOT NULL AUTO_INCREMENT,
  `orders_id` int NOT NULL,
  `amount` int NOT NULL,
  `products_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_order_line_orders1_idx` (`orders_id`),
  KEY `fk_order_line_products1_idx` (`products_id`),
  CONSTRAINT `fk_order_line_orders1` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `fk_order_line_products1` FOREIGN KEY (`products_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=185 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_line`
--

LOCK TABLES `order_line` WRITE;
/*!40000 ALTER TABLE `order_line` DISABLE KEYS */;
INSERT INTO `order_line` VALUES (164,82,6,8),(165,82,4,10),(166,82,11,23),(167,82,2,38),(168,82,2,32),(169,82,2,39),(170,82,2,33),(171,83,8,8),(172,83,6,10),(173,83,15,23),(174,83,2,38),(175,83,2,32),(176,83,4,36),(177,83,4,30),(178,84,12,8),(179,84,9,10),(180,84,15,27),(181,84,2,51),(182,84,2,45),(183,84,4,36),(184,84,4,30);
/*!40000 ALTER TABLE `order_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `carportWidth` int NOT NULL,
  `carportHeight` int NOT NULL,
  `carportLength` int NOT NULL,
  `carportSlope` int NOT NULL,
  `customer_id` int NOT NULL,
  `totalPrice` int DEFAULT NULL,
  `discountPrice` int DEFAULT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_orders_customers_idx` (`customer_id`),
  CONSTRAINT `fk_orders_customers` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (82,500,300,600,0,1,9054,0,1),(83,500,300,800,0,1,11934,0,1),(84,600,300,800,0,1,14933,0,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `width` int NOT NULL,
  `height` int DEFAULT NULL,
  `length` int NOT NULL,
  `price` int NOT NULL,
  `category` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (5,97,97,240,120,'Stolpe','Skal graves 90 cm ned i jorden.'),(6,97,97,300,150,'Stolpe','Skal graves 90 cm ned i jorden.'),(7,97,97,360,180,'Stolpe','Skal graves 90 cm ned i jorden.'),(8,97,97,420,210,'Stolpe','Skal graves 90 cm ned i jorden.'),(9,97,97,480,240,'Stolpe','Skal graves 90 cm ned i jorden.'),(10,47,200,300,170,'Rem','Skal samles over hver stolpe med beslag.'),(11,47,200,360,205,'Rem','Skal samles over hver stolpe med beslag.'),(12,47,200,420,240,'Rem','Skal samles over hver stolpe med beslag.'),(13,47,200,480,274,'Rem','Skal samles over hver stolpe med beslag.'),(14,47,200,540,308,'Rem','Skal samles over hver stolpe med beslag.'),(15,47,200,600,468,'Rem','Skal samles over hver stolpe med beslag.'),(18,47,200,300,170,'Spær','Skal hænge 35 cm ud over rem i begge sider.'),(19,47,200,360,205,'Spær','Skal hænge 35 cm ud over rem i begge sider.'),(20,47,200,420,240,'Spær','Skal hænge 35 cm ud over rem i begge sider.'),(21,47,200,480,274,'Spær','Skal hænge 35 cm ud over rem i begge sider.'),(22,47,200,540,308,'Spær','Skal hænge 35 cm ud over rem i begge sider.'),(23,47,200,600,468,'Spær','Skal hænge 35 cm ud over rem i begge sider.'),(24,47,200,660,515,'Rem','Skal samles over hver stolpe med beslag.'),(25,47,200,720,561,'Rem','Skal samles over hver stolpe med beslag.'),(26,47,200,660,515,'Spær','Skal hænge 35 cm ud over rem i begge sider.'),(27,47,200,720,561,'Spær','Skal hænge 35 cm ud over rem i begge sider.'),(28,25,200,300,171,'Understern (side)','Understernbræt til siderne på carporten.'),(29,25,200,360,205,'Understern (side)','Understernbræt til siderne på carporten.'),(30,25,200,420,240,'Understern (side)','Understernbræt til siderne på carporten.'),(31,25,200,480,274,'Understern (side)','Understernbræt til siderne på carporten.'),(32,25,200,540,308,'Understern (side)','Understernbræt til siderne på carporten.'),(33,25,200,600,390,'Understern (side)','Understernbræt til siderne på carporten.'),(34,25,125,300,108,'Overstern (side)','Oversternbræt til siderne på carporten.'),(35,25,125,360,130,'Overstern (side)','Oversternbræt til siderne på carporten.'),(36,25,125,420,151,'Overstern (side)','Oversternbræt til siderne på carporten.'),(37,25,125,480,173,'Overstern (side)','Oversternbræt til siderne på carporten.'),(38,25,125,540,195,'Overstern (side)','Oversternbræt til siderne på carporten.'),(39,25,125,600,240,'Overstern (side)','Oversternbræt til siderne på carporten.'),(40,25,200,300,171,'Understern (for/bag)','Understernbræt til for- og bagside på carporten.'),(41,25,200,360,205,'Understern (for/bag)','Understernbræt til for- og bagside på carporten.'),(42,25,200,420,240,'Understern (for/bag)','Understernbræt til for- og bagside på carporten.'),(43,25,200,480,274,'Understern (for/bag)','Understernbræt til for- og bagside på carporten.'),(44,25,200,540,308,'Understern (for/bag)','Understernbræt til for- og bagside på carporten.'),(45,25,200,600,390,'Understern (for/bag)','Understernbræt til for- og bagside på carporten.'),(46,25,125,300,108,'Overstern (for/bag)','Oversternbræt til for- og bagside på carporten.'),(47,25,125,360,130,'Overstern (for/bag)','Oversternbræt til for- og bagside på carporten.'),(48,25,125,420,151,'Overstern (for/bag)','Oversternbræt til for- og bagside på carporten.'),(49,25,125,480,173,'Overstern (for/bag)','Oversternbræt til for- og bagside på carporten.'),(50,25,125,540,195,'Overstern (for/bag)','Oversternbræt til for- og bagside på carporten.'),(51,25,125,600,240,'Overstern (for/bag)','Oversternbræt til for- og bagside på carporten.');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-08  6:28:03
