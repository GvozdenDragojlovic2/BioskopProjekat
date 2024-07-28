/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.18-MariaDB : Database - database
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bioskop` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `bioskop`;



DROP TABLE IF EXISTS `Administrator`;

CREATE TABLE `Administrator` (
  `AdministratorID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(30) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `Username` VARCHAR(30) NOT NULL,
  `Password` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`AdministratorID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Administrator` VALUES 
(1,'Gvozden','Dragojlovic','gvozden','gvozden'),
(2,'Jovan','Galovic','jovan','jovan');


DROP TABLE IF EXISTS `Sala`;

CREATE TABLE `Sala` (
  `SalaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`SalaID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Sala` VALUES 
(1,'Batman'),
(2,'Joker'),
(3,'Harley Quinn');



DROP TABLE IF EXISTS `Gledalac`;

CREATE TABLE `Gledalac` (
  `GledalacID` BIGINT(10) UNSIGNED NOT NULL,
  `Ime` VARCHAR(30) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `Email` VARCHAR(50) NOT NULL,
  `Telefon` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`GledalacID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Gledalac` VALUES 
(1,'Dimitrije','Obradovic','dimitrije@gmail.com', '0623271829'),
(2,'Aleksa','Grzincic','aleksa@gmail.com', '0632271283'),
(3,'Kosta','Divnic', 'kosta@gmail.com', '0632312344');


DROP TABLE IF EXISTS `Glumac`;

CREATE TABLE `Glumac` (
  `GlumacID` BIGINT(10) UNSIGNED NOT NULL,
  `Ime` VARCHAR(30) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `DatumRodjenja` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`GlumacID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Glumac` VALUES 
(1,'Al','Pacino','1940-04-25'),
(2,'Marlon','Brando','1924-04-03'),
(3,'Javier','Bardem','1969-03-01'),
(4,'Woody','Harrelson','1961-07-23'),
(5,'Elijah','Wood','1981-01-28'),
(6,'Viggo','Mortensen','1958-10-20');


DROP TABLE IF EXISTS `Film`;

CREATE TABLE `Film` (
  `FilmID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(50) NOT NULL,
  `Zanr` VARCHAR(70) NOT NULL,
  `Opis` VARCHAR(200) NOT NULL,
  `Reziser` VARCHAR(70) NOT NULL,
  PRIMARY KEY (`FilmID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Film` VALUES 
(1,'The Godfather', 'Krimi, drama', 'Brutalan film.', 'Francis Ford Coppola'),
(2,'No country for old men', 'Krimi, triler', 'Opasan film.', 'Ethan Coen, Joel Coen'),
(3,'Lord of the Rings: The Return of the King', 'Akcija, avantura', 'Jedan od najboljih.', 'Peter Jackson');


DROP TABLE IF EXISTS `Uloga`;

CREATE TABLE `Uloga` (
  `FilmID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Rb` INT(7) NOT NULL,
  `Naziv` VARCHAR(70) NOT NULL,
  `Opis` VARCHAR(200) NOT NULL,
  `GlumacID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`FilmID`,`Rb`),
  CONSTRAINT `fk_film_id` FOREIGN KEY (`FilmID`) REFERENCES `Film` (`FilmID`) ON DELETE CASCADE,
  CONSTRAINT `fk_glumac_id` FOREIGN KEY (`GlumacID`) REFERENCES `Glumac` (`GlumacID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


INSERT  INTO `Uloga` VALUES 
(1,1,'Michael Corleone', 'Sin Vita Korleonea.',1),
(1,2,'Don Vito Corleone', 'Don.',2),
(2,1,'Anton Chigurh', 'Opasan psihopata.',3),
(2,2,'Carson Wells', 'Lovac na glave.',4),
(3,1,'Frodo Baggins', 'Mali hobit.',5),
(3,2,'Aragorn II', 'Kralj.',6);


DROP TABLE IF EXISTS `Termin`;

CREATE TABLE `Termin` (
  `TerminID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `DatumVreme` DATETIME NOT NULL,
  `CenaKarte` DECIMAL(10,2) NOT NULL,
  `Projekcija` VARCHAR(5),
  `FilmID` BIGINT(10) UNSIGNED NOT NULL,
  `SalaID` BIGINT(10) UNSIGNED NOT NULL,
  `AdministratorID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`TerminID`),
  CONSTRAINT `fk_film2_id` FOREIGN KEY (`FilmID`) REFERENCES `Film` (`FilmID`),
  CONSTRAINT `fk_sala_id` FOREIGN KEY (`SalaID`) REFERENCES `Sala` (`SalaID`),
  CONSTRAINT `fk_admin_id` FOREIGN KEY (`AdministratorID`) REFERENCES `Administrator` (`AdministratorID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



INSERT  INTO `Termin` VALUES 
(1,'2024-04-10 20:00:00',800,'2D',1,1,1);



DROP TABLE IF EXISTS `Karta`;

CREATE TABLE `Karta` (
  `KartaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `TerminID` BIGINT(10) UNSIGNED NOT NULL,
  `GledalacID` BIGINT(10) UNSIGNED NOT NULL,
  `Sediste` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`KartaID`,`TerminID`,`GledalacID`),
  CONSTRAINT `fk_termin_id` FOREIGN KEY (`TerminID`) REFERENCES `Termin` (`TerminID`) ON DELETE CASCADE,
  CONSTRAINT `fk_gledalac_id` FOREIGN KEY (`GledalacID`) REFERENCES `Gledalac` (`GledalacID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



INSERT  INTO `Karta` VALUES 
(1,1,1,'A1'),
(2,1,2,'A2'),
(3,1,3,'A3');




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
