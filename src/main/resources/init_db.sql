CREATE SCHEMA `internetshop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `internetshop`.`items` (
`item_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
 `price` DECIMAL(6,2) NOT NULL,
 PRIMARY KEY (`item_id`));

INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('Iphone 11', '1100');
INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('Samsung U8', '900');

CREATE TABLE `internetshop`.`users` (
`user_id` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(80) NOT NULL,
`login` VARCHAR(80) NOT NULL,
`password` VARCHAR(80) NOT NULL,
`role_name` VARCHAR(80) NOT NULL,
`token` VARCHAR(80) NOT NULL,
PRIMARY KEY (`user_id`));



ALTER TABLE `internetshop`.`orders_items`
    DROP FOREIGN KEY `orders_items_items_fk`,
    DROP FOREIGN KEY `orders_items_orders_fk`;
ALTER TABLE `internetshop`.`orders_items`
    ADD CONSTRAINT `orders_items_items_fk`
        FOREIGN KEY (`item_id`)
            REFERENCES `internetshop`.`items` (`item_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    ADD CONSTRAINT `orders_items_orders_fk`
        FOREIGN KEY (`order_id`)
            REFERENCES `internetshop`.`orders` (`order_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;
