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
-- Table `libr`.`GENRES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `libr`.`GENRES` (
  `GENRE_ID` INT NOT NULL AUTO_INCREMENT,
  `GENRE_NAME` VARCHAR(45) NULL,
  PRIMARY KEY (`GENRE_ID`),
  UNIQUE INDEX `GENRE_ID_UNIQUE` (`GENRE_ID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `libr`.`BOOKS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `libr`.`BOOKS` (
  `BOOK_ID` INT NOT NULL AUTO_INCREMENT,
  `BOOK_NAME` VARCHAR(70) NULL,
  `BOOK_INV_NUM` VARCHAR(15) NULL,
  `GENRE_ID` INT NOT NULL,
  PRIMARY KEY (`BOOK_ID`, `GENRE_ID`),
  UNIQUE INDEX `BOOK_ID_UNIQUE` (`BOOK_ID` ASC),
  INDEX `fk_BOOKS_GENRES1_idx` (`GENRE_ID` ASC),
  CONSTRAINT `fk_BOOKS_GENRES1`
    FOREIGN KEY (`GENRE_ID`)
    REFERENCES `libr`.`GENRES` (`GENRE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `libr`.`AUTHORS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `libr`.`AUTHORS` (
  `AUTHOR_ID` INT NOT NULL AUTO_INCREMENT,
  `AUTHOR_NAME` VARCHAR(45) NULL,
  PRIMARY KEY (`AUTHOR_ID`),
  UNIQUE INDEX `AUTHOR_ID_UNIQUE` (`AUTHOR_ID` ASC))
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
  `USER_POS_ID` INT NOT NULL AUTO_INCREMENT,
  `USER_POS_NAME` VARCHAR(20) NULL,
  PRIMARY KEY (`USER_POS_ID`),
  UNIQUE INDEX `USER_POS_ID_UNIQUE` (`USER_POS_ID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `libr`.`USERS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `libr`.`USERS` (
  `USER_ID` INT NOT NULL AUTO_INCREMENT,
  `USER_NAME` VARCHAR(30) NULL,
  `USER_PASSWORD` VARCHAR(60) NULL,
  `USER_POS_ID` INT NOT NULL,
  `USER_LOYALTY` INT NULL,
  `USER_PH_NUM` VARCHAR(12) NULL,
  PRIMARY KEY (`USER_ID`, `USER_POS_ID`),
  INDEX `fk_USERS_USER_POSSITIONS1_idx` (`USER_POS_ID` ASC),
  UNIQUE INDEX `USER_ID_UNIQUE` (`USER_ID` ASC),
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
  `BOOK_STATUS_ID` INT NOT NULL AUTO_INCREMENT,
  `BOOK_STATUS_DESC` VARCHAR(20) NULL,
  PRIMARY KEY (`BOOK_STATUS_ID`),
  UNIQUE INDEX `BOOK_STATUS_UNIQUE` (`BOOK_STATUS_ID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `libr`.`BOOK_ENTITIES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `libr`.`BOOK_ENTITIES` (
  `BOOK_ENTITY_ID` INT NOT NULL AUTO_INCREMENT,
  `BOOK_CONDITION` INT NULL,
  `BOOK_STATUS` INT NOT NULL,
  `BOOK_ID` INT NOT NULL,
  PRIMARY KEY (`BOOK_ENTITY_ID`, `BOOK_STATUS`, `BOOK_ID`),
  INDEX `fk_BOOK_ENITIES_BOOKS_STATUSES1_idx` (`BOOK_STATUS` ASC),
  INDEX `fk_BOOK_ENITIES_BOOKS1_idx` (`BOOK_ID` ASC),
  UNIQUE INDEX `BOOK_ENITITY_ID_UNIQUE` (`BOOK_ENTITY_ID` ASC),
  CONSTRAINT `fk_BOOK_ENITIES_BOOKS_STATUSES1`
    FOREIGN KEY (`BOOK_STATUS`)
    REFERENCES `libr`.`BOOKS_STATUSES` (`BOOK_STATUS_ID`)
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
  `RENT_ID` INT NOT NULL AUTO_INCREMENT,
  `TAKE_DATE` DATE NULL,
  `REALISE_DATE` DATE NULL,
  `BOOK_ENTITY_ID` INT NOT NULL,
  `USER_ID` INT NOT NULL,
  PRIMARY KEY (`RENT_ID`, `BOOK_ENTITY_ID`, `USER_ID`),
  INDEX `fk_RENTS_BOOK_ENITIES1_idx` (`BOOK_ENTITY_ID` ASC),
  INDEX `fk_RENTS_USERS1_idx` (`USER_ID` ASC),
  UNIQUE INDEX `RENT_ID_UNIQUE` (`RENT_ID` ASC),
  CONSTRAINT `fk_RENTS_BOOK_ENITIES1`
    FOREIGN KEY (`BOOK_ENTITY_ID`)
    REFERENCES `libr`.`BOOK_ENTITIES` (`BOOK_ENTITY_ID`)
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
