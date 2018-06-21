-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Klant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Klant` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `voornaam` VARCHAR(45) NULL,
  `tussenvoegsel` VARCHAR(45) NULL,
  `achternaam` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idKlant_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `rol` ENUM("klant", "medewerker", "beheerder") NULL,
  `Klant_idKlant` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idAccount_UNIQUE` (`id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_Account_Klant1_idx` (`Klant_idKlant` ASC),
  CONSTRAINT `fk_Account_Klant1`
    FOREIGN KEY (`Klant_idKlant`)
    REFERENCES `mydb`.`Klant` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Adres`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Adres` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `straatnaam` VARCHAR(45) NULL,
  `huisnummer` VARCHAR(45) NULL,
  `toevoeging` VARCHAR(45) NULL,
  `postcode` VARCHAR(45) NULL,
  `woonplaats` VARCHAR(45) NULL,
  `Adrestype` ENUM("postadres", "factuuradres", "bezorgadres") NULL,
  `Klant_idKlant` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idAdres_UNIQUE` (`id` ASC),
  INDEX `fk_Adres_Klant1_idx` (`Klant_idKlant` ASC),
  CONSTRAINT `fk_Adres_Klant1`
    FOREIGN KEY (`Klant_idKlant`)
    REFERENCES `mydb`.`Klant` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Artikel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Artikel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `naam` VARCHAR(45) NULL,
  `prijs` DECIMAL(6,2) NULL,
  `voorraad` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idArtikel_UNIQUE` (`id` ASC),
  UNIQUE INDEX `naam_UNIQUE` (`naam` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Bestelling`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Bestelling` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `totaalprijs` DECIMAL(6,2) NULL,
  `Klant_idKlant` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idBestelling_UNIQUE` (`id` ASC),
  INDEX `fk_Bestelling_Klant1_idx` (`Klant_idKlant` ASC),
  CONSTRAINT `fk_Bestelling_Klant1`
    FOREIGN KEY (`Klant_idKlant`)
    REFERENCES `mydb`.`Klant` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Bestelregel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Bestelregel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `aantal` INT NULL,
  `prijs` DECIMAL(6,2) NULL,
  `Bestelling_idBestelling` INT NOT NULL,
  `Artikel_idArtikel` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idBestelregel_UNIQUE` (`id` ASC),
  INDEX `fk_Bestelregel_Bestelling1_idx` (`Bestelling_idBestelling` ASC),
  INDEX `fk_Bestelregel_Artikel1_idx` (`Artikel_idArtikel` ASC),
  CONSTRAINT `fk_Bestelregel_Bestelling1`
    FOREIGN KEY (`Bestelling_idBestelling`)
    REFERENCES `mydb`.`Bestelling` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bestelregel_Artikel1`
    FOREIGN KEY (`Artikel_idArtikel`)
    REFERENCES `mydb`.`Artikel` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
