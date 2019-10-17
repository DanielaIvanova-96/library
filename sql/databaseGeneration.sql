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
-- Table `GENRES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GENRES` (
  `GENRE_ID` INT NOT NULL AUTO_INCREMENT,
  `GENRE_NAME` VARCHAR(45) NULL,
  PRIMARY KEY (`GENRE_ID`),
  UNIQUE INDEX `GENRE_ID_UNIQUE` (`GENRE_ID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BOOKS_INFO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BOOKS_INFO` (
  `BOOK_INFO_ID` INT NOT NULL AUTO_INCREMENT,
  `BOOK_INFO_NAME` VARCHAR(70) NULL,
  `BOOK_INFO_INV_NUM` VARCHAR(15) NULL,
  `GENRE_ID` INT NOT NULL,
  PRIMARY KEY (`BOOK_INFO_ID`, `GENRE_ID`),
  UNIQUE INDEX `BOOK_ID_UNIQUE` (`BOOK_INFO_ID` ASC),
  INDEX `fk_BOOKS_GENRES1_idx` (`GENRE_ID` ASC),
  CONSTRAINT `fk_BOOKS_GENRES1`
    FOREIGN KEY (`GENRE_ID`)
    REFERENCES `GENRES` (`GENRE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AUTHORS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AUTHORS` (
  `AUTHOR_ID` INT NOT NULL AUTO_INCREMENT,
  `AUTHOR_NAME` VARCHAR(45) NULL,
  PRIMARY KEY (`AUTHOR_ID`),
  UNIQUE INDEX `AUTHOR_ID_UNIQUE` (`AUTHOR_ID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AUTHORS_BOOKS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AUTHORS_BOOKS` (
  `AUTHOR_ID` INT NOT NULL,
  `BOOK_NFO_ID` INT NOT NULL,
  PRIMARY KEY (`AUTHOR_ID`, `BOOK_NFO_ID`),
  INDEX `fk_AUTHORS_BOOKS_BOOKS1_idx` (`BOOK_NFO_ID` ASC),
  CONSTRAINT `fk_AUTHORS_BOOKS_AUTHORS1`
    FOREIGN KEY (`AUTHOR_ID`)
    REFERENCES `AUTHORS` (`AUTHOR_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AUTHORS_BOOKS_BOOKS1`
    FOREIGN KEY (`BOOK_NFO_ID`)
    REFERENCES `BOOKS_INFO` (`BOOK_INFO_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `USERS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `USERS` (
  `USER_ID` INT NOT NULL AUTO_INCREMENT,
  `USER_NAME` VARCHAR(30) NULL,
  `USER_PASSWORD` VARCHAR(60) NULL,
  `USER_POSS` INT NULL,
  `USER_LOYALTY` INT NULL,
  `USER_PH_NUM` VARCHAR(12) NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE INDEX `USER_ID_UNIQUE` (`USER_ID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BOOKS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BOOKS` (
  `BOOK_ID` INT NOT NULL AUTO_INCREMENT,
  `BOOK_CONDITION` INT NULL,
  `BOOK_INFO_ID` INT NOT NULL,
  PRIMARY KEY (`BOOK_ID`, `BOOK_INFO_ID`),
  INDEX `fk_BOOK_ENITIES_BOOKS1_idx` (`BOOK_INFO_ID` ASC),
  UNIQUE INDEX `BOOK_ENITITY_ID_UNIQUE` (`BOOK_ID` ASC),
  CONSTRAINT `fk_BOOK_ENITIES_BOOKS1`
    FOREIGN KEY (`BOOK_INFO_ID`)
    REFERENCES `BOOKS_INFO` (`BOOK_INFO_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RENTS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RENTS` (
  `RENT_ID` INT NOT NULL AUTO_INCREMENT,
  `TAKE_DATE` DATE NULL,
  `REALISE_DATE` DATE NULL,
  `BOOK_ID` INT NOT NULL,
  `USER_ID` INT NOT NULL,
  PRIMARY KEY (`RENT_ID`, `BOOK_ID`, `USER_ID`),
  INDEX `fk_RENTS_BOOK_ENITIES1_idx` (`BOOK_ID` ASC),
  INDEX `fk_RENTS_USERS1_idx` (`USER_ID` ASC),
  UNIQUE INDEX `RENT_ID_UNIQUE` (`RENT_ID` ASC),
  CONSTRAINT `fk_RENTS_BOOK_ENITIES1`
    FOREIGN KEY (`BOOK_ID`)
    REFERENCES `BOOKS` (`BOOK_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RENTS_USERS1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `USERS` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
