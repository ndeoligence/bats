-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bats_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bats_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bats_db` DEFAULT CHARACTER SET utf8 ;
USE `bats_db` ;

-- -----------------------------------------------------
-- Table `bats_db`.`accountholdercardtbl`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bats_db`.`accountholdercardtbl` (
  `CardNo` VARCHAR(16) NOT NULL,
  `PIN` VARCHAR(4) NOT NULL,
  `Active` TINYINT(1) NOT NULL,
  `CVC` VARCHAR(7) NOT NULL,
  `ExpiryDate` DATE NOT NULL,
  `AccountHolderID` VARCHAR(13) NOT NULL,
  PRIMARY KEY (`CardNo`),
  UNIQUE INDEX `CardNo_UNIQUE` (`CardNo` ASC),
  INDEX `fk_Cardtbl_AccountHoldertbl1_idx` (`AccountHolderID` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bats_db`.`accountholdertbl`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bats_db`.`accountholdertbl` (
  `ID` VARCHAR(13) NOT NULL,
  `Name` VARCHAR(150) NOT NULL,
  `Surname` VARCHAR(150) NOT NULL,
  `Address` VARCHAR(255) NULL DEFAULT NULL,
  `ContactNumber` VARCHAR(45) NOT NULL,
  `AccountHolderCardNo` VARCHAR(16) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `CustomerID_UNIQUE` (`ID` ASC),
  INDEX `CardNo_idx` (`AccountHolderCardNo` ASC),
  CONSTRAINT `CardNo`
    FOREIGN KEY (`AccountHolderCardNo`)
    REFERENCES `bats_db`.`accountholdercardtbl` (`CardNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bats_db`.`accounttypetbl`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bats_db`.`accounttypetbl` (
  `TypeNo` INT(11) NOT NULL AUTO_INCREMENT,
  `AccountType` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`TypeNo`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bats_db`.`accounttbl`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bats_db`.`accounttbl` (
  `AccountNo` VARCHAR(11) NOT NULL,
  `Type` INT(2) NOT NULL,
  `Balance` DECIMAL(10,2) NOT NULL,
  `MinBalance` DECIMAL(10,2) NULL DEFAULT '100.00',
  `MaxTransferPerDay` DECIMAL(10,2) NULL DEFAULT '1000.00',
  `MaxWithdrawalPerDay` DECIMAL(10,2) NULL DEFAULT NULL,
  `Active` TINYINT(1) NOT NULL DEFAULT '0',
  `CardNo` VARCHAR(16) NULL DEFAULT NULL,
  `WithdrawalPending` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`AccountNo`),
  UNIQUE INDEX `AcountNo_UNIQUE` (`AccountNo` ASC),
  INDEX `CardNo_idx` (`CardNo` ASC),
  INDEX `AccountType_idx` (`Type` ASC),
  CONSTRAINT `AccountType`
    FOREIGN KEY (`Type`)
    REFERENCES `bats_db`.`accounttypetbl` (`TypeNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bats_db`.`admincardtbl`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bats_db`.`admincardtbl` (
  `CardNo` VARCHAR(8) NOT NULL,
  `Active` TINYINT(1) NOT NULL,
  `PIN` VARCHAR(4) NOT NULL,
  `EmployeeNo` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`CardNo`),
  UNIQUE INDEX `CardNo_UNIQUE` (`CardNo` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bats_db`.`admintbl`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bats_db`.`admintbl` (
  `EmployeeNo` INT(11) NOT NULL AUTO_INCREMENT,
  `AdminCardNo` VARCHAR(8) NULL DEFAULT NULL,
  PRIMARY KEY (`EmployeeNo`),
  UNIQUE INDEX `AdminID_UNIQUE` (`EmployeeNo` ASC),
  INDEX `CardNo_idx` (`AdminCardNo` ASC),
  CONSTRAINT `AdminCardNo`
    FOREIGN KEY (`AdminCardNo`)
    REFERENCES `bats_db`.`admincardtbl` (`CardNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 203
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bats_db`.`atmtbl`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bats_db`.`atmtbl` (
  `ATM_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `Balance` DECIMAL(10,2) NOT NULL DEFAULT '190000.00',
  PRIMARY KEY (`ATM_ID`),
  UNIQUE INDEX `ATM_ID_UNIQUE` (`ATM_ID` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bats_db`.`employeetbl`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bats_db`.`employeetbl` (
  `EmployeeNo` VARCHAR(4) NULL DEFAULT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `ID` VARCHAR(13) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `EmployeeID_UNIQUE` (`ID` ASC),
  UNIQUE INDEX `Admintbl_AdminID_UNIQUE` (`EmployeeNo` ASC),
  INDEX `fk_Employeetbl_Admintbl1_idx` (`EmployeeNo` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bats_db`.`pendingwithdrawalstbl`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bats_db`.`pendingwithdrawalstbl` (
  `AccountNo` VARCHAR(11) NOT NULL,
  `AvailableDate` DATE NOT NULL,
  `Amount` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`AccountNo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bats_db`.`transactiontypetbl`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bats_db`.`transactiontypetbl` (
  `TypeNo` INT(11) NOT NULL AUTO_INCREMENT,
  `TransactionType` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`TypeNo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bats_db`.`transactiontbl`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bats_db`.`transactiontbl` (
  `TransactionID` INT(11) NOT NULL AUTO_INCREMENT,
  `TimeStamp` DATETIME NOT NULL,
  `Amount` DECIMAL(10,2) NOT NULL,
  `Type` INT(11) NOT NULL,
  `ATM_ID` INT(11) NOT NULL,
  `AccountNo` VARCHAR(11) NOT NULL,
  PRIMARY KEY (`TransactionID`),
  UNIQUE INDEX `TransactionID_UNIQUE` (`TransactionID` ASC),
  UNIQUE INDEX `ATMtbl_ATM_ID_UNIQUE` (`ATM_ID` ASC),
  INDEX `fk_Transactiontbl_ATMtbl1_idx` (`ATM_ID` ASC),
  INDEX `fk_Transactiontbl_Accounttbl1_idx` (`AccountNo` ASC),
  INDEX `TransactionType_idx` (`Type` ASC),
  CONSTRAINT `TransactionType`
    FOREIGN KEY (`Type`)
    REFERENCES `bats_db`.`transactiontypetbl` (`TypeNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transactiontbl_ATMtbl1`
    FOREIGN KEY (`ATM_ID`)
    REFERENCES `bats_db`.`atmtbl` (`ATM_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transactiontbl_Accounttbl1`
    FOREIGN KEY (`AccountNo`)
    REFERENCES `bats_db`.`accounttbl` (`AccountNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
