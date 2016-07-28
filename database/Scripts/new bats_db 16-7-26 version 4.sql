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
  `cardExpireDate` DATE NOT NULL ,
  `cardActive` TINYINT(1) NOT NULL ,
  `cardPIN` VARCHAR(6) NOT NULL ,
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
  `employeeID` VARCHAR(13) NOT NULL ,
  `employeeName` VARCHAR(45) NOT NULL ,
  `employeeAddress` VARCHAR(255) NULL ,
  `employeeContactNo` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`employeeID`) ,
  UNIQUE INDEX `EmployeeID_UNIQUE` (`employeeID` ASC) )
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
    REFERENCES `bats_db`.`employeetbl` (`employeeID` )
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
-- Table `bats_db`.`creditcardaccounttbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bats_db`.`creditcardaccounttbl` (
  `accountNo` VARCHAR(11) NOT NULL ,
  `acountBalance` VARCHAR(45) NOT NULL ,
  `acountMax/day` DECIMAL(10,2) NOT NULL ,
  `acountMinwithdrawal` DECIMAL(10,2) NOT NULL ,
  `active` TINYINT(1) NOT NULL ,
  `cardNo` VARCHAR(16) NOT NULL ,
  PRIMARY KEY (`accountNo`) ,
  UNIQUE INDEX `AccountNo_UNIQUE` (`accountNo` ASC) ,
  INDEX `fk_CreditCardAccounttbl_Cardtbl1_idx` (`cardNo` ASC) ,
  CONSTRAINT `fk_CreditCardAccounttbl_Cardtbl1`
    FOREIGN KEY (`cardNo` )
    REFERENCES `bats_db`.`accountHolderCardtbl` (`cardNo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bats_db`.`savingsaccounttbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bats_db`.`savingsaccounttbl` (
  `acountNo` VARCHAR(11) NOT NULL ,
  `accountBalance` DECIMAL(10,2) NOT NULL ,
  `accountMax/withdrawal` DECIMAL(10,2) NULL DEFAULT 5000.00 ,
  `accountMin` DECIMAL(10,2) NOT NULL DEFAULT 1000.00 ,
  `active` TINYINT(1) NOT NULL ,
  `noticeDate` DATE NOT NULL ,
  `noticePending` TINYINT(1) NOT NULL ,
  `cardNo` VARCHAR(16) NOT NULL ,
  PRIMARY KEY (`acountNo`) ,
  UNIQUE INDEX `AcountNo_UNIQUE` (`acountNo` ASC) ,
  INDEX `fk_SavingsAccounttbl_Cardtbl1_idx` (`cardNo` ASC) ,
  CONSTRAINT `fk_SavingsAccounttbl_Cardtbl1`
    FOREIGN KEY (`cardNo` )
    REFERENCES `bats_db`.`accountHolderCardtbl` (`cardNo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bats_db`.`currentaccounttbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bats_db`.`currentaccounttbl` (
  `accountNo` VARCHAR(11) NOT NULL ,
  `accountBalance` DECIMAL(10,2) NOT NULL ,
  `accountMaxWithdrawal/day` DECIMAL(10,2) NOT NULL DEFAULT 5000.00 ,
  `accountMin` DECIMAL(10,2) NOT NULL DEFAULT 100.00 ,
  `active` TINYINT(1) NULL ,
  `accountMaxTransfer/day` DECIMAL(10,2) NOT NULL ,
  `accountHolderCardNo` VARCHAR(16) NOT NULL ,
  PRIMARY KEY (`accountNo`) ,
  UNIQUE INDEX `CurrentAccountNo_UNIQUE` (`accountNo` ASC) ,
  INDEX `fk_currentaccounttbl_accountHolderCardtbl1_idx` (`accountHolderCardNo` ASC) ,
  CONSTRAINT `fk_currentaccounttbl_accountHolderCardtbl1`
    FOREIGN KEY (`accountHolderCardNo` )
    REFERENCES `bats_db`.`accountHolderCardtbl` (`cardNo` )
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
  `creditCardAccountNo` VARCHAR(11) NULL ,
  `savingsAcountNo` VARCHAR(11) NULL ,
  `currentAccountNo` VARCHAR(11) NULL ,
  PRIMARY KEY (`transactionID`) ,
  UNIQUE INDEX `TransactionID_UNIQUE` (`transactionID` ASC) ,
  INDEX `fk_Transactiontbl_ATMtbl1_idx` (`atmID` ASC) ,
  UNIQUE INDEX `ATMtbl_ATM_ID_UNIQUE` (`atmID` ASC) ,
  INDEX `fk_Transactiontbl_CreditCardAccounttbl1_idx` (`creditCardAccountNo` ASC) ,
  INDEX `fk_Transactiontbl_SavingsAccounttbl1_idx` (`savingsAcountNo` ASC) ,
  INDEX `fk_Transactiontbl_CurrentAcounttbl1_idx` (`currentAccountNo` ASC) ,
  CONSTRAINT `fk_Transactiontbl_ATMtbl1`
    FOREIGN KEY (`atmID` )
    REFERENCES `bats_db`.`atmtbl` (`atm_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transactiontbl_CreditCardAccounttbl1`
    FOREIGN KEY (`creditCardAccountNo` )
    REFERENCES `bats_db`.`creditcardaccounttbl` (`accountNo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transactiontbl_SavingsAccounttbl1`
    FOREIGN KEY (`savingsAcountNo` )
    REFERENCES `bats_db`.`savingsaccounttbl` (`acountNo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transactiontbl_CurrentAcounttbl1`
    FOREIGN KEY (`currentAccountNo` )
    REFERENCES `bats_db`.`currentaccounttbl` (`accountNo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `bats_db` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
