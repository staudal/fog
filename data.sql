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
) ENGINE=InnoDB AUTO_INCREMENT=487 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_line`
--

LOCK TABLES `order_line` WRITE;
/*!40000 ALTER TABLE `order_line` DISABLE KEYS */;
INSERT INTO `order_line` VALUES (460,120,6,6),(461,120,2,13),(462,120,9,23),(463,120,2,31),(464,120,2,37),(465,120,2,43),(466,120,2,49),(467,120,5,56),(468,120,12,58),(469,120,12,59),(470,120,2,60),(471,120,9,62),(472,120,9,61),(473,120,1,63),(474,120,1,64),(475,120,3,65),(476,120,1,66),(477,120,1,69),(478,120,4,73),(479,120,4,74),(480,120,12,74),(481,120,176,85),(482,120,2,87),(483,120,2,86),(484,120,2,88),(485,120,1,89),(486,120,32,90);
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
INSERT INTO `orders` VALUES (120,480,450,210,410,1,18806,22000,3);
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
  `name` varchar(255) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `kind` int DEFAULT NULL,
  `length` int NOT NULL,
  `price` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `use` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (6,'RAW Stolpe Trykimpr??gneret 97x97 mm','Stolpe',1,300,150,'Stolpe til carport. Skal graves 90 cm ned i jorden.',0),(10,'RAW Sp??rtr?? C24 47x200 mm','Rem',1,300,170,'Rem til carport. Skal ligge p?? carportens stolper.',0),(11,'RAW Sp??rtr?? C24 47x200 mm','Rem',1,360,205,'Rem til carport. Skal ligge p?? carportens stolper.',0),(12,'RAW Sp??rtr?? C24 47x200 mm','Rem',1,420,240,'Rem til carport. Skal ligge p?? carportens stolper.',0),(13,'RAW Sp??rtr?? C24 47x200 mm','Rem',1,480,274,'Rem til carport. Skal ligge p?? carportens stolper.',0),(14,'RAW Sp??rtr?? C24 47x200 mm','Rem',1,540,308,'Rem til carport. Skal ligge p?? carportens stolper.',0),(15,'RAW Sp??rtr?? C24 47x200 mm','Rem',1,600,468,'Rem til carport. Skal ligge p?? carportens stolper.',0),(18,'RAW Sp??rtr?? C24 47x200 mm','Sp??r',1,300,170,'Sp??r til carport. Skal h??nge 35 cm over rem i begge sider.',0),(19,'RAW Sp??rtr?? C24 47x200 mm','Sp??r',1,360,205,'Sp??r til carport. Skal h??nge 35 cm over rem i begge sider.',0),(20,'RAW Sp??rtr?? C24 47x200 mm','Sp??r',1,420,240,'Sp??r til carport. Skal h??nge 35 cm over rem i begge sider.',0),(21,'RAW Sp??rtr?? C24 47x200 mm','Sp??r',1,480,274,'Sp??r til carport. Skal h??nge 35 cm over rem i begge sider.',0),(22,'RAW Sp??rtr?? C24 47x200 mm','Sp??r',1,540,308,'Sp??r til carport. Skal h??nge 35 cm over rem i begge sider.',0),(23,'RAW Sp??rtr?? C24 47x200 mm','Sp??r',1,600,468,'Sp??r til carport. Skal h??nge 35 cm over rem i begge sider.',0),(24,'RAW Sp??rtr?? C24 47x200 mm','Rem',1,660,515,'Rem til carport. Skal ligge p?? carportens stolper.',0),(25,'RAW Sp??rtr?? C24 47x200 mm','Rem',1,720,561,'Rem til carport. Skal ligge p?? carportens stolper.',0),(26,'RAW Sp??rtr?? C24 47x200 mm','Sp??r',1,660,515,'Sp??r til carport. Skal h??nge 35 cm over rem i begge sider.',0),(27,'RAW Sp??rtr?? C24 47x200 mm','Sp??r',1,720,561,'Sp??r til carport. Skal h??nge 35 cm over rem i begge sider.',0),(28,'RAW Fyr VTA Trykimpr??gneret 25x200 mm','Understern (side)',1,300,171,'Understernbr??t til siderne p?? carporten.',0),(29,'RAW Fyr VTA Trykimpr??gneret 25x200 mm','Understern (side)',1,360,205,'Understernbr??t til siderne p?? carporten.',0),(30,'RAW Fyr VTA Trykimpr??gneret 25x200 mm','Understern (side)',1,420,240,'Understernbr??t til siderne p?? carporten.',0),(31,'RAW Fyr VTA Trykimpr??gneret 25x200 mm','Understern (side)',1,480,274,'Understernbr??t til siderne p?? carporten.',0),(32,'RAW Fyr VTA Trykimpr??gneret 25x200 mm','Understern (side)',1,540,308,'Understernbr??t til siderne p?? carporten.',0),(33,'RAW Fyr VTA Trykimpr??gneret 25x200 mm','Understern (side)',1,600,390,'Understernbr??t til siderne p?? carporten.',0),(34,'RAW Fyr VTA Trykimpr??gneret 25x125 mm','Overstern (side)',1,300,108,'Oversternbr??t til siderne p?? carporten.',0),(35,'RAW Fyr VTA Trykimpr??gneret 25x125 mm','Overstern (side)',1,360,130,'Oversternbr??t til siderne p?? carporten.',0),(36,'RAW Fyr VTA Trykimpr??gneret 25x125 mm','Overstern (side)',1,420,151,'Oversternbr??t til siderne p?? carporten.',0),(37,'RAW Fyr VTA Trykimpr??gneret 25x125 mm','Overstern (side)',1,480,173,'Oversternbr??t til siderne p?? carporten.',0),(38,'RAW Fyr VTA Trykimpr??gneret 25x125 mm','Overstern (side)',1,540,195,'Oversternbr??t til siderne p?? carporten.',0),(39,'RAW Fyr VTA Trykimpr??gneret 25x125 mm','Overstern (side)',1,600,240,'Oversternbr??t til siderne p?? carporten.',0),(40,'RAW Fyr VTA Trykimpr??gneret 25x200 mm','Understern (for/bag)',1,300,171,'Understernbr??t til for- og bagside p?? carporten.',0),(41,'RAW Fyr VTA Trykimpr??gneret 25x200 mm','Understern (for/bag)',1,360,205,'Understernbr??t til for- og bagside p?? carporten.',0),(42,'RAW Fyr VTA Trykimpr??gneret 25x200 mm','Understern (for/bag)',1,420,240,'Understernbr??t til for- og bagside p?? carporten.',0),(43,'RAW Fyr VTA Trykimpr??gneret 25x200 mm','Understern (for/bag)',1,480,274,'Understernbr??t til for- og bagside p?? carporten.',0),(44,'RAW Fyr VTA Trykimpr??gneret 25x200 mm','Understern (for/bag)',1,540,308,'Understernbr??t til for- og bagside p?? carporten.',0),(45,'RAW Fyr VTA Trykimpr??gneret 25x200 mm','Understern (for/bag)',1,600,390,'Understernbr??t til for- og bagside p?? carporten.',0),(46,'RAW Fyr VTA Trykimpr??gneret 25x125 mm','Overstern (for/bag)',1,300,108,'Oversternbr??t til for- og bagside p?? carporten.',0),(47,'RAW Fyr VTA Trykimpr??gneret 25x125 mm','Overstern (for/bag)',1,360,130,'Oversternbr??t til for- og bagside p?? carporten.',0),(48,'RAW Fyr VTA Trykimpr??gneret 25x125 mm','Overstern (for/bag)',1,420,151,'Oversternbr??t til for- og bagside p?? carporten.',0),(49,'RAW Fyr VTA Trykimpr??gneret 25x125 mm','Overstern (for/bag)',1,480,173,'Oversternbr??t til for- og bagside p?? carporten.',0),(50,'RAW Fyr VTA Trykimpr??gneret 25x125 mm','Overstern (for/bag)',1,540,195,'Oversternbr??t til for- og bagside p?? carporten.',0),(51,'RAW Fyr VTA Trykimpr??gneret 25x125 mm','Overstern (for/bag)',1,600,240,'Oversternbr??t til for- og bagside p?? carporten.',0),(52,'PLASTMO EcoLite PVC Trapezplade 1090 mm','Tag',1,240,139,'Tag til carporten. Overlappes 10 cm i bredden og 20 cm i l??ngden.',0),(53,'PLASTMO EcoLite PVC Trapezplade 1090 mm','Tag',1,300,179,'Tag til carporten. Overlappes 10 cm i bredden og 20 cm i l??ngden.',0),(54,'PLASTMO EcoLite PVC Trapezplade 1090 mm','Tag',1,360,199,'Tag til carporten. Overlappes 10 cm i bredden og 20 cm i l??ngden.',0),(55,'PLASTMO EcoLite PVC Trapezplade 1090 mm','Tag',1,420,239,'Tag til carporten. Overlappes 10 cm i bredden og 20 cm i l??ngden.',0),(56,'PLASTMO EcoLite PVC Trapezplade 1090 mm','Tag',1,480,269,'Tag til carporten. Overlappes 10 cm i bredden og 20 cm i l??ngden.',0),(57,'PLASTMO EcoLite PVC Trapezplade 1090 mm','Tag',1,600,339,'Tag til carporten. Overlappes 10 cm i bredden og 20 cm i l??ngden.',0),(58,'NKT Br??ddebolt M12 120 mm','Br??ddebolt',0,0,17,'Til montering af rem p?? stolpe. 2 bolte pr. stolpe.',0),(59,'NKT Firkantskive M12 40x40 mm','Firkantskive',0,0,9,'Til montering af rem p?? stolpe. 2 skiver pr. stolpe.',0),(60,'PASLODE Hulb??nd 1x20 mm','Hulb??nd',0,1000,349,'Til vindkryds p?? sp??r under tag.',0),(61,'SIMPSON Universalbeslag H??jre','Universalbeslag (h??jre)',0,0,35,'Til montering af sp??r p?? rem.',0),(62,'SIMPSON Universalbeslag Venstre','Universalbeslag (venstre)',0,0,35,'Til montering af sp??r p?? rem.',0),(63,'RAPTOR TX20 Beslagskruer 40x5 mm','Beslagskruer',0,0,125,'(350 stk) Skruer til montering af universalbeslag og hulb??nd.',0),(64,'NKT SPUN??+ Universalskruer TX20 60x4,5 mm','Sternskruer',0,0,219,'(200 stk) Skruer til montering af stern.',0),(65,'PLASTMO Trapez Bundskruer 30x4,9 mm','Bundskruer',0,0,429,'(200 stk) Skruer til montering af Plastmo tag.',0),(66,'RAW Gran Tagl??gte 38x73 mm','Z til skurd??r',1,420,67,'L??gde som laves til Z p?? bagsiden af skurets d??r.',1),(67,'RAW Sp??rtr?? C24 47x200 mm','Rem til skur',1,300,170,'Rem til sider i skur. Skal deles i 2. En til hver side.',1),(68,'RAW Sp??rtr?? C24 47x200 mm','Rem til skur',1,360,205,'Rem til sider i skur. Skal deles i 2. En til hver side.',1),(69,'RAW Sp??rtr?? C24 47x200 mm','Rem til skur',1,420,240,'Rem til sider i skur. Skal deles i 2. En til hver side.',1),(70,'RAW Sp??rtr?? C24 47x200 mm','Rem til skur',1,480,274,'Rem til sider i skur. Skal deles i 2. En til hver side.',1),(71,'RAW Sp??rtr?? C24 47x200 mm','Rem til skur',1,540,308,'Rem til sider i skur. Skal deles i 2. En til hver side.',1),(72,'RAW Sp??rtr?? C24 47x200 mm','Rem til skur',1,600,468,'Rem til sider i skur. Skal deles i 2. En til hver side.',1),(73,'RAW Stolpe Trykimpr??gneret 97x97 mm','Stolpe til skur',1,300,150,'Stolpe til skur. Skal graves 90 cm ned i jorden.',1),(74,'RAW Sp??rtr?? C24 47x100 mm','L??sholte til skur',1,240,48,'L??sholte til side og gavl i skur. Skal saves til.',1),(75,'RAW Sp??rtr?? C24 47x100 mm','L??sholte til skur',1,270,54,'L??sholte til side og gavl i skur. Skal saves til.',1),(76,'RAW Sp??rtr?? C24 47x100 mm','L??sholte til skur',1,300,60,'L??sholte til side og gavl i skur. Skal saves til.',1),(77,'RAW Sp??rtr?? C24 47x100 mm','L??sholte til skur',1,330,66,'L??sholte til side og gavl i skur. Skal saves til.',1),(78,'RAW Sp??rtr?? C24 47x100 mm','L??sholte til skur',1,360,72,'L??sholte til side og gavl i skur. Skal saves til.',1),(79,'RAW Sp??rtr?? C24 47x100 mm','L??sholte til skur',1,390,78,'L??sholte til side og gavl i skur. Skal saves til.',1),(80,'RAW Sp??rtr?? C24 47x100 mm','L??sholte til skur',1,420,84,'L??sholte til side og gavl i skur. Skal saves til.',1),(81,'RAW Sp??rtr?? C24 47x100 mm','L??sholte til skur',1,450,90,'L??sholte til side og gavl i skur. Skal saves til.',1),(82,'RAW Sp??rtr?? C24 47x100 mm','L??sholte til skur',1,480,96,'L??sholte til side og gavl i skur. Skal saves til.',1),(83,'RAW Sp??rtr?? C24 47x100 mm','L??sholte til skur',1,510,102,'L??sholte til side og gavl i skur. Skal saves til.',1),(84,'RAW Sp??rtr?? C24 47x100 mm','L??sholte til skur',1,540,107,'L??sholte til side og gavl i skur. Skal saves til.',1),(85,'RAW Fyr VTA 19x100 mm','Bekl??dning af skur',1,210,19,'Til bekl??dning af skurets sider og gavle. 2 cm overlap pr. br??t.',1),(86,'NKT SPUN??+ Universalskruer TX20 70x4,5 mm','Skruer til bekl??dning (lange)',0,0,229,'(400 stk) Skruer til skurets yderste bekl??dningsbr??t.',1),(87,'NKT SPUN??+ Universalskruer TX20 50x4,5 mm','Skruer til bekl??dning (korte)',0,0,129,'(300 stk) Skruer til skurets inderste bekl??dningsbr??t.',1),(88,'PN T-h??ngsel 390 mm','T-h??ngsel til skurd??r',0,0,139,'Der skal bruges 2 T-h??ngsler til skurd??ren.',1),(89,'PN Staldd??rsgreb 50x75 mm','Staldd??rsgreb til skur',0,0,249,'Greb til skurets d??r.',1),(90,'PASLODE S35 PL Vinkelbeslag','Beslag til l??sholter',0,0,15,'Beslag bruges til at fastmontere l??sholter i skur.',1);
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

-- Dump completed on 2022-12-15 19:33:42
