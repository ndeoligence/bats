SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `bats_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `bats_db` ;

-- -----------------------------------------------------
-- Table `bats_db`.`accountholdertbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bats_db`.`accountholdertbl` (
  `accountHolderID` VARCHAR(13) NOT NULL ,
  `accountHolderName` VARCHAR(150) NOT NULL ,
  `accountHolderAddress` VARCHAR(255) NULL ,
  `accountHolderContactNumber` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`accountHolderID`) ,
  UNIQUE INDEX `CustomerID_UNIQUE` (`accountHolderID` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bats_db`.`accountHolderCardtbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bats_db`.`accountHolderCardtbl` (
  `cardNo` VARCHAR(16) NOT NULL ,
  `cardActive` TINYINT(1) NOT NULL ,
  `cardPIN` VARCHAR(4) NOT NULL ,
  `accountHolderID` VARCHAR(13) NOT NULL ,
  PRIMARY KEY (`cardNo`) ,
  UNIQUE INDEX `CardNo_UNIQUE` (`cardNo` ASC) ,
  INDEX `fk_Cardtbl_AccountHoldertbl1_idx` (`accountHolderID` ASC) ,
  CONSTRAINT `fk_Cardtbl_AccountHoldertbl1`
    FOREIGN KEY (`accountHolderID` )
    REFERENCES `bats_db`.`accountholdertbl` (`accountHolderID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bats_db`.`employeetbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bats_db`.`employeetbl` (
  `employeeIDNo` VARCHAR(13) NOT NULL ,
  `employeeName` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`employeeIDNo`) ,
  UNIQUE INDEX `EmployeeID_UNIQUE` (`employeeIDNo` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bats_db`.`adminCardtbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bats_db`.`adminCardtbl` (
  `employeeID` VARCHAR(13) NOT NULL ,
  `cardNo` VARCHAR(8) NOT NULL ,
  `pinNo` VARCHAR(4) NOT NULL ,
  `active` TINYINT(1) NOT NULL ,
  INDEX `fk_Admintbl_Employeetbl1_idx` (`employeeID` ASC) ,
  UNIQUE INDEX `AdminCard_UNIQUE` (`cardNo` ASC) ,
  PRIMARY KEY (`cardNo`) ,
  CONSTRAINT `fk_Admintbl_Employeetbl1`
    FOREIGN KEY (`employeeID` )
    REFERENCES `bats_db`.`employeetbl` (`employeeIDNo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bats_db`.`atmtbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bats_db`.`atmtbl` (
  `atm_id` INT NOT NULL AUTO_INCREMENT ,
  `atm_balance` DECIMAL(10,2) NOT NULL DEFAULT 190000.00 ,
  PRIMARY KEY (`atm_id`) ,
  UNIQUE INDEX `ATM_ID_UNIQUE` (`atm_id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bats_db`.`accounts`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bats_db`.`accounts` (
  `accountNo` VARCHAR(11) NOT NULL ,
  `type` VARCHAR(45) NOT NULL ,
  `balance` DECIMAL(10,2) NOT NULL ,
  `maxWithdrawal/day` DECIMAL(10,2) NOT NULL ,
  `maxTransfer/day` DECIMAL(10,2) NOT NULL ,
  `active` TINYINT(1) NOT NULL ,
  `noticeDate` DATE NULL ,
  `noticePending` TINYINT(1) NULL ,
  `accountHolderID` VARCHAR(13) NOT NULL ,
  PRIMARY KEY (`accountNo`) ,
  UNIQUE INDEX `accountNo_UNIQUE` (`accountNo` ASC) ,
  INDEX `fk_accounts_accountholdertbl1_idx` (`accountHolderID` ASC) ,
  CONSTRAINT `fk_accounts_accountholdertbl1`
    FOREIGN KEY (`accountHolderID` )
    REFERENCES `bats_db`.`accountholdertbl` (`accountHolderID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bats_db`.`transactiontbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bats_db`.`transactiontbl` (
  `transactionID` INT NOT NULL AUTO_INCREMENT ,
  `transactionTimeStamp` TIMESTAMP NOT NULL ,
  `transactionAmount` DECIMAL(10,2) NOT NULL ,
  `atmID` INT NOT NULL ,
  `accounts_accountNo` VARCHAR(11) NOT NULL ,
  `type` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`transactionID`) ,
  UNIQUE INDEX `TransactionID_UNIQUE` (`transactionID` ASC) ,
  INDEX `fk_Transactiontbl_ATMtbl1_idx` (`atmID` ASC) ,
  UNIQUE INDEX `ATMtbl_ATM_ID_UNIQUE` (`atmID` ASC) ,
  INDEX `fk_transactiontbl_accounts1_idx` (`accounts_accountNo` ASC) ,
  CONSTRAINT `fk_Transactiontbl_ATMtbl1`
    FOREIGN KEY (`atmID` )
    REFERENCES `bats_db`.`atmtbl` (`atm_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transactiontbl_accounts1`
    FOREIGN KEY (`accounts_accountNo` )
    REFERENCES `bats_db`.`accounts` (`accountNo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `bats_db` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
