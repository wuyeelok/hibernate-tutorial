DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `emp_id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `company` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`emp_id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;