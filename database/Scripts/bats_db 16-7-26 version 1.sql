SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `BATS_DB` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `BATS_DB` ;

-- -----------------------------------------------------
-- Table `BATS_DB`.`AccountHoldertbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BATS_DB`.`AccountHoldertbl` (
  `AccountHolderID` VARCHAR(13) NOT NULL ,
  `AccountHolderName` VARCHAR(150) NOT NULL ,
  `AccountHolderAddress` VARCHAR(255) NULL ,
  `AccountHolderContactNumber` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`AccountHolderID`) ,
  UNIQUE INDEX `CustomerID_UNIQUE` (`AccountHolderID` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BATS_DB`.`Cardtbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BATS_DB`.`Cardtbl` (
  `CardNo` VARCHAR(16) NOT NULL ,
  `Card_ExpireDate` DATE NOT NULL ,
  `Card_Active` TINYINT(1) NOT NULL ,
  `Card_PIN` VARCHAR(6) NOT NULL ,
  `AccountHolderID` VARCHAR(13) NOT NULL ,
  PRIMARY KEY (`CardNo`) ,
  UNIQUE INDEX `CardNo_UNIQUE` (`CardNo` ASC) ,
  INDEX `fk_Cardtbl_AccountHoldertbl1_idx` (`AccountHolderID` ASC) ,
  CONSTRAINT `fk_Cardtbl_AccountHoldertbl1`
    FOREIGN KEY (`AccountHolderID` )
    REFERENCES `BATS_DB`.`AccountHoldertbl` (`AccountHolderID` )
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
  PRIMARY KEY (`EmployeeID`) ,
  UNIQUE INDEX `EmployeeID_UNIQUE` (`EmployeeID` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BATS_DB`.`Admintbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BATS_DB`.`Admintbl` (
  `AdminID` INT NULL AUTO_INCREMENT ,
  `EmployeeID` VARCHAR(13) NOT NULL ,
  `AdminCard` VARCHAR(8) NOT NULL ,
  PRIMARY KEY (`AdminID`) ,
  UNIQUE INDEX `AdminID_UNIQUE` (`AdminID` ASC) ,
  INDEX `fk_Admintbl_Employeetbl1_idx` (`EmployeeID` ASC) ,
  UNIQUE INDEX `AdminCard_UNIQUE` (`AdminCard` ASC) ,
  CONSTRAINT `fk_Admintbl_Employeetbl1`
    FOREIGN KEY (`EmployeeID` )
    REFERENCES `BATS_DB`.`Employeetbl` (`EmployeeID` )
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
-- Table `BATS_DB`.`CreditCardAccounttbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BATS_DB`.`CreditCardAccounttbl` (
  `CreditCard_AccountNo` VARCHAR(11) NOT NULL ,
  `CVCNo` VARCHAR(4) NOT NULL ,
  `AcountBalance` VARCHAR(45) NOT NULL ,
  `AcountMax/day` DECIMAL(10,2) NOT NULL ,
  `AcountMinwithdrawal` DECIMAL(10,2) NOT NULL ,
  `isActive` TINYINT(1) NOT NULL ,
  `Cardtbl_CardNo` VARCHAR(16) NOT NULL ,
  PRIMARY KEY (`CreditCard_AccountNo`) ,
  UNIQUE INDEX `CVCNo_UNIQUE` (`CVCNo` ASC) ,
  UNIQUE INDEX `AccountNo_UNIQUE` (`CreditCard_AccountNo` ASC) ,
  INDEX `fk_CreditCardAccounttbl_Cardtbl1_idx` (`Cardtbl_CardNo` ASC) ,
  CONSTRAINT `fk_CreditCardAccounttbl_Cardtbl1`
    FOREIGN KEY (`Cardtbl_CardNo` )
    REFERENCES `BATS_DB`.`Cardtbl` (`CardNo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BATS_DB`.`SavingsAccounttbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BATS_DB`.`SavingsAccounttbl` (
  `Savings_AcountNo` VARCHAR(11) NOT NULL ,
  `AccountBalance` DECIMAL(10,2) NOT NULL ,
  `AccountMax/withdrawal` DECIMAL(10,2) NULL ,
  `AccountMin` DECIMAL(10,2) NOT NULL ,
  `isActive` TINYINT(1) NOT NULL ,
  `NoticeDate` DATE NOT NULL ,
  `NoticePending` TINYINT(1) NOT NULL ,
  `Cardtbl_CardNo` VARCHAR(16) NOT NULL ,
  PRIMARY KEY (`Savings_AcountNo`) ,
  UNIQUE INDEX `AcountNo_UNIQUE` (`Savings_AcountNo` ASC) ,
  INDEX `fk_SavingsAccounttbl_Cardtbl1_idx` (`Cardtbl_CardNo` ASC) ,
  CONSTRAINT `fk_SavingsAccounttbl_Cardtbl1`
    FOREIGN KEY (`Cardtbl_CardNo` )
    REFERENCES `BATS_DB`.`Cardtbl` (`CardNo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BATS_DB`.`CurrentAcounttbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BATS_DB`.`CurrentAcounttbl` (
  `CurrentAccountNo` VARCHAR(11) NOT NULL ,
  `AccountBalance` DECIMAL(10,2) NOT NULL ,
  `AccountMaxWithdrawal/day` DECIMAL(10,2) NOT NULL DEFAULT 5000.00 ,
  `AccountMin` DECIMAL(10,2) NOT NULL DEFAULT 100.00 ,
  `Cardtbl_CardNo` VARCHAR(16) NOT NULL ,
  `isActive` TINYINT(1) NULL ,
  `AccountMaxtransfer/day` DECIMAL(10,2) NOT NULL ,
  PRIMARY KEY (`CurrentAccountNo`) ,
  UNIQUE INDEX `CurrentAccountNo_UNIQUE` (`CurrentAccountNo` ASC) ,
  INDEX `fk_CurrentAcounttbl_Cardtbl1_idx` (`Cardtbl_CardNo` ASC) ,
  CONSTRAINT `fk_CurrentAcounttbl_Cardtbl1`
    FOREIGN KEY (`Cardtbl_CardNo` )
    REFERENCES `BATS_DB`.`Cardtbl` (`CardNo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BATS_DB`.`Transactiontbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BATS_DB`.`Transactiontbl` (
  `TransactionID` INT NOT NULL AUTO_INCREMENT ,
  `Transaction_TimeStamp` TIMESTAMP NOT NULL ,
  `Transaction_Amount` DECIMAL(10,2) NOT NULL ,
  `ATMID` INT NOT NULL ,
  `CurrentAccountNo` VARCHAR(11) NULL ,
  `CreditCard_AccountNo` VARCHAR(11) NULL ,
  `Savings_AcountNo` VARCHAR(11) NULL ,
  `CurrentAcounttbl_CurrentAccountNo` VARCHAR(16) NOT NULL ,
  PRIMARY KEY (`TransactionID`) ,
  UNIQUE INDEX `TransactionID_UNIQUE` (`TransactionID` ASC) ,
  INDEX `fk_Transactiontbl_ATMtbl1_idx` (`ATMID` ASC) ,
  UNIQUE INDEX `ATMtbl_ATM_ID_UNIQUE` (`ATMID` ASC) ,
  INDEX `fk_Transactiontbl_CreditCardAccounttbl1_idx` (`CreditCard_AccountNo` ASC) ,
  INDEX `fk_Transactiontbl_SavingsAccounttbl1_idx` (`Savings_AcountNo` ASC) ,
  INDEX `fk_Transactiontbl_CurrentAcounttbl1_idx` (`CurrentAcounttbl_CurrentAccountNo` ASC) ,
  CONSTRAINT `fk_Transactiontbl_ATMtbl1`
    FOREIGN KEY (`ATMID` )
    REFERENCES `BATS_DB`.`ATMtbl` (`ATM_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transactiontbl_CreditCardAccounttbl1`
    FOREIGN KEY (`CreditCard_AccountNo` )
    REFERENCES `BATS_DB`.`CreditCardAccounttbl` (`CreditCard_AccountNo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transactiontbl_SavingsAccounttbl1`
    FOREIGN KEY (`Savings_AcountNo` )
    REFERENCES `BATS_DB`.`SavingsAccounttbl` (`Savings_AcountNo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transactiontbl_CurrentAcounttbl1`
    FOREIGN KEY (`CurrentAcounttbl_CurrentAccountNo` )
    REFERENCES `BATS_DB`.`CurrentAcounttbl` (`CurrentAccountNo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `BATS_DB` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
