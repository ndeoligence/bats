-- MySQL dump 10.13  Distrib 5.6.23, for Win32 (x86)
--
-- Host: localhost    Database: bats_db
-- ------------------------------------------------------
-- Server version	5.6.23-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accountholdercardtbl`
--

DROP TABLE IF EXISTS `accountholdercardtbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accountholdercardtbl` (
  `CardNo` varchar(16) NOT NULL,
  `PIN` varchar(4) NOT NULL,
  `Active` tinyint(1) NOT NULL,
  `CVC` varchar(7) NOT NULL,
  `ExpiryDate` date NOT NULL,
  `AccountHolderID` varchar(13) NOT NULL,
  PRIMARY KEY (`CardNo`),
  UNIQUE KEY `CardNo_UNIQUE` (`CardNo`),
  KEY `fk_Cardtbl_AccountHoldertbl1_idx` (`AccountHolderID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountholdercardtbl`
--

LOCK TABLES `accountholdercardtbl` WRITE;
/*!40000 ALTER TABLE `accountholdercardtbl` DISABLE KEYS */;
INSERT INTO `accountholdercardtbl` VALUES ('2701552190795431','7474',1,'291','2018-03-31','7405010098205'),('2701751600234490','1906',1,'156','2018-06-30','7101025810085');
/*!40000 ALTER TABLE `accountholdercardtbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accountholdertbl`
--

DROP TABLE IF EXISTS `accountholdertbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accountholdertbl` (
  `ID` varchar(13) NOT NULL,
  `Name` varchar(150) NOT NULL,
  `Surname` varchar(150) NOT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `ContactNumber` varchar(45) NOT NULL,
  `AccountHolderCardNo` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CustomerID_UNIQUE` (`ID`),
  KEY `CardNo_idx` (`AccountHolderCardNo`),
  CONSTRAINT `CardNo` FOREIGN KEY (`AccountHolderCardNo`) REFERENCES `accountholdercardtbl` (`CardNo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountholdertbl`
--

LOCK TABLES `accountholdertbl` WRITE;
/*!40000 ALTER TABLE `accountholdertbl` DISABLE KEYS */;
INSERT INTO `accountholdertbl` VALUES ('7101025810085','Hein','Smith','7 Moondance Ave, Cape Town, 1002','0712539380','2701751600234490'),('7405010098205','Anita','Murray','34 Swallows Nest, Bartlett, 1459','0821150281','2701552190795431');
/*!40000 ALTER TABLE `accountholdertbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accounttbl`
--

DROP TABLE IF EXISTS `accounttbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounttbl` (
  `AccountNo` varchar(11) NOT NULL,
  `Type` int(2) NOT NULL,
  `Balance` decimal(10,2) NOT NULL,
  `MinBalance` decimal(10,2) NOT NULL DEFAULT '100.00',
  `MaxTransferPerDay` decimal(10,2) NOT NULL DEFAULT '1000.00',
  `MaxWithdrawalPerDay` decimal(10,2) NOT NULL,
  `Active` tinyint(1) NOT NULL DEFAULT '0',
  `CardNo` varchar(16) NOT NULL,
  `WithdrawalPending` tinyint(1) NOT NULL,
  PRIMARY KEY (`AccountNo`),
  UNIQUE KEY `AcountNo_UNIQUE` (`AccountNo`),
  KEY `CardNo_idx` (`CardNo`),
  KEY `AccountType_idx` (`Type`),
  CONSTRAINT `AccountType` FOREIGN KEY (`Type`) REFERENCES `accounttypetbl` (`TypeNo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 KEY_BLOCK_SIZE=2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounttbl`
--

LOCK TABLES `accounttbl` WRITE;
/*!40000 ALTER TABLE `accounttbl` DISABLE KEYS */;
INSERT INTO `accounttbl` VALUES ('71025219226',1,23010.00,100.00,1000.00,1000.00,1,'2701751600234490',0),('71095311216',1,11100.00,100.00,500.00,800.00,1,'2701552190795431',0),('71096218517',2,8500.00,1000.00,1000.00,1000.00,1,'2701552190795431',0);
/*!40000 ALTER TABLE `accounttbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accounttypetbl`
--

DROP TABLE IF EXISTS `accounttypetbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounttypetbl` (
  `TypeNo` int(11) NOT NULL AUTO_INCREMENT,
  `AccountType` varchar(45) NOT NULL,
  PRIMARY KEY (`TypeNo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounttypetbl`
--

LOCK TABLES `accounttypetbl` WRITE;
/*!40000 ALTER TABLE `accounttypetbl` DISABLE KEYS */;
INSERT INTO `accounttypetbl` VALUES (1,'Current Account'),(2,'Savings Account'),(3,'Credit Card Account');
/*!40000 ALTER TABLE `accounttypetbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admincardtbl`
--

DROP TABLE IF EXISTS `admincardtbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admincardtbl` (
  `CardNo` varchar(8) NOT NULL,
  `Active` tinyint(1) NOT NULL,
  `PIN` varchar(4) NOT NULL,
  `EmployeeNo` varchar(4) NOT NULL,
  PRIMARY KEY (`CardNo`),
  UNIQUE KEY `CardNo_UNIQUE` (`CardNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admincardtbl`
--

LOCK TABLES `admincardtbl` WRITE;
/*!40000 ALTER TABLE `admincardtbl` DISABLE KEYS */;
INSERT INTO `admincardtbl` VALUES ('12101882',1,'2881','0202'),('12701569',1,'9651','0201');
/*!40000 ALTER TABLE `admincardtbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admintbl`
--

DROP TABLE IF EXISTS `admintbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admintbl` (
  `EmployeeNo` int(11) NOT NULL AUTO_INCREMENT,
  `AdminCardNo` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`EmployeeNo`),
  UNIQUE KEY `AdminID_UNIQUE` (`EmployeeNo`),
  KEY `CardNo_idx` (`AdminCardNo`),
  CONSTRAINT `AdminCardNo` FOREIGN KEY (`AdminCardNo`) REFERENCES `admincardtbl` (`CardNo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admintbl`
--

LOCK TABLES `admintbl` WRITE;
/*!40000 ALTER TABLE `admintbl` DISABLE KEYS */;
INSERT INTO `admintbl` VALUES (202,'12101882'),(201,'12701569');
/*!40000 ALTER TABLE `admintbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `atmtbl`
--

DROP TABLE IF EXISTS `atmtbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `atmtbl` (
  `ATM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Balance` decimal(10,2) NOT NULL DEFAULT '190000.00',
  PRIMARY KEY (`ATM_ID`),
  UNIQUE KEY `ATM_ID_UNIQUE` (`ATM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atmtbl`
--

LOCK TABLES `atmtbl` WRITE;
/*!40000 ALTER TABLE `atmtbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `atmtbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employeetbl`
--

DROP TABLE IF EXISTS `employeetbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employeetbl` (
  `EmployeeNo` varchar(4) DEFAULT NULL,
  `Name` varchar(45) NOT NULL,
  `ID` varchar(13) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EmployeeID_UNIQUE` (`ID`),
  UNIQUE KEY `Admintbl_AdminID_UNIQUE` (`EmployeeNo`),
  KEY `fk_Employeetbl_Admintbl1_idx` (`EmployeeNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employeetbl`
--

LOCK TABLES `employeetbl` WRITE;
/*!40000 ALTER TABLE `employeetbl` DISABLE KEYS */;
INSERT INTO `employeetbl` VALUES ('202','Sello Makanyane','6205030902085'),('201','Karin Clifford','8012090075083');
/*!40000 ALTER TABLE `employeetbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pendingwithdrawalstbl`
--

DROP TABLE IF EXISTS `pendingwithdrawalstbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pendingwithdrawalstbl` (
  `AccountNo` varchar(11) NOT NULL,
  `AvailableDate` date NOT NULL,
  `Amount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`AccountNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pendingwithdrawalstbl`
--

LOCK TABLES `pendingwithdrawalstbl` WRITE;
/*!40000 ALTER TABLE `pendingwithdrawalstbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `pendingwithdrawalstbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactiontbl`
--

DROP TABLE IF EXISTS `transactiontbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactiontbl` (
  `TransactionID` int(11) NOT NULL AUTO_INCREMENT,
  `TimeStamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Amount` decimal(10,2) NOT NULL,
  `Type` int(11) NOT NULL,
  `ATM_ID` int(11) NOT NULL,
  `AccountNo` varchar(11) NOT NULL,
  PRIMARY KEY (`TransactionID`),
  UNIQUE KEY `TransactionID_UNIQUE` (`TransactionID`),
  KEY `fk_Transactiontbl_ATMtbl1_idx` (`ATM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactiontbl`
--

LOCK TABLES `transactiontbl` WRITE;
/*!40000 ALTER TABLE `transactiontbl` DISABLE KEYS */;
INSERT INTO `transactiontbl` VALUES (2,'2016-08-04 12:05:45',200.00,1,13701,'71095311216'),(3,'2016-08-04 12:54:28',200.00,1,13701,'71095311216'),(4,'2016-08-04 12:59:30',200.00,1,13701,'71095311216'),(5,'2016-08-04 13:01:10',200.00,1,13701,'71095311216'),(6,'2016-08-04 13:05:51',100.00,1,13701,'71095311216'),(7,'2016-08-04 13:10:25',300.00,1,13701,'71095311216'),(8,'2016-08-04 13:15:23',200.00,1,13701,'71095311216'),(9,'2016-08-04 13:28:32',300.00,1,13701,'71095311216'),(10,'2016-08-04 13:43:55',1000.00,1,13701,'71095311216'),(11,'2016-08-04 13:48:14',100.00,1,13701,'71095311216');
/*!40000 ALTER TABLE `transactiontbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactiontypetbl`
--

DROP TABLE IF EXISTS `transactiontypetbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactiontypetbl` (
  `TypeNo` int(11) NOT NULL AUTO_INCREMENT,
  `TransactionType` varchar(45) NOT NULL,
  PRIMARY KEY (`TypeNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactiontypetbl`
--

LOCK TABLES `transactiontypetbl` WRITE;
/*!40000 ALTER TABLE `transactiontypetbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `transactiontypetbl` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-05 12:43:14
