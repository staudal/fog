CREATE DATABASE  IF NOT EXISTS `fog` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fog`;
-- MySQL dump 10.13  Distrib 8.0.30, for macos12 (x86_64)
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
) ENGINE=InnoDB AUTO_INCREMENT=487 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_line`
--

LOCK TABLES `order_line` WRITE;
/*!40000 ALTER TABLE `order_line` DISABLE KEYS */;
INSERT INTO `order_line` VALUES (366,115,6,6),(367,115,2,14),(368,115,10,23),(369,115,2,32),(370,115,2,38),(371,115,2,44),(372,115,2,50),(373,115,6,57),(374,115,12,58),(375,115,12,59),(376,115,2,60),(377,115,10,62),(378,115,10,61),(379,116,6,6),(380,116,2,15),(381,116,11,26),(382,116,2,33),(383,116,2,39),(384,116,2,44),(385,116,2,50),(386,116,6,57),(387,116,12,58),(388,116,12,59),(389,116,2,60),(390,116,11,62),(391,116,11,61),(392,116,1,63),(393,116,1,64),(394,116,3,65),(395,116,1,66),(396,116,1,70),(397,116,4,73),(398,117,6,6),(399,117,2,15),(400,117,11,23),(401,117,2,33),(402,117,2,39),(403,117,2,43),(404,117,2,49),(405,117,5,57),(406,117,12,58),(407,117,12,59),(408,117,2,60),(409,117,11,62),(410,117,11,61),(411,117,1,63),(412,117,1,64),(413,117,3,65),(414,117,1,66),(415,117,1,69),(416,117,4,73),(417,118,6,6),(418,118,2,15),(419,118,11,27),(420,118,2,33),(421,118,2,39),(422,118,2,45),(423,118,2,51),(424,118,7,57),(425,118,12,58),(426,118,12,59),(427,118,2,60),(428,118,11,62),(429,118,11,61),(430,118,1,63),(431,118,1,64),(432,118,3,65),(433,118,1,66),(434,118,1,70),(435,118,4,73),(436,118,4,74),(437,118,12,76),(438,119,6,6),(439,119,2,15),(440,119,11,27),(441,119,2,33),(442,119,2,39),(443,119,2,45),(444,119,2,51),(445,119,7,57),(446,119,12,58),(447,119,12,59),(448,119,2,60),(449,119,11,62),(450,119,11,61),(451,119,1,63),(452,119,1,64),(453,119,3,65),(454,119,1,66),(455,119,1,72),(456,119,4,73),(457,119,4,76),(458,119,12,76),(459,119,236,85),(460,120,6,6),(461,120,2,13),(462,120,9,23),(463,120,2,31),(464,120,2,37),(465,120,2,43),(466,120,2,49),(467,120,5,56),(468,120,12,58),(469,120,12,59),(470,120,2,60),(471,120,9,62),(472,120,9,61),(473,120,1,63),(474,120,1,64),(475,120,3,65),(476,120,1,66),(477,120,1,69),(478,120,4,73),(479,120,4,74),(480,120,12,74),(481,120,176,85),(482,120,2,87),(483,120,2,86),(484,120,2,88),(485,120,1,89),(486,120,32,90);
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
  `carportLength` int NOT NULL,
  `shedLength` int DEFAULT NULL,
  `shedWidth` int DEFAULT NULL,
  `customer_id` int NOT NULL,
  `totalPrice` int DEFAULT NULL,
  `discountPrice` int DEFAULT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_orders_customers_idx` (`customer_id`),
  CONSTRAINT `fk_orders_customers` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (115,500,500,0,0,1,11952,0,1),(116,540,600,0,0,1,16153,0,1),(117,480,600,210,410,1,15151,0,1),(118,600,600,240,530,1,18164,0,1),(119,600,600,300,530,1,22890,0,1),(120,480,450,210,410,1,18806,22000,3);
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
  `dimensions` varchar(255) DEFAULT NULL,
  `length` int NOT NULL,
  `price` int NOT NULL,
  `category` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (6,'97x97mm',300,150,'Stolpe','Skal graves 90 cm ned i jorden.'),(10,'47x200mm',300,170,'Rem',''),(11,'47x200mm',360,205,'Rem',NULL),(12,'47x200mm',420,240,'Rem',NULL),(13,'47x200mm',480,274,'Rem',NULL),(14,'47x200mm',540,308,'Rem',NULL),(15,'47x200mm',600,468,'Rem',NULL),(18,'47x200mm',300,170,'Spær','Skal hænge 35 cm ud over rem i begge sider.'),(19,'47x200mm',360,205,'Spær','Skal hænge 35 cm ud over rem i begge sider.'),(20,'47x200mm',420,240,'Spær','Skal hænge 35 cm ud over rem i begge sider.'),(21,'47x200mm',480,274,'Spær','Skal hænge 35 cm ud over rem i begge sider.'),(22,'47x200mm',540,308,'Spær','Skal hænge 35 cm ud over rem i begge sider.'),(23,'47x200mm',600,468,'Spær','Skal hænge 35 cm ud over rem i begge sider.'),(24,'47x200mm',660,515,'Rem',NULL),(25,'47x200mm',720,561,'Rem',NULL),(26,'47x200mm',660,515,'Spær','Skal hænge 35 cm ud over rem i begge sider.'),(27,'47x200mm',720,561,'Spær','Skal hænge 35 cm ud over rem i begge sider.'),(28,'25x200mm',300,171,'Understern (side)','Understernbræt til siderne på carporten.'),(29,'25x200mm',360,205,'Understern (side)','Understernbræt til siderne på carporten.'),(30,'25x200mm',420,240,'Understern (side)','Understernbræt til siderne på carporten.'),(31,'25x200mm',480,274,'Understern (side)','Understernbræt til siderne på carporten.'),(32,'25x200mm',540,308,'Understern (side)','Understernbræt til siderne på carporten.'),(33,'25x200mm',600,390,'Understern (side)','Understernbræt til siderne på carporten.'),(34,'25x125mm',300,108,'Overstern (side)','Oversternbræt til siderne på carporten.'),(35,'25x125mm',360,130,'Overstern (side)','Oversternbræt til siderne på carporten.'),(36,'25x125mm',420,151,'Overstern (side)','Oversternbræt til siderne på carporten.'),(37,'25x125mm',480,173,'Overstern (side)','Oversternbræt til siderne på carporten.'),(38,'25x125mm',540,195,'Overstern (side)','Oversternbræt til siderne på carporten.'),(39,'25x125mm',600,240,'Overstern (side)','Oversternbræt til siderne på carporten.'),(40,'25x200mm',300,171,'Understern (for/bag)','Understernbræt til for- og bagside på carporten.'),(41,'25x200mm',360,205,'Understern (for/bag)','Understernbræt til for- og bagside på carporten.'),(42,'25x200mm',420,240,'Understern (for/bag)','Understernbræt til for- og bagside på carporten.'),(43,'25x200mm',480,274,'Understern (for/bag)','Understernbræt til for- og bagside på carporten.'),(44,'25x200mm',540,308,'Understern (for/bag)','Understernbræt til for- og bagside på carporten.'),(45,'25x200mm',600,390,'Understern (for/bag)','Understernbræt til for- og bagside på carporten.'),(46,'25x125mm',300,108,'Overstern (for/bag)','Oversternbræt til for- og bagside på carporten.'),(47,'25x125mm',360,130,'Overstern (for/bag)','Oversternbræt til for- og bagside på carporten.'),(48,'25x125mm',420,151,'Overstern (for/bag)','Oversternbræt til for- og bagside på carporten.'),(49,'25x125mm',480,173,'Overstern (for/bag)','Oversternbræt til for- og bagside på carporten.'),(50,'25x125mm',540,195,'Overstern (for/bag)','Oversternbræt til for- og bagside på carporten.'),(51,'25x125mm',600,240,'Overstern (for/bag)','Oversternbræt til for- og bagside på carporten.'),(52,'109mm',240,139,'Tag','Overlappes 10 cm i bredden og 20 cm i længden.'),(53,'109mm',300,179,'Tag','Overlappes 10 cm i bredden og 20 cm i længden.'),(54,'109mm',360,199,'Tag','Overlappes 10 cm i bredden og 20 cm i længden.'),(55,'109mm',420,239,'Tag','Overlappes 10 cm i bredden og 20 cm i længden.'),(56,'109mm',480,269,'Tag','Overlappes 10 cm i bredden og 20 cm i længden.'),(57,'109mm',600,339,'Tag','Overlappes 10 cm i bredden og 20 cm i længden.'),(58,'10x120mm',0,17,'Bræddebolt','Til montering af rem på stolpe. 2 bolte pr. stolpe.'),(59,'40x40x11mm',0,9,'Firkantskive','Til montering af rem på stolpe. 2 skiver pr. stolpe.'),(60,'20x1mm',1000,349,'Hulbånd','Til vindkryds på spær under tag.'),(61,'190x50x2mm',0,35,'Universalbeslag (højre)','Til montering af spær på rem.'),(62,'190x50x2mm',0,35,'Universalbeslag (venstre)','Til montering af spær på rem.'),(63,'4x50mm',0,125,'Beslagskruer','(350 stk) Skruer til montering af universalbeslag og hulbånd.'),(64,'4,5x60mm',0,219,'Sternskruer','(200 stk) Skruer til montering af stern.'),(65,'4,9x30mm',0,429,'Bundskruer','(200 stk) Skruer til montering af Plastmo tag.'),(66,'38x73mm',420,67,'Z til skurdør','Lægde som laves til Z på bagsiden af skurets dør.'),(67,'47x200mm',300,170,'Rem til skur','Rem til sider i skur. Skal deles i 2.'),(68,'47x200mm',360,205,'Rem til skur','Rem til sider i skur. Skal deles i 2.'),(69,'47x200mm',420,240,'Rem til skur','Rem til sider i skur. Skal deles i 2.'),(70,'47x200mm',480,274,'Rem til skur','Rem til sider i skur. Skal deles i 2.'),(71,'47x200mm',540,308,'Rem til skur','Rem til sider i skur. Skal deles i 2.'),(72,'47x200mm',600,468,'Rem til skur','Rem til sider i skur. Skal deles i 2.'),(73,'97x97mm',300,150,'Stolpe til skur','Skal graves 90 cm ned i jorden.'),(74,'47x100mm',240,48,'Løsholte til skur','Til stabilisering af sider og gavle i skur.'),(75,'47x100mm',270,54,'Løsholte til skur','Til stabilisering af sider og gavle i skur.'),(76,'47x100mm',300,60,'Løsholte til skur','Til stabilisering af sider og gavle i skur.'),(77,'47x100mm',330,66,'Løsholte til skur','Til stabilisering af sider og gavle i skur.'),(78,'47x100mm',360,72,'Løsholte til skur','Til stabilisering af sider og gavle i skur.'),(79,'47x100mm',390,78,'Løsholte til skur','Til stabilisering af sider og gavle i skur.'),(80,'47x100mm',420,84,'Løsholte til skur','Til stabilisering af sider og gavle i skur.'),(81,'47x100mm',450,90,'Løsholte til skur','Til stabilisering af sider og gavle i skur.'),(82,'47x100mm',480,96,'Løsholte til skur','Til stabilisering af sider og gavle i skur.'),(83,'47x100mm',510,102,'Løsholte til skur','Til stabilisering af sider og gavle i skur.'),(84,'47x100mm',540,107,'Løsholte til skur','Til stabilisering af sider og gavle i skur.'),(85,'19x100mm',210,19,'Beklædning af skur','Til beklædning af skurets sider og gavle. 2 cm overlap pr. bræt.'),(86,'4,5x70mm',0,229,'Skruer til beklædning (lange)','(400 stk) Skruer til skurets yderste beklædningsbræt.'),(87,'4,5x50mm',0,129,'Skruer til beklædning (korte)','(300 stk) Skruer til skurets inderste beklædningsbræt.'),(88,'100x390mm',0,139,'T-hængsel til skurdør','Der skal bruges 2 T-hængsler til skurdøren.'),(89,'50x75mm',0,249,'Staldørsgreb til skur','Greb til skurets dør.'),(90,'50x50x2,5mm',0,15,'Beslag til løsholter','Beslag bruges til at fastmontere løsholter i skur.');
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

-- Dump completed on 2022-12-13  9:35:13
