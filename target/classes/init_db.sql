CREATE SCHEMA `internetshop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `internetshop`.`items` (
`item_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
 `price` DECIMAL(6,2) NOT NULL,
 PRIMARY KEY (`item_id`));

INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('Iphone 11', '1100');
INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('Samsung U8', '900');




