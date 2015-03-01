DROP TABLE hotel.users;
DROP TABLE hotel.roles;
DROP TABLE hotel.orders;
DROP TABLE hotel.rooms;
DROP TABLE hotel.categories;
DROP TABLE hotel.orders_room;

CREATE TABLE `hotel`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(20) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(20) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC));
  
  
  CREATE TABLE `hotel`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));
  
  CREATE TABLE `hotel`.`rooms` (
  `id` INT NOT NULL,
  `room_number` INT NOT NULL,
  `floor` INT NOT NULL,
  `category_id` INT NOT NULL,
  `price` INT NOT NULL,
  `busy_state` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `room_number_UNIQUE` (`room_number` ASC),
  ALTER TABLE `hotel`.`rooms` 
	CHANGE COLUMN `room_number` `number` INT(11) NOT NULL ,
	ADD COLUMN `place_amount` INT NOT NULL AFTER `busy_state`;
 
	
  
   CREATE TABLE `hotel`.`categories` (
  `id` INT NOT NULL,
  `room_category` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `room_category_UNIQUE` (`room_category` ASC));
  ALTER TABLE `hotel`.`categories` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ;
  
  CREATE TABLE `hotel`.`orders` (
  `id` INT NOT NULL,
  `bill` INT NOT NULL,
  `rooms_amount` INT NOT NULL,
  `date_arrival` DATE NOT NULL,
  `date_check-out` DATE NOT NULL,
  `user_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_idx` (`user_id` ASC),
  CONSTRAINT `id`
    FOREIGN KEY (`user_id`)
    REFERENCES `hotel`.`users` (`id`)
    ON DELETE CASCADE
  	ON UPDATE CASCADE;
)
    
    CREATE TABLE `hotel`.`orders_room` (
  `id` INT NOT NULL,
  `order_id` INT NOT NULL,
  `room_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `order_id_idx` (`order_id` ASC),
  INDEX `room_id_idx` (`room_id` ASC),
  CONSTRAINT `order_id`
    FOREIGN KEY (`order_id`)
    REFERENCES `hotel`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `room_id`
    FOREIGN KEY (`room_id`)
    REFERENCES `hotel`.`rooms` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    ALTER TABLE `hotel`.`orders` 
CHANGE COLUMN `bill` `bill` INT(11) NOT NULL ,
ADD COLUMN `date_booking` DATE NOT NULL AFTER `bill`;

ALTER TABLE `hotel`.`booking_request` 
		ADD COLUMN `date_booking` DATE NOT NULL AFTER `category_id`;
		
		
		ALTER TABLE `hotel`.`booking_request` 
CHANGE COLUMN `category_id` `category_id` INT NOT NULL ,
ADD COLUMN `request_state` VARCHAR(45) NULL AFTER `date_booking`,
ADD COLUMN `room_id` INT NULL AFTER `request_state`;

    
    
    INSERT INTO roles VALUES (1, 'admin');
	INSERT INTO roles VALUES (2, 'manager');
	INSERT INTO roles VALUES (3, 'client');
	
	INSERT INTO categories VALUES (1, 1);
	INSERT INTO categories VALUES (2, 2);
	INSERT INTO categories VALUES (3, 3);
	INSERT INTO categories VALUES (4, 4);
	INSERT INTO categories VALUES (5, 5);
	
	INSERT INTO users VALUES(1, 'petrov','petrov','Petrov','Petr', 3);
	INSERT INTO users VALUES(2, 'ivanov','ivanov','Ivanov','Ivan', 3);
	INSERT INTO users VALUES(3, 'sidorov','sidorov','Sidorov','Aleksandr', 3);
	INSERT INTO users VALUES(4, 'manager','manager','Tkachuk','Andrii', 2);
	INSERT INTO users VALUES(5, 'admin','admin','Geichenko','Iegor', 1);
	
	INSERT INTO rooms VALUES(1,5,1,1,200,0,1);
	INSERT INTO rooms VALUES(2,7,1,1,400,0,2);
	INSERT INTO rooms VALUES(3,10,1,2,600,0,2);
	INSERT INTO rooms VALUES(4,21,2,3,800,0,1);
	INSERT INTO rooms VALUES(5,25,2,3,800,0,3);
	INSERT INTO rooms VALUES(6,31,3,4,1000,0,2);
	INSERT INTO rooms VALUES(7,33,3,4,1000,0,4);
	INSERT INTO rooms VALUES(8,1,1,1,199,0,1);
	 INSERT INTO rooms VALUES(9,2,1,1,220,0,2);
	INSERT INTO rooms VALUES(10,3,1,1,281,0,3);
	INSERT INTO rooms VALUES(11,4,1,1,315,0,4);
	INSERT INTO rooms VALUES(12,6,1,2,299,0,1);
	INSERT INTO rooms VALUES(13,9,1,2,361,0,2);
	INSERT INTO rooms VALUES(14,11,1,2,512,0,3);
    INSERT INTO rooms VALUES(15,12,1,2,454,0,4);
	INSERT INTO rooms VALUES(16,14,1,3,379,0,1);
	INSERT INTO rooms VALUES(17,15,1,3,578,0,2);
	INSERT INTO rooms VALUES(18,16,2,3,629,0,3);
	INSERT INTO rooms VALUES(19,22,2,3,690,0,4);
	INSERT INTO rooms VALUES(20,23,3,4,567,0,1);
	INSERT INTO rooms VALUES(21,24,3,4,800,0,2);
    INSERT INTO rooms VALUES(22,26,3,4,679,0,3);
    INSERT INTO rooms VALUES(23,27,1,4,389,0,4);
	INSERT INTO rooms VALUES(24,28,1,1,235,0,2);
	INSERT INTO rooms VALUES(25,30,1,2,399,0,2);
	INSERT INTO rooms VALUES(26,32,2,3,599,0,2);
	INSERT INTO rooms VALUES(27,34,2,4,799,0,2);
	INSERT INTO rooms VALUES(28,35,3,1,180,0,1);
	INSERT INTO rooms VALUES(29,36,3,2,620,0,1);
    INSERT INTO rooms VALUES(30,37,3,3,750,0,1);
    INSERT INTO rooms VALUES(31,38,3,4,898,0,1);
    
	
	


    
    
    
    
    
