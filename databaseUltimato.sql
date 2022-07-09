CREATE DATABASE  IF NOT EXISTS `dbuss` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dbuss`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: dbuss
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
-- Table structure for table `azienda`
--

DROP TABLE IF EXISTS `azienda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `azienda` (
  `nome` varchar(45) NOT NULL,
  `descrizione` varchar(1000) NOT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `azienda`
--

LOCK TABLES `azienda` WRITE;
/*!40000 ALTER TABLE `azienda` DISABLE KEYS */;
INSERT INTO `azienda` VALUES ('Chupa Chups','azienda bevande'),('Doritos','azienda patatine'),('Fanta','azienda bevande'),('Fluff','azienda creme'),('Hershey','azienda cioccolato'),('Hostess','azienda brioshe'),('Jif','azienda creme'),('Netsle','azienda cioccolato'),('Oreo','azienda cioccolato'),('Pringles','azienda patatine'),('Reeses','azienda cioccolato'),('Skittles','azienda caramelle');
/*!40000 ALTER TABLE `azienda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `tipo` varchar(45) NOT NULL,
  `descrizione` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES ('Bevande','Le bevande americane sono conosciute in tutto il mondo e alcune di esse sono diventate delle vere e proprie istituzioni!'),('Biscotti e Cereali','In questa sezione troverai un vasto assortimento di biscotti e cereali: dai tradizionali cookies americani passando per l’ampia selezione di Oreo. La colazione è il pasto più importante della giornata, ma la merenda non scherza!'),('Brioshes','Nella sezione Brioches troverai le storiche merendine americane, quelle per cui vanno matti i protagonisti degli storici film made in USA.'),('Caramelle e Chewingum','Caramelle e chewingum, da quelle storiche fino alle recenti produzioni, per tutti i gusti.'),('Cioccolato','A chi non piace il gusto intenso del cioccolato?'),('Creme e Sciroppi','In questa sezione troverai la nostra selezione di creme spalmabili e sciroppi: dal burro di arachidi, classico o con i pezzetti croccanti, fino allo sciroppo d’acero americano.'),('Patatine e Salatini','Patatine e salatini, in questa sezione troverai la più ampia scelta possibile di snack salati. Dalle patatine al mais tostato, passando per i pretzel e la carne essiccata.'),('Salse','In questa sezione troverai un vasto assortimento di salse perfette per impreziosire i tuoi piatti di carne o verdure. Classiche, dolci, piccanti o affumicate, saranno tantissime le sfumature di sapori che potrai scegliere per arricchire i tuoi pasti.');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `indirizzo`
--

DROP TABLE IF EXISTS `indirizzo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `indirizzo` (
  `idIndirizzo` int NOT NULL AUTO_INCREMENT,
  `numeroCivico` int NOT NULL,
  `Citta` varchar(45) NOT NULL,
  `Cap` int NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `via` varchar(45) NOT NULL,
  PRIMARY KEY (`idIndirizzo`),
  KEY `userIndirizzo_idx` (`username`),
  CONSTRAINT `userIndirizzo` FOREIGN KEY (`username`) REFERENCES `utente` (`username`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indirizzo`
--

LOCK TABLES `indirizzo` WRITE;
/*!40000 ALTER TABLE `indirizzo` DISABLE KEYS */;
INSERT INTO `indirizzo` VALUES (1,13,'Saviano',80039,NULL,'Via moloino'),(3,13,'Saviano',80039,'bensca','Molino'),(4,56,'Nola',80040,'bensca','Corso Europa'),(6,39,'Avellino',80040,NULL,'Lorenzo Ferrante'),(7,83,'Casoria',80039,'bensca','Via Italia'),(8,3,'Bisaccia',83044,NULL,'Corso Italia'),(9,31,'Saviano',80039,NULL,'Corso Italia'),(10,23,'Cicciano',86543,NULL,'Corso Italia'),(11,13,'Cicciano',80040,NULL,'Corso Italia'),(12,15,'Avellino',80023,NULL,'cacca'),(13,54,'Avellino',80021,NULL,'Corso Europa'),(14,83,'Casoria',33434,NULL,'Corso Europa'),(15,12,'Avellino',80032,'bensca','Corso Europa'),(16,23,'Avellino',80024,'rob65','Via lorenzo Ferrante'),(17,83,'Cicciano',80039,'bensca','Lorenzo Ferrante'),(18,14,'Salerno',80024,NULL,'Via Roma'),(19,12,'Napoli',8053,NULL,'Via Porto rico'),(20,12,'Salerno',80039,NULL,'Via Roma'),(21,56,'Abuquerque',80032,'Cookie','Via Messico');
/*!40000 ALTER TABLE `indirizzo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inserimento`
--

DROP TABLE IF EXISTS `inserimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inserimento` (
  `ordine` int NOT NULL,
  `prodotto` int NOT NULL,
  `prezzo` double NOT NULL,
  `iva` int NOT NULL,
  `quantita` int NOT NULL,
  `nomeProdotto` varchar(45) NOT NULL,
  `urlImmagine` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ordine`,`prodotto`),
  KEY `prodotto_idx` (`prodotto`),
  CONSTRAINT `ordine` FOREIGN KEY (`ordine`) REFERENCES `ordine` (`idOrdine`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inserimento`
--

LOCK TABLES `inserimento` WRITE;
/*!40000 ALTER TABLE `inserimento` DISABLE KEYS */;
INSERT INTO `inserimento` VALUES (60,1,6.54,10,1,'Jif Creamy Peanut Butter','images\\JifCreamyPeanutButter.png'),(60,3,4.43,11,1,'Fluff Marshmallows Strawberry','images\\FluffMarshmallowsStrawberry.png'),(60,6,2.19,10,1,'Hershey\'s Cookies\'N Creme','images\\Hershey\'sCookies\'NCreme.png'),(60,10,1.16,10,1,'Doritos Chilli Heatwave','images\\DoritosChilliHeatwave.png'),(61,2,6.54,10,1,'Jif Extra Crunchy Peanut Butter','images\\JifExtraCrunchyPeanutButter.png'),(61,7,2.19,10,2,'Hershey\'s Chocolate And Almond','images\\Hershey\'sChocolateAndAlmond.png'),(61,10,1.16,10,1,'Doritos Chilli Heatwave','images\\DoritosChilliHeatwave.png'),(62,4,4.43,11,1,'Fluff Marshmallows Vaniglia','images\\FluffMarshmallowsVaniglia.png'),(62,6,2.19,10,1,'Hershey\'s Cookies\'N Creme','images\\Hershey\'sCookies\'NCreme.png'),(63,1,6.54,10,1,'Jif Creamy Peanut Butter','images\\JifCreamyPeanutButter.png'),(63,2,6.54,10,1,'Jif Extra Crunchy Peanut Butter','images\\JifExtraCrunchyPeanutButter.png'),(63,3,4.43,11,1,'Fluff Marshmallows Strawberry','images\\FluffMarshmallowsStrawberry.png'),(63,4,4.43,11,1,'Fluff Marshmallows Vaniglia','images\\FluffMarshmallowsVaniglia.png'),(63,5,1.65,10,1,'Reese\'s Peanut Butter Cups','images\\Reese\'sPeanutButterCups.png'),(63,6,2.19,10,1,'Hershey\'s Cookies\'N Creme','images\\Hershey\'sCookies\'NCreme.png'),(63,7,2.19,10,1,'Hershey\'s Chocolate And Almond','images\\Hershey\'sChocolateAndAlmond.png'),(63,8,1.47,10,2,'Lion White','images\\LionWhite.png'),(63,9,1.57,10,1,'Doritos Original','images/DoritosOriginal.png'),(63,10,1.16,10,1,'Doritos Chilli Heatwave','images\\DoritosChilliHeatwave.png'),(63,12,4.39,10,3,'Pringles Smokey Bacon','images\\PringlesSmokeyBacon.png'),(63,13,4.39,10,1,'Pringles Cheesy Cheese','images\\PringlesCheesyCheese.png'),(63,14,4.39,10,1,'Pringles Cheese & Onion','images\\PringlesCheese&Onion.png'),(63,15,3.29,10,1,'Oreo Peanut Butter and Chocolate','images\\OreoPeanutButterAndChocolate.png'),(63,16,3.29,10,1,'Oreo Ice Cream Blueberry','images\\OreoIceCreamBlueberry.png'),(63,17,3.29,10,1,'Oreo Dark & White Chocolate','images\\OreoDark&WhiteChocolate.png'),(63,18,7.91,10,1,'Hostess Banana Twinkies','images\\HostessBananaTwinkies.png'),(63,19,7.05,12,1,'Hostess Twinkies Chocolate Cake','images\\HostessTwinkiesChocolateCake.png'),(63,20,9.89,10,1,'Hostess Cupcakes Holiday','images/Hostess_Cupcakes_Holiday.png'),(63,21,1.65,10,1,'Skittles Sour','images\\SkittlesSour.png'),(63,22,1.65,10,1,'Skittles Wild Berry','images\\SkittlesWildBerry.png'),(63,23,6.16,10,1,'Honey Chipotle Barbecue Sauce','images\\HoneyChipotleBarbecueSauce.png'),(63,24,6.05,10,1,'Hickory & Brown Sugar Barbecue Sauce','images\\Hickory&BrownSugarBarbecueSauce.png'),(63,25,6.05,10,1,'Sweet \'N Spicy Barbecue Sauce','images\\Sweet\'NSpicyBarbecueSauce.png'),(63,26,6.05,10,1,'Honey Barbecue Sauce','images\\HoneyBarbecueSauce.png'),(63,27,2.08,10,1,'Fanta Grape','images\\FantaGrape.png'),(63,28,2.08,10,1,'Fanta Berry','images\\FantaBerry.png'),(63,29,2.08,10,1,'Fanta Pineapple','images\\FantaPineapple.png'),(65,1,6.54,10,1,'Jif Creamy Peanut Butter','images\\JifCreamyPeanutButter.png'),(65,2,6.54,10,1,'Jif Extra Crunchy Peanut Butter','images\\JifExtraCrunchyPeanutButter.png'),(66,2,6.54,10,1,'Jif Extra Crunchy Peanut Butter','images\\JifExtraCrunchyPeanutButter.png'),(66,3,4.43,11,1,'Fluff Marshmallows Strawberry','images\\FluffMarshmallowsStrawberry.png'),(66,4,4.43,11,1,'Fluff Marshmallows Vaniglia','images\\FluffMarshmallowsVaniglia.png'),(67,1,6.54,10,3,'Jif Creamy Peanut Butter','images\\JifCreamyPeanutButter.png'),(67,2,6.54,10,1,'Jif Extra Crunchy Peanut Butter','images\\JifExtraCrunchyPeanutButter.png'),(67,3,4.43,11,1,'Fluff Marshmallows Strawberry','images\\FluffMarshmallowsStrawberry.png'),(67,4,4.43,11,1,'Fluff Marshmallows Vaniglia','images\\FluffMarshmallowsVaniglia.png'),(68,2,6.54,10,1,'Jif Extra Crunchy Peanut Butter','images\\JifExtraCrunchyPeanutButter.png'),(68,7,2.19,10,1,'Hershey\'s Chocolate And Almond','images\\Hershey\'sChocolateAndAlmond.png'),(68,10,1.16,10,1,'Doritos Chilli Heatwave','images\\DoritosChilliHeatwave.png'),(68,16,3.29,10,1,'Oreo Ice Cream Blueberry','images\\OreoIceCreamBlueberry.png'),(68,18,7.91,10,1,'Hostess Banana Twinkies','images\\HostessBananaTwinkies.png'),(68,24,6.05,10,1,'Hickory & Brown Sugar Barbecue Sauce','images\\Hickory&BrownSugarBarbecueSauce.png'),(68,29,2.08,10,1,'Fanta Pineapple','images\\FantaPineapple.png'),(68,30,2.08,10,1,'Fanta Peach','images\\FantaPeach.png'),(68,33,2.19,10,3,'Chupa Chups Melon Cream Soda','images\\ChupaChupsMelonCreamSoda.png'),(69,21,1.65,10,100,'Skittles Sour','images\\SkittlesSour.png'),(70,6,2.19,10,100,'Hershey\'s Cookies\'N Creme','images\\Hershey\'sCookies\'NCreme.png'),(70,15,3.29,10,12,'Oreo Peanut Butter and Chocolate','images\\OreoPeanutButterAndChocolate.png'),(71,71,7.21,12,51,'Cookie','images\\Cookie'),(72,73,21.49,11,5,'Cookie','images\\Cookie');
/*!40000 ALTER TABLE `inserimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordine`
--

DROP TABLE IF EXISTS `ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine` (
  `idOrdine` int NOT NULL AUTO_INCREMENT,
  `data` date NOT NULL,
  `ora` time NOT NULL,
  `utente` varchar(45) NOT NULL,
  `idIndirizzo` int DEFAULT NULL,
  `idPagamento` int DEFAULT NULL,
  `totale` double NOT NULL,
  PRIMARY KEY (`idOrdine`),
  KEY `utenteOrd_idx` (`utente`),
  KEY `indirizzoOrd_idx` (`idIndirizzo`),
  KEY `pagamentoOrd_idx` (`idPagamento`),
  CONSTRAINT `indirizzoOrd` FOREIGN KEY (`idIndirizzo`) REFERENCES `indirizzo` (`idIndirizzo`) ON UPDATE CASCADE,
  CONSTRAINT `pagamentoOrd` FOREIGN KEY (`idPagamento`) REFERENCES `pagamento` (`idPagamento`) ON UPDATE CASCADE,
  CONSTRAINT `utenteOrd` FOREIGN KEY (`utente`) REFERENCES `utente` (`username`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
INSERT INTO `ordine` VALUES (60,'2022-07-02','13:54:01','miky01',8,9,16.31),(61,'2022-07-02','14:11:43','miky01',6,16,14.07),(62,'2022-07-03','09:17:52','bensca',4,15,8.61),(63,'2022-07-03','17:16:46','bensca',3,18,8),(65,'2022-07-03','17:35:42','bensca',3,1,15),(66,'2022-07-03','17:38:28','bensca',3,1,17.39),(67,'2022-07-03','17:43:48','rob65',16,20,37.01),(68,'2022-07-03','18:06:43','bensca',17,19,39.86),(69,'2022-07-03','18:13:42','bensca',3,1,166.99),(70,'2022-07-04','08:22:42','Pepves',19,22,260.47),(71,'2022-07-04','08:26:54','Pepves',20,23,369.7),(72,'2022-07-04','09:51:22','Cookie',21,24,109.44);
/*!40000 ALTER TABLE `ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamento`
--

DROP TABLE IF EXISTS `pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagamento` (
  `idPagamento` int NOT NULL AUTO_INCREMENT,
  `dataScadenza` date NOT NULL,
  `cvv` int NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `numeroCarta` varchar(45) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idPagamento`),
  KEY `utentePagamento_idx` (`username`),
  CONSTRAINT `utentePagamento` FOREIGN KEY (`username`) REFERENCES `utente` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamento`
--

LOCK TABLES `pagamento` WRITE;
/*!40000 ALTER TABLE `pagamento` DISABLE KEYS */;
INSERT INTO `pagamento` VALUES (1,'2001-12-11',366,'Benedetto','Scala','3568-2568-4567-1234','bensca'),(2,'2022-05-03',344,'Michela','Faella','4560-9856-2345-1234','bensca'),(3,'2023-06-13',966,'Michela','Faella','4568-8967-2345-1234','miky01'),(4,'2022-05-25',232,'Anna','Sommese','4560-9856-2345-1234',NULL),(5,'2022-06-29',657,'Luigi','Scala','4560-9856-2345-1234',NULL),(6,'0212-12-10',232,'11','11','sds',NULL),(7,'0011-11-10',11,'11','11','ss',NULL),(8,'0011-11-10',11,'11','11','22',NULL),(9,'2023-05-23',111,'benedetto','scala','2345-2424-2321-1211',NULL),(10,'2023-05-23',111,'benedetto','scala','2345-2424-2321-1211',NULL),(11,'2022-07-28',323,'Anna','Sommese','4560-9856-2345-1134',NULL),(12,'2022-07-30',2323,'Luigi','Scala','4560-9856-2345-3222',NULL),(13,'2022-08-17',222,'Gerardo','Napolitano','4560-9856-2345-6432',NULL),(14,'2023-11-11',232,'bbb','bbsd','4568-8967-2345-1234',NULL),(15,'2022-07-28',567,'Mario','Rossi','4560-9856-2345-1134','bensca'),(16,'2022-07-29',321,'Michela','Faella','2325-2313-2345-2149',NULL),(17,'2022-07-28',234,'Romina','Cazzari','4568-8967-2345-1234',NULL),(18,'2022-09-07',123,'Umberto','Smaila','3333-2321-2321-2344','bensca'),(19,'2022-07-28',234,'Luigi','Scala','3242-2321-2321-2344','bensca'),(20,'2022-09-08',345,'Umberto','Smaila','3242-2321-2321-2344','rob65'),(21,'2022-08-25',564,'Peppe','Vessicchio','6679-3456-5432-1223',NULL),(22,'2022-10-25',323,'Luigi','Scala','2345-4521-2345-3245',NULL),(23,'2022-08-27',321,'Michela','Faella','2345-4521-2345-5623',NULL),(24,'2023-01-26',2324,'Cookie','Cook','2351-2321-2452-1233','Cookie');
/*!40000 ALTER TABLE `pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodotto`
--

DROP TABLE IF EXISTS `prodotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prodotto` (
  `idprodotto` int NOT NULL AUTO_INCREMENT,
  `azienda` varchar(45) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `prezzo` double NOT NULL,
  `descrizione` varchar(300) DEFAULT NULL,
  `urlImmagine` varchar(100) DEFAULT NULL,
  `quantita` int NOT NULL,
  `sconto` int NOT NULL DEFAULT '0',
  `IVA` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`idprodotto`),
  KEY `azienda_idx` (`azienda`) /*!80000 INVISIBLE */,
  KEY `nome_idx` (`nome`),
  KEY `categoria_idx` (`categoria`,`IVA`) /*!80000 INVISIBLE */,
  CONSTRAINT `azienda` FOREIGN KEY (`azienda`) REFERENCES `azienda` (`nome`) ON UPDATE CASCADE,
  CONSTRAINT `categoria` FOREIGN KEY (`categoria`) REFERENCES `categoria` (`tipo`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodotto`
--

LOCK TABLES `prodotto` WRITE;
/*!40000 ALTER TABLE `prodotto` DISABLE KEYS */;
INSERT INTO `prodotto` VALUES (1,'Jif','Creme e Sciroppi','Jif Creamy Peanut Butter',6.99,'Il burro d\'arachidi è uno degli ingredienti fondamentali nella tradizione culinaria americana, Jif Peanut Butter ti permette con i suoi 454 grammi,di prendere parte a questa tradizione. Il burro d?arachidi Jif oltre ad essere altamente proteico è studiato per coloro che manifestano intolleranze al g','images\\JifCreamyPeanutButter.png',120,15,10),(2,'Jif','Creme e Sciroppi','Jif Extra Crunchy Peanut Butter',6.99,'Il burro d?arachidi è un alimento di cui gli americani non possono proprio fare a meno, è ormai radicato nella tradizione culinaria da diversi decenni ed è la crema spalmabile preferita dal popolo USA spuntini e colazioni. Jif Crunchy Peanut Butter non è un semplice burro d?arachidi, infatti all?int','images\\JifExtraCrunchyPeanutButter.png',113,15,10),(3,'Fluff','Creme e Sciroppi','Fluff Marshmallows Strawberry',3.99,'La selezione di creme Fluff si arricchisce di un nuovo accattivante gusto: Fluff Marshmallow Strawberry. Una morbida crema di marshmallow al gusto fragola, ideale da combinare con frosting al formaggio o da utilizzare sui bagels o come farcitura per i muffin. Non hai voglia di scoprire perché fluff ','images\\FluffMarshmallowsStrawberry.png',145,0,11),(4,'Fluff','Creme e Sciroppi','Fluff Marshmallows Vaniglia',3.99,'Marshmallow alla vaniglia, questo è il sapore che la rende unica ed inimitabile. Fra le migliori e più duttili creme spalmabili della mia selezione, devo dire che questa variante è una straordinaria invenzione: ideale un po? per tutto.','images\\FluffMarshmallowsVaniglia.png',120,0,11),(5,'Reeses','Cioccolato','Reese\'s Peanut Butter Cups',1.5,'Un delizioso dolcetto al burro di arachidi, uno dei simboli del marchio Reese?s. Il cioccolato e la crema, dolce e saporita, stanno sempre bene insieme. Per questo motivo le Reese?s Peanut Butter Cups sono tra gli snack americani più celebri in assoluto. Due piccoli tortini ripieni di bontà amati da','images\\Reese\'sPeanutButterCups.png',122,0,10),(6,'Hershey','Cioccolato','Hershey\'s Cookies\'N Creme',1.99,'Hai voglia di una barretta di cioccolato? La risposta è sempre una: Hershey?s Cookies ?N creme; Creme. Una barretta di cioccolato bianco con un tocco di croccantezza dato dai pezzetti di cookies sbriciolati al suo interno. Scommettiamo che hai già l?acquolina in bocca! Hershey?s è uno dei marchi più','images\\Hershey\'sCookies\'NCreme.png',106,0,10),(7,'Hershey','Cioccolato','Hershey\'s Chocolate And Almond',1.99,'Ti piacciono le mandorle ma ti piace anche il cioccolato? Per la tua pausa non devi decidere tra uno o le altre, basta mangiare Hershey Milk Chocolate with Almonds. In questa barretta i tuoi ingredienti preferiti si incontrano per dare vita ad uno snack gustosissimo, per una pausa all?insegna della ','images\\Hershey\'sChocolateAndAlmond.png',110,0,10),(8,'Netsle','Cioccolato','Lion White',1.5,'Il Lion è stato creato dal dal maitre chocolatier Allan Norman. Da quando questa barretta si è presentata sulle scena degli snack ha subito delle evoluzioni, una di queste è Lion White A differenza della classica barretta, Lion White è arricchita dal sapore del cioccolato bianco che ricopre il famos','images\\LionWhite.png',132,11,10),(9,'Doritos','Patatine e salatini','Doritos Original',1.5,'Doritos è il venditore numero uno al mondo di patatine a base di mais; dopo averle assaggiate è facile capire perché le Doritos abbiano così successo. Doritos Cool Original è la ricetta che maggiormente si avvicina all’idea originaria da cui nascono queste patatine: un sapore che ricalca al meglio q','images/DoritosOriginal.png',100,5,10),(10,'Doritos','Patatine e Salatini','Doritos Chilli Heatwave',1.5,'Voglia di croccantezza? Doritos! Se ami sgranocchiare snack salati, sono sempre la risposta ai tuoi dilemmi sul cibo. Le Doritos Chilli sono un?esplosione di gusto racchiusa in un triangolino di mais. Piccanti al punto giusto, perfette da immergere in una ciotola di guacamole o di un?altra salsina s','images\\DoritosChilliHeatwave.png',100,30,10),(11,'Doritos','Patatine e Salatini','Doritos Tangy Cheese',1.5,'Doritos Tangy Cheese ha in ogni singola tortillas allinterno del pacchetto tutta la tradizione della cucina tex mex','images\\DoritosTangyCheese.png',100,34,12),(12,'Pringles','Patatine e Salatini','Pringles Smokey Bacon',3.99,'C?è un ingrediente che non manca mai in un?autentica ricetta americana: il bacon. Il sapore del bacon conquista sempre tutti, perciò i fan del celebre marchio di snack americani possono gustare le Pringles Bacon in tutta la loro bontà. Una volta stappato il tubo, verrai travolto dal profumo del baco','images\\PringlesSmokeyBacon.png',100,0,10),(13,'Pringles','Patatine e Salatini','Pringles Cheesy Cheese',3.99,'Non c?è niente di meglio di uno snack al formaggio, se non uno snack ancora più formaggioso. Se poi parliamo di Pringles Cheesy Cheese, non ci sono dubbi sul fatto che sia uno degli snack americani più buoni che potrai mai avere nella tua dispensa. Ideali per tutti gli amanti degli snack al formaggi','images\\PringlesCheesyCheese.png',100,0,10),(14,'Pringles','Patatine e Salatini','Pringles Cheese & Onion',3.99,'Ogni patatina Pringles Cheese & Onion ha il sapore di sandwiches di cipolla con in mezzo uno strato di formaggio I palati delicati possono tranquillamente stare lontani da questa variante ma gli amanti della cipolla devono assolutamente provare questa combinazione esplosiva.','images\\PringlesCheese&Onion.png',100,0,10),(15,'Oreo','Cioccolato','Oreo Peanut Butter and Chocolate',2.99,'Se già pensavi che i biscotti Oreo fossero irresistibili, allora non hai ancora provato la versione ?double delight?. Doppio gusto, doppia delizia: se il cioccolato di per sé è una garanzia, funziona sempre l?accoppiata con il burro di arachidi, un vero e proprio must per ogni snack americano che si','images\\OreoPeanutButterAndChocolate.png',95,0,10),(16,'Oreo','Biscotti e Cereali','Oreo Ice Cream Blueberry',2.99,'Avevi mai visto dei biscotti Oreo con il ripieno viola? Il più celebre è quello alla vaniglia ma ogni tanto bisogna uscire dagli schermi e per farlo potete assaggiare gli Oreo Ice Cream Blueberry. Lo strato in mezzo ai due biscotti è insolitamente viola, perché questa versione è al gusto di gelato a','images\\OreoIceCreamBlueberry.png',95,0,10),(17,'Oreo','Biscotti e Cereali','Oreo Dark & White Chocolate',2.99,'Credevi di aver visto e provato tutti i tipi di Oreo? Come sempre, ogni volta che pensi di aver visto, Oreo ti sorprende. Oreo Dark & White chocolate sono con doppia crema: metà cioccolato bianco, metà cioccolato fondente. Aspettano solo te!','images\\OreoDark&WhiteChocolate.png',95,0,10),(18,'Hostess','Brioshes','Hostess Banana Twinkies',8.99,'Le merendine Twinkies sono tra le più famose degli Stati Uniti, oltre alla classica versione con la crema è possibile anche trovare le Twinkies Banana.','images\\HostessBananaTwinkies.png',65,20,10),(19,'Hostess','Brioshes','Hostess Twinkies Chocolate Cake',8.99,'Quasi come un tortino al cioccolato, solo che si tratta di un tortino speciale, perché parliamo di Twinkies. Ti ricordiamo che gli americani vanno talmente matti per questo snack che, quando hanno rischiato di non poterlo mangiare più, si sono subito mobilitati.','images\\HostessTwinkiesChocolateCake.png',70,30,12),(20,'Hostess','Brioshes','Hostess Cupcakes Holiday',8.99,'Nell’infinito mondo di snack americani proposti dalla Hostess, c’è sempre spazio per le edizioni limitate e a tema festivo. Tra queste ci sono le Hostess Cupcakes Holiday, delle irresistibili cupcake ispirate al periodo natalizio.','images/Hostess_Cupcakes_Holiday.png',65,0,10),(21,'Skittles','Caramelle e Chewingum','Skittles Sour',1.5,'Skittles Sour ti riserva una meravigliosa pioggia di colori?.aspri! I gusti di fragola, limone, lime e uva si presentano con l?asprezza di chi vuole sfidare i palati alla ricerca di sensazioni sempre particolari.','images\\SkittlesSour.png',72,0,10),(22,'Skittles','Caramelle e Chewingum','Skittles Wild Berry',1.5,'Fragola, uva, ciliegia, mora, lampone, sono i gusti di cui si compone Skittles Wild Berry. Ancora una volta potrai giocare con i colori di queste caramelle ma soprattutto potrai godere a pieno della capacità di Skittles di trasformare in un?opera d?arte tutte le proprie caramelle! ','images\\SkittlesWildBerry.png',72,0,10),(23,'Chupa Chups','Salse','Honey Chipotle Barbecue Sauce',5.6,'Non è facile combinare due sapori distinti come avviene nella salsa Sweet Baby Ray?s Honey Chipotle Barbecue Sauce. In questa famosa salsa barbecue, la dolce nota del miele crea un contrasto deciso, ma bilanciato, con i peperoncini Chipotle affumicati. Per quanto la Sweet Baby Ray?s Honey Chipotle B','images\\HoneyChipotleBarbecueSauce.png',50,0,10),(24,'Chupa Chups','Salse','Hickory & Brown Sugar Barbecue Sauce',5.5,'Tra le Salse prodotte da Baby Ray?s, sicuramente quella maggiormente riconosciuta a livello internazionale per la sua bontà è Sweet Baby Ray?s Hickory & Brown Sugar Barbecue Sauce. Anche il sito stesso di Sweet Baby Ray?s definisce questa salsa ?la propria salsa barbecue vincente?. I modi per utiliz','images\\Hickory&BrownSugarBarbecueSauce.png',50,0,10),(25,'Chupa Chups','Salse','Sweet \'N Spicy Barbecue Sauce',5.5,'Sweet Baby Ray?s Sweet ?N Spicy Barbecue Sauce deve le sue note di piccantezza al peperoncino Jalapeno. Se ami impreziosire le tue ricette con delle salse barbecue piccanti questa salsa soddisferà le tue aspettative. Sweet Baby Ray?s è famosa anche per la propria capacità di creare ricette fantastic','images\\Sweet\'NSpicyBarbecueSauce.png',50,0,10),(26,'Chupa Chups','Salse','Honey Barbecue Sauce',5.5,'Il miglior pasto per utilizzare Sweet Baby Ray?s Honey Barbecue Sauce è il prossimo! Su questa salsa barbecue ci piace davvero azzardare questa affermazione ma siamo sicuri che chiunque provi questa salsa bbq addolcita dal miele, non avrà nulla da obbiettare! L?equilibrata nota donata dal miele rend','images\\HoneyBarbecueSauce.png',50,0,10),(27,'Fanta','Bevande','Fanta Grape',1.99,'In Italia le bevande a base di uva non sono popolarissime mentre negli USA tutti ne vanno matti: Fanta Grape ne è la prova. Adesso puoi finalmente assaggiare anche tu, senza fossilizzarsi sui soliti gusti di Fanta. La celebre bevanda, infatti, esiste in moltissime varianti, a seconda del posto del m','images\\FantaGrape.png',100,5,10),(28,'Fanta','Bevande','Fanta Berry',1.99,'Ti piace la Fanta ma sei stanco dei soliti gusti? Nessun problema, c?è Fanta Berry! Il drink esiste oggi in tantissime varianti e una delle più buone è sicuramente quella al mirtillo blu. Perfetta per rinfrescarsi nei caldi pomeriggi estivi e per fare una pausa tra un impegno e l?altro, la Fanta Ber','images\\FantaBerry.png',100,5,10),(29,'Fanta','Bevande','Fanta Pineapple',1.99,'Fanta Pineapple è un?altra delle varianti di Fanta consumate oltreoceano. Il sapore dell\'Ananas frizzante la rende una bevanda dalle alte capacità dissetanti, Fanta Pineapple è sicuramente una delle varianti da provare! Se collezioni lattine, dai un tocco di colore acceso con Fanta Pineapple.','images\\FantaPineapple.png',100,5,10),(30,'Fanta','Bevande','Fanta Peach',1.99,'La Fanta è una bevanda intramontabile, in Italia non conosciamo tutte le varianti esistenti quindi approfittane per assaggiare la versione alla pesca. Gli aromi sono al 100% naturali e le caratteristiche sono le stesse che ricordi della Fanta classica: è dolce, frizzante, perfetta per dissetarti in ','images\\FantaPeach.png',100,5,10),(31,'Chupa Chups','Bevande','Chupa Chups Orange Soda',1.99,'Dagli anni Novanta con furore: chi non ha mai gustato i lecca-lecca Chupa Chups? Spesso sono legati a ricordi d?infanzia, ma queste caramelle squisite vanno bene a tutte le età, non fanno distinzioni. E vanno bene anche rivisitate in altri formati, magari come bibite in lattina. ','images\\ChupaChupsOrangeSoda.png',120,0,10),(32,'Chupa Chups','Bevande','Chupa Chups Strawberry Soda',1.99,'Hai mai pensato che le irresistibili Chupa Chups, icona degli anni Novanta, potessero diventare un drink? È proprio quello che è successo, così potrai bere la tua Chupa Chups Soda Strawberry e riscoprire tutta la bontà dei lecca-lecca in un formato completamente nuovo.','images\\ChupaChupsStrawberrySoda.png',120,0,10),(33,'Chupa Chups','Bevande','Chupa Chups Melon Cream Soda',1.99,'I lecca-lecca Chupa Chups al melone sono un gusto imperdibile, ma li hai mai assaggiati in versione drink? Adesso puoi farlo, scopri subito la bontà di questa soda, non vorrai più restare senza! Chupa Chups Melon Cream Soda è la versione soft drink di un Chupa Chups gusto melone, una sorsata di fres','images\\ChupaChupsMelonCreamSoda.png',120,0,10),(73,'Netsle','Brioshes','Cookie',22,'chh','images\\Cookie',123,12,11);
/*!40000 ALTER TABLE `prodotto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `username` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `dataNascita` date NOT NULL,
  `autorizzazione` varchar(45) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES ('anna','anna.sommese@libero.it','pass','Anna','Sommese','1965-08-06','utente'),('bensca','scala.benedetto@outlook.it','Bengopico57#','Benedetto','Scala','2001-11-10','Amministratore'),('Cookie','cookieforever@gmail.com','Bengopico57#','Simon','Cook','1996-04-24','utente'),('eliminato','eliminato','eliminato','eliminato','eliminato','1996-04-03','Utente'),('gercraftPRO','gerardomitico@gmail.com','mitico','Gerardo','Napolitano','1998-08-06','utente'),('miky01','mitica46@gmail.com','passwordsicura','Michela','Faella','2001-05-06','Utente'),('Pepves','peppe.vessicchio@gmail.com','passwordsicura','Peppe','Vessicchio','1988-01-03','utente'),('rob65','umbero.smaila@gmail.com','passwordsicura','Roberto','Depresso','1989-07-12','utente');
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-04 11:56:23
