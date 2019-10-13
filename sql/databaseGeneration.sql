-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema libr
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema libr
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `libr` DEFAULT CHARACTER SET utf8 ;
USE `libr` ;

-- -----------------------------------------------------
-- Table `libr`.`BOOKS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `libr`.`BOOKS` (
  `BOOK_ID` INT NOT NULL,
  `BOOK_NAME` VARCHAR(70) NULL,
  `BOOK_INV_NUM` VARCHAR(15) NULL,
  `BOOK_GENRE` VARCHAR(30) NULL,
  PRIMARY KEY (`BOOK_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `libr`.`AUTHORS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `libr`.`AUTHORS` (
  `AUTHOR_ID` INT NOT NULL,
  `AUTHOR_NAME` VARCHAR(45) NULL,
  PRIMARY KEY (`AUTHOR_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `libr`.`AUTHORS_BOOKS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `libr`.`AUTHORS_BOOKS` (
  `AUTHOR_ID` INT NOT NULL,
  `BOOK_ID` INT NOT NULL,
  PRIMARY KEY (`AUTHOR_ID`, `BOOK_ID`),
  INDEX `fk_AUTHORS_BOOKS_BOOKS1_idx` (`BOOK_ID` ASC),
  CONSTRAINT `fk_AUTHORS_BOOKS_AUTHORS1`
    FOREIGN KEY (`AUTHOR_ID`)
    REFERENCES `libr`.`AUTHORS` (`AUTHOR_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AUTHORS_BOOKS_BOOKS1`
    FOREIGN KEY (`BOOK_ID`)
    REFERENCES `libr`.`BOOKS` (`BOOK_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `libr`.`USER_POSITIONS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `libr`.`USER_POSITIONS` (
  `USER_POS_ID` INT NOT NULL,
  `USER_POS_NAME` VARCHAR(20) NULL,
  PRIMARY KEY (`USER_POS_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `libr`.`USERS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `libr`.`USERS` (
  `USER_ID` INT NOT NULL,
  `USER_NAME` VARCHAR(30) NULL,
  `USER_PASSWORD` VARCHAR(60) NULL,
  `USER_POS_ID` INT NOT NULL,
  `USER_LOYALTY` INT NULL,
  `USER_PH_NUM` VARCHAR(12) NULL,
  PRIMARY KEY (`USER_ID`, `USER_POS_ID`),
  INDEX `fk_USERS_USER_POSSITIONS1_idx` (`USER_POS_ID` ASC),
  CONSTRAINT `fk_USERS_USER_POSSITIONS1`
    FOREIGN KEY (`USER_POS_ID`)
    REFERENCES `libr`.`USER_POSITIONS` (`USER_POS_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `libr`.`BOOKS_STATUSES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `libr`.`BOOKS_STATUSES` (
  `BOOK_STATUS` INT NOT NULL,
  `BOOK_STATUS_DESC` VARCHAR(20) NULL,
  PRIMARY KEY (`BOOK_STATUS`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `libr`.`BOOK_ENITIES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `libr`.`BOOK_ENITIES` (
  `BOOK_ENITITY_ID` INT NOT NULL,
  `BOOK_CONDITION` INT NULL,
  `BOOK_STATUS` INT NOT NULL,
  `BOOK_ID` INT NOT NULL,
  PRIMARY KEY (`BOOK_ENITITY_ID`, `BOOK_STATUS`, `BOOK_ID`),
  INDEX `fk_BOOK_ENITIES_BOOKS_STATUSES1_idx` (`BOOK_STATUS` ASC),
  INDEX `fk_BOOK_ENITIES_BOOKS1_idx` (`BOOK_ID` ASC),
  CONSTRAINT `fk_BOOK_ENITIES_BOOKS_STATUSES1`
    FOREIGN KEY (`BOOK_STATUS`)
    REFERENCES `libr`.`BOOKS_STATUSES` (`BOOK_STATUS`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BOOK_ENITIES_BOOKS1`
    FOREIGN KEY (`BOOK_ID`)
    REFERENCES `libr`.`BOOKS` (`BOOK_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `libr`.`RENTS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `libr`.`RENTS` (
  `RENT_ID` INT NOT NULL,
  `TAKE_DATE` DATE NULL,
  `REALISE_DATE` DATE NULL,
  `BOOK_ENITITY_ID` INT NOT NULL,
  `USER_ID` INT NOT NULL,
  PRIMARY KEY (`RENT_ID`, `BOOK_ENITITY_ID`, `USER_ID`),
  INDEX `fk_RENTS_BOOK_ENITIES1_idx` (`BOOK_ENITITY_ID` ASC),
  INDEX `fk_RENTS_USERS1_idx` (`USER_ID` ASC),
  CONSTRAINT `fk_RENTS_BOOK_ENITIES1`
    FOREIGN KEY (`BOOK_ENITITY_ID`)
    REFERENCES `libr`.`BOOK_ENITIES` (`BOOK_ENITITY_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RENTS_USERS1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `libr`.`USERS` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

