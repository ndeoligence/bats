-- MySQL dump 10.13  Distrib 5.6.30, for Linux (x86_64)
--
-- Host: localhost    Database: bats_db
-- ------------------------------------------------------
-- Server version	5.6.30

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
-- Table structure for table `accountHolderCards`
--

DROP TABLE IF EXISTS `accountHolderCards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accountHolderCards` (
  `CardNo` varchar(16) NOT NULL,
  `PIN` varchar(4) NOT NULL,
  `Active` tinyint(1) NOT NULL,
  `AccountHolderID` varchar(13) NOT NULL,
  PRIMARY KEY (`CardNo`),
  UNIQUE KEY `CardNo_UNIQUE` (`CardNo`),
  KEY `fk_Cardtbl_AccountHoldertbl1_idx` (`AccountHolderID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountHolderCards`
--

LOCK TABLES `accountHolderCards` WRITE;
/*!40000 ALTER TABLE `accountHolderCards` DISABLE KEYS */;
INSERT INTO `accountHolderCards` VALUES ('1602147483647','1234',1,'8011025206081'),('1605850177078193','7474',1,'7405010098205');
/*!40000 ALTER TABLE `accountHolderCards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accountHolders`
--

DROP TABLE IF EXISTS `accountHolders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accountHolders` (
  `ID` varchar(13) NOT NULL,
  `Name` varchar(150) NOT NULL,
  `Surname` varchar(150) NOT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `ContactNumber` varchar(45) NOT NULL,
  `AccountHolderCardNo` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CustomerID_UNIQUE` (`ID`),
  KEY `CardNo_idx` (`AccountHolderCardNo`),
  CONSTRAINT `CardNo` FOREIGN KEY (`AccountHolderCardNo`) REFERENCES `accountHolderCards` (`CardNo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountHolders`
--

LOCK TABLES `accountHolders` WRITE;
/*!40000 ALTER TABLE `accountHolders` DISABLE KEYS */;
INSERT INTO `accountHolders` VALUES ('1996325498546','Elias','Silas','Bouvalaad Matilda','011 233 3322',NULL),('4309035395876','Cher','NotCommercial','Malibu, California, USA','098 543 4321',NULL),('5609035285983','Barbara','Striesand','HollyWood','011 434 5555',NULL),('6009045489509','Cat','Stevens','Paris France','011 222 3456',NULL),('6605037495983','Dirk','Uys','Barario Randburg','021 342 5555',NULL),('6809034520898','Shania','Twain','Vankoover, Canada','011 222 3333',NULL),('6809034569083','Missy','Eliot','Jacob Terrace, 11 Henny Str','072  333 5467',NULL),('7101025810085','Hein ','Smith','7 Moondance Ave, Cape Town, 1002','071 253 9380',NULL),('7405010098205','Anita','Murray','34 Swallows Nest, Bartlett, 1459','082 115 0281','1605850177078193'),('7702025206087','Shakira','Mebarak','Barranquila, Colombia, South America','011 667 4321',NULL),('8011025206081','Alannis','Morisset','12 Billy Drive','011 867 2341','1602147483647'),('8704235206082','Gill','Heward','8 Min Str, Styn City, Pretoria, 8845','074 552 1987',NULL),('9708048659398','Michael','Smith','Malibu, California','011 345 4567',NULL);
/*!40000 ALTER TABLE `accountHolders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accountTypes`
--

DROP TABLE IF EXISTS `accountTypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accountTypes` (
  `TypeNo` int(11) NOT NULL AUTO_INCREMENT,
  `AccountType` varchar(45) NOT NULL,
  PRIMARY KEY (`TypeNo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountTypes`
--

LOCK TABLES `accountTypes` WRITE;
/*!40000 ALTER TABLE `accountTypes` DISABLE KEYS */;
INSERT INTO `accountTypes` VALUES (1,'Current'),(2,'Savings'),(3,'CreditCard');
/*!40000 ALTER TABLE `accountTypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `AccountNo` varchar(11) NOT NULL,
  `Type` int(2) NOT NULL,
  `Balance` decimal(10,2) NOT NULL,
  `MinBalance` decimal(10,2) DEFAULT '100.00',
  `MaxTransferPerDay` decimal(10,2) DEFAULT '1000.00',
  `MaxWithdrawalPerDay` decimal(10,2) DEFAULT NULL,
  `Active` tinyint(1) NOT NULL DEFAULT '0',
  `CardNo` varchar(16) DEFAULT NULL,
  `WithdrawalPending` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`AccountNo`),
  UNIQUE KEY `AcountNo_UNIQUE` (`AccountNo`),
  KEY `CardNo_idx` (`CardNo`),
  KEY `AccountType_idx` (`Type`),
  CONSTRAINT `AccountType` FOREIGN KEY (`Type`) REFERENCES `accountTypes` (`TypeNo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES ('27277104814',1,92.65,100.00,1000.00,1000.00,1,'1605850177078193',0);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adminCards`
--

DROP TABLE IF EXISTS `adminCards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adminCards` (
  `CardNo` varchar(8) NOT NULL,
  `Active` tinyint(1) NOT NULL,
  `PIN` varchar(4) NOT NULL,
  `EmployeeNo` varchar(4) NOT NULL,
  PRIMARY KEY (`CardNo`),
  UNIQUE KEY `CardNo_UNIQUE` (`CardNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adminCards`
--

LOCK TABLES `adminCards` WRITE;
/*!40000 ALTER TABLE `adminCards` DISABLE KEYS */;
/*!40000 ALTER TABLE `adminCards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admins` (
  `EmployeeNo` int(11) NOT NULL AUTO_INCREMENT,
  `AdminCardNo` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`EmployeeNo`),
  UNIQUE KEY `AdminID_UNIQUE` (`EmployeeNo`),
  KEY `CardNo_idx` (`AdminCardNo`),
  CONSTRAINT `AdminCardNo` FOREIGN KEY (`AdminCardNo`) REFERENCES `adminCards` (`CardNo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `atms`
--

DROP TABLE IF EXISTS `atms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `atms` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(128) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ATM_ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atms`
--

LOCK TABLES `atms` WRITE;
/*!40000 ALTER TABLE `atms` DISABLE KEYS */;
INSERT INTO `atms` VALUES (1,'Ilana\'s PC'),(2,'James\' PC'),(3,'Michael\'s PC');
/*!40000 ALTER TABLE `atms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employees` (
  `EmployeeNo` varchar(4) DEFAULT NULL,
  `Name` varchar(45) NOT NULL,
  `ID` varchar(13) NOT NULL,
  `Surname` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EmployeeID_UNIQUE` (`ID`),
  UNIQUE KEY `Admintbl_AdminID_UNIQUE` (`EmployeeNo`),
  KEY `fk_Employeetbl_Admintbl1_idx` (`EmployeeNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES ('1010','Ilana','1234567890110','Uys'),('1020','James','1234567890120','Jacobs');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pendigSavingsWithdrawals`
--

DROP TABLE IF EXISTS `pendigSavingsWithdrawals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pendigSavingsWithdrawals` (
  `AccountNo` varchar(11) NOT NULL,
  `AvailableDate` date NOT NULL,
  `Amount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`AccountNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pendigSavingsWithdrawals`
--

LOCK TABLES `pendigSavingsWithdrawals` WRITE;
/*!40000 ALTER TABLE `pendigSavingsWithdrawals` DISABLE KEYS */;
/*!40000 ALTER TABLE `pendigSavingsWithdrawals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactionCharges`
--

DROP TABLE IF EXISTS `transactionCharges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactionCharges` (
  `TypeId` int(11) NOT NULL,
  `InitialCharge` decimal(10,2) NOT NULL,
  `PerUnitCharge` decimal(10,2) NOT NULL,
  `UnitAmount` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactionCharges`
--

LOCK TABLES `transactionCharges` WRITE;
/*!40000 ALTER TABLE `transactionCharges` DISABLE KEYS */;
INSERT INTO `transactionCharges` VALUES (1,0.00,1.05,100.00),(2,0.00,1.05,100.00),(3,0.00,1.05,100.00);
/*!40000 ALTER TABLE `transactionCharges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactionTypes`
--

DROP TABLE IF EXISTS `transactionTypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactionTypes` (
  `TypeNo` int(11) NOT NULL AUTO_INCREMENT,
  `TransactionType` varchar(45) NOT NULL,
  PRIMARY KEY (`TypeNo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactionTypes`
--

LOCK TABLES `transactionTypes` WRITE;
/*!40000 ALTER TABLE `transactionTypes` DISABLE KEYS */;
INSERT INTO `transactionTypes` VALUES (1,'Deposit'),(2,'Transfer'),(3,'Withdrawal'),(4,'WithdrawalNotice'),(5,'BankCharges');
/*!40000 ALTER TABLE `transactionTypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions` (
  `TransactionID` int(11) NOT NULL AUTO_INCREMENT,
  `TimeStamp` datetime NOT NULL,
  `Amount` decimal(10,2) NOT NULL,
  `Type` int(11) NOT NULL,
  `ATM_ID` int(11) NOT NULL,
  `SecondaryAccountNo` varchar(11) DEFAULT NULL,
  `PrimaryAccountNo` varchar(11) NOT NULL,
  PRIMARY KEY (`TransactionID`),
  UNIQUE KEY `TransactionID_UNIQUE` (`TransactionID`),
  KEY `fk_Transactiontbl_ATMtbl1_idx` (`ATM_ID`),
  KEY `fk_Transactiontbl_Accounttbl1_idx` (`SecondaryAccountNo`),
  KEY `TransactionType_idx` (`Type`),
  CONSTRAINT `TransactionType` FOREIGN KEY (`Type`) REFERENCES `transactionTypes` (`TypeNo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transactiontbl_ATMtbl1` FOREIGN KEY (`ATM_ID`) REFERENCES `atms` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transactiontbl_Accounttbl1` FOREIGN KEY (`SecondaryAccountNo`) REFERENCES `accounts` (`AccountNo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (1,'2016-08-09 22:50:23',100.00,3,1,NULL,'21410364'),(6,'2016-08-10 00:00:00',400.00,1,1,NULL,'27277104814');
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-10 15:59:58
