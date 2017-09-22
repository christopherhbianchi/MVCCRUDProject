-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `movie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie` ;

CREATE TABLE IF NOT EXISTS `movie` (
  `id` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `genre` VARCHAR(45) NULL,
  `year_released` VARCHAR(45) NULL,
  `leading_actor` VARCHAR(45) NULL,
  `movie_poster_url` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `genre` ;

CREATE TABLE IF NOT EXISTS `genre` (
  `genre_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`genre_name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie_genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie_genre` ;

CREATE TABLE IF NOT EXISTS `movie_genre` (
  `movie_id` INT NOT NULL,
  `genre_name` VARCHAR(45) NOT NULL,
  INDEX `fk_movie_genre_movie_idx` (`movie_id` ASC),
  INDEX `fk_movie_genre_genre_idx` (`genre_name` ASC),
  PRIMARY KEY (`movie_id`, `genre_name`),
  CONSTRAINT `fk_movie_genre_movie`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_movie_genre_genre`
    FOREIGN KEY (`genre_name`)
    REFERENCES `genre` (`genre_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `actor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `actor` ;

CREATE TABLE IF NOT EXISTS `actor` (
  `id` INT NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acted_in`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acted_in` ;

CREATE TABLE IF NOT EXISTS `acted_in` (
  `movie_id` INT NOT NULL,
  `actor_id` INT NOT NULL,
  PRIMARY KEY (`movie_id`, `actor_id`),
  INDEX `fk_acted_in_movie_idx` (`movie_id` ASC),
  CONSTRAINT `fk_acted_in_movie`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_acted_in_actor`
    FOREIGN KEY (`actor_id`)
    REFERENCES `actor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `years`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `years` ;

CREATE TABLE IF NOT EXISTS `years` (
  `year` INT NOT NULL,
  PRIMARY KEY (`year`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `released_in`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `released_in` ;

CREATE TABLE IF NOT EXISTS `released_in` (
  `movie_id` INT NOT NULL,
  `year` INT NOT NULL,
  PRIMARY KEY (`movie_id`, `year`),
  INDEX `fk_released_in_years_idx` (`year` ASC),
  CONSTRAINT `fk_released_in_years`
    FOREIGN KEY (`year`)
    REFERENCES `years` (`year`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_released_in_movie`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO movieapp@localhost;
 DROP USER movieapp@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'movieapp'@'localhost' IDENTIFIED BY 'movieapp';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'movieapp'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `movie`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `movie` (`id`, `title`, `genre`, `year_released`, `leading_actor`, `movie_poster_url`) VALUES (1, 'Shutter Island', 'Thriller', '2010', NULL, NULL);
INSERT INTO `movie` (`id`, `title`, `genre`, `year_released`, `leading_actor`, `movie_poster_url`) VALUES (2, 'Inception', 'Thriller', '2010', NULL, NULL);
INSERT INTO `movie` (`id`, `title`, `genre`, `year_released`, `leading_actor`, `movie_poster_url`) VALUES (3, 'The Imitation Game', 'Drama', '2014', NULL, NULL);
INSERT INTO `movie` (`id`, `title`, `genre`, `year_released`, `leading_actor`, `movie_poster_url`) VALUES (4, 'Mission Impossible III', 'Action', '2006', NULL, NULL);
INSERT INTO `movie` (`id`, `title`, `genre`, `year_released`, `leading_actor`, `movie_poster_url`) VALUES (5, 'Indiana Jones and the Last Crusade', 'Action', '1989', NULL, NULL);
INSERT INTO `movie` (`id`, `title`, `genre`, `year_released`, `leading_actor`, `movie_poster_url`) VALUES (6, 'Valkyrie', 'Action', '2008', NULL, NULL);
INSERT INTO `movie` (`id`, `title`, `genre`, `year_released`, `leading_actor`, `movie_poster_url`) VALUES (7, 'Oblivion', 'Action', '2013', NULL, NULL);
INSERT INTO `movie` (`id`, `title`, `genre`, `year_released`, `leading_actor`, `movie_poster_url`) VALUES (8, 'Transformers', 'Action', '2007', NULL, NULL);
INSERT INTO `movie` (`id`, `title`, `genre`, `year_released`, `leading_actor`, `movie_poster_url`) VALUES (9, 'The Other Guys', 'Comedy', '2010', NULL, NULL);
INSERT INTO `movie` (`id`, `title`, `genre`, `year_released`, `leading_actor`, `movie_poster_url`) VALUES (10, 'The Conjuring', 'Horror', '2013', NULL, NULL);
INSERT INTO `movie` (`id`, `title`, `genre`, `year_released`, `leading_actor`, `movie_poster_url`) VALUES (11, 'Insidious', 'Horror', '2010', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `genre`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `genre` (`genre_name`) VALUES ('Action');
INSERT INTO `genre` (`genre_name`) VALUES ('Comedy');
INSERT INTO `genre` (`genre_name`) VALUES ('Horror');
INSERT INTO `genre` (`genre_name`) VALUES ('Drama');
INSERT INTO `genre` (`genre_name`) VALUES ('Thriller');

COMMIT;


-- -----------------------------------------------------
-- Data for table `movie_genre`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `movie_genre` (`movie_id`, `genre_name`) VALUES (1, 'Thriller');
INSERT INTO `movie_genre` (`movie_id`, `genre_name`) VALUES (2, 'Thriller');
INSERT INTO `movie_genre` (`movie_id`, `genre_name`) VALUES (3, 'Drama');
INSERT INTO `movie_genre` (`movie_id`, `genre_name`) VALUES (4, 'Action');
INSERT INTO `movie_genre` (`movie_id`, `genre_name`) VALUES (5, 'Action');
INSERT INTO `movie_genre` (`movie_id`, `genre_name`) VALUES (6, 'Action');
INSERT INTO `movie_genre` (`movie_id`, `genre_name`) VALUES (7, 'Action');
INSERT INTO `movie_genre` (`movie_id`, `genre_name`) VALUES (8, 'Action');
INSERT INTO `movie_genre` (`movie_id`, `genre_name`) VALUES (9, 'Comedy');
INSERT INTO `movie_genre` (`movie_id`, `genre_name`) VALUES (10, 'Horror');
INSERT INTO `movie_genre` (`movie_id`, `genre_name`) VALUES (11, 'Horror');

COMMIT;


-- -----------------------------------------------------
-- Data for table `actor`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `actor` (`id`, `first_name`, `last_name`) VALUES (1, 'Leonardo', 'DiCaprio');
INSERT INTO `actor` (`id`, `first_name`, `last_name`) VALUES (2, 'Tom', 'Cruise');
INSERT INTO `actor` (`id`, `first_name`, `last_name`) VALUES (3, 'Benedict', 'Cumberbatch');
INSERT INTO `actor` (`id`, `first_name`, `last_name`) VALUES (4, 'Patrick', 'Wilson');
INSERT INTO `actor` (`id`, `first_name`, `last_name`) VALUES (5, 'Will', 'Ferrell');
INSERT INTO `actor` (`id`, `first_name`, `last_name`) VALUES (6, 'Harrison', 'Ford');
INSERT INTO `actor` (`id`, `first_name`, `last_name`) VALUES (7, 'Shia', 'LaBeouf');

COMMIT;


-- -----------------------------------------------------
-- Data for table `acted_in`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `acted_in` (`movie_id`, `actor_id`) VALUES (1, 1);
INSERT INTO `acted_in` (`movie_id`, `actor_id`) VALUES (2, 1);
INSERT INTO `acted_in` (`movie_id`, `actor_id`) VALUES (3, 3);
INSERT INTO `acted_in` (`movie_id`, `actor_id`) VALUES (4, 2);
INSERT INTO `acted_in` (`movie_id`, `actor_id`) VALUES (5, 4);
INSERT INTO `acted_in` (`movie_id`, `actor_id`) VALUES (6, 2);
INSERT INTO `acted_in` (`movie_id`, `actor_id`) VALUES (7, 2);
INSERT INTO `acted_in` (`movie_id`, `actor_id`) VALUES (8, 7);
INSERT INTO `acted_in` (`movie_id`, `actor_id`) VALUES (9, 5);
INSERT INTO `acted_in` (`movie_id`, `actor_id`) VALUES (10, 4);
INSERT INTO `acted_in` (`movie_id`, `actor_id`) VALUES (11, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `years`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `years` (`year`) VALUES (2007);
INSERT INTO `years` (`year`) VALUES (2008);
INSERT INTO `years` (`year`) VALUES (2009);
INSERT INTO `years` (`year`) VALUES (2010);
INSERT INTO `years` (`year`) VALUES (2011);
INSERT INTO `years` (`year`) VALUES (2012);
INSERT INTO `years` (`year`) VALUES (2013);
INSERT INTO `years` (`year`) VALUES (2014);
INSERT INTO `years` (`year`) VALUES (1989);

COMMIT;

