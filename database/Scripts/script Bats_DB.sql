SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `BATS_DB` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `BATS_DB` ;

-- -----------------------------------------------------
-- Table `BATS_DB`.`Cardtbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BATS_DB`.`Cardtbl` (
  `CardNo` VARCHAR(16) NOT NULL ,
  `Card_CVC` VARCHAR(4) NOT NULL ,
  `Card_ExpireDate` DATETIME NOT NULL ,
  `Card_Active` TINYINT(1) NOT NULL ,
  `Card_PIN` VARCHAR(6) NOT NULL ,
  PRIMARY KEY (`CardNo`) ,
  UNIQUE INDEX `CardNo_UNIQUE` (`CardNo` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BATS_DB`.`AccountHoldertbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BATS_DB`.`AccountHoldertbl` (
  `AccountHolderID` VARCHAR(13) NOT NULL ,
  `AccountHolderName` VARCHAR(150) NOT NULL ,
  `AccountHolderAddress` VARCHAR(255) NULL ,
  `AccountHolderContactNumber` VARCHAR(45) NOT NULL ,
  `Cardtbl_CardNo` VARCHAR(16) NULL ,
  PRIMARY KEY (`AccountHolderID`) ,
  UNIQUE INDEX `CustomerID_UNIQUE` (`AccountHolderID` ASC) ,
  INDEX `fk_AccountHoldertbl_Cardtbl1_idx` (`Cardtbl_CardNo` ASC) ,
  CONSTRAINT `fk_AccountHoldertbl_Cardtbl1`
    FOREIGN KEY (`Cardtbl_CardNo` )
    REFERENCES `BATS_DB`.`Cardtbl` (`CardNo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BATS_DB`.`Accounttbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BATS_DB`.`Accounttbl` (
  `AcountNo` VARCHAR(11) NOT NULL ,
  `Acount_Type` VARCHAR(45) NOT NULL ,
  `Account_Balance` DECIMAL(10,2) NOT NULL ,
  `Account_Min` DECIMAL(10,2) NULL DEFAULT 1000.00 ,
  `Acount_Max/Day` DECIMAL(10,2) NULL DEFAULT 5000.00 ,
  `isActive` TINYINT(1) NOT NULL DEFAULT 0 ,
  `Cardtbl_CardNo` VARCHAR(16) NOT NULL ,
  PRIMARY KEY (`AcountNo`, `Cardtbl_CardNo`) ,
  UNIQUE INDEX `AcountNo_UNIQUE` (`AcountNo` ASC) ,
  INDEX `fk_Accounttbl_Cardtbl1_idx` (`Cardtbl_CardNo` ASC) ,
  CONSTRAINT `fk_Accounttbl_Cardtbl1`
    FOREIGN KEY (`Cardtbl_CardNo` )
    REFERENCES `BATS_DB`.`Cardtbl` (`CardNo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BATS_DB`.`Admintbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BATS_DB`.`Admintbl` (
  `AdminID` INT NULL AUTO_INCREMENT ,
  `AdminCardNo` VARCHAR(8) NOT NULL ,
  PRIMARY KEY (`AdminID`) ,
  UNIQUE INDEX `AdminID_UNIQUE` (`AdminID` ASC) ,
  INDEX `fk_Admintbl_Cardtbl1_idx` (`AdminCardNo` ASC) ,
  UNIQUE INDEX `Cardtbl_CardNo_UNIQUE` (`AdminCardNo` ASC) ,
  CONSTRAINT `fk_Admintbl_Cardtbl1`
    FOREIGN KEY (`AdminCardNo` )
    REFERENCES `BATS_DB`.`Cardtbl` (`CardNo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BATS_DB`.`Employeetbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BATS_DB`.`Employeetbl` (
  `EmployeeID` VARCHAR(13) NOT NULL ,
  `Employee_Name` VARCHAR(45) NOT NULL ,
  `Employee_Address` VARCHAR(255) NULL ,
  `Employee_ContactNo` VARCHAR(45) NOT NULL ,
  `EmployeeAdminID` INT NULL ,
  PRIMARY KEY (`EmployeeID`) ,
  UNIQUE INDEX `EmployeeID_UNIQUE` (`EmployeeID` ASC) ,
  INDEX `fk_Employeetbl_Admintbl1_idx` (`EmployeeAdminID` ASC) ,
  UNIQUE INDEX `Admintbl_AdminID_UNIQUE` (`EmployeeAdminID` ASC) ,
  CONSTRAINT `fk_Employeetbl_Admintbl1`
    FOREIGN KEY (`EmployeeAdminID` )
    REFERENCES `BATS_DB`.`Admintbl` (`AdminID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BATS_DB`.`ATMtbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BATS_DB`.`ATMtbl` (
  `ATM_ID` INT NOT NULL AUTO_INCREMENT ,
  `ATM_Balance` DECIMAL(10,2) NOT NULL DEFAULT 190000.00 ,
  PRIMARY KEY (`ATM_ID`) ,
  UNIQUE INDEX `ATM_ID_UNIQUE` (`ATM_ID` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BATS_DB`.`Transactiontbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BATS_DB`.`Transactiontbl` (
  `TransactionID` INT NOT NULL AUTO_INCREMENT ,
  `Transaction_TimeStamp` DATETIME NOT NULL ,
  `Transaction_Amount` DECIMAL(10,2) NOT NULL ,
  `Transaction_Type` VARCHAR(45) NOT NULL ,
  `ATMID` INT NOT NULL ,
  `AcountNo` VARCHAR(11) NOT NULL ,
  PRIMARY KEY (`TransactionID`) ,
  UNIQUE INDEX `TransactionID_UNIQUE` (`TransactionID` ASC) ,
  INDEX `fk_Transactiontbl_ATMtbl1_idx` (`ATMID` ASC) ,
  UNIQUE INDEX `ATMtbl_ATM_ID_UNIQUE` (`ATMID` ASC) ,
  INDEX `fk_Transactiontbl_Accounttbl1_idx` (`AcountNo` ASC) ,
  CONSTRAINT `fk_Transactiontbl_ATMtbl1`
    FOREIGN KEY (`ATMID` )
    REFERENCES `BATS_DB`.`ATMtbl` (`ATM_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transactiontbl_Accounttbl1`
    FOREIGN KEY (`AcountNo` )
    REFERENCES `BATS_DB`.`Accounttbl` (`AcountNo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `BATS_DB` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
