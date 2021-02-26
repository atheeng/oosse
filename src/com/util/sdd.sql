/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.5.62 : Database - online_order
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `cart` */

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `item` varchar(255) DEFAULT NULL,
  `unit_price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `totalAmount` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `cart` */

/*Table structure for table `order_item` */

DROP TABLE IF EXISTS `order_item`;

CREATE TABLE `order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(255) NOT NULL,
  `product_id` int(11) NOT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `order_item` */

insert  into `order_item`(`id`,`order_no`,`product_id`) values 
(1,'2021-02-03-1',2),
(2,'2021-02-03-1',1);

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no_item` int(11) DEFAULT NULL,
  `order_date` date NOT NULL,
  `total_amount` double NOT NULL,
  `status` varchar(255) NOT NULL,
  `order_no` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `orders` */

insert  into `orders`(`id`,`no_item`,`order_date`,`total_amount`,`status`,`order_no`,`user_id`) values 
(1,1,'2021-02-03',1200,'PENDING','2021-02-03-1',1),
(2,1,'2021-02-03',12000,'PENDING','2021-02-03-1',1);

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) NOT NULL,
  `product_type` varchar(40) NOT NULL,
  `total_quantity` int(11) NOT NULL,
  `price` double NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `product` */

insert  into `product`(`id`,`product_name`,`product_type`,`total_quantity`,`price`,`description`,`created_date`,`updated_date`) values 
(1,'TV','Electronic_Media',10,12000,'test','2021-02-02','2021-02-03'),
(2,'MOBILE','Electronic_Media',5,1200,'xiomi','2021-02-02','2021-02-03');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(225) NOT NULL,
  `password` varchar(225) NOT NULL,
  `role` varchar(225) NOT NULL,
  `first_name` varchar(225) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`role`,`first_name`,`last_name`,`address`,`mobile_no`,`status`) values 
(1,'admin','d033e22ae348aeb5660fc2140aec35850c4da997','ADMIN','admin','admin','admin','9841141368','ACTIVE'),
(2,'arun@yopmail.com','admin','CUSTOMER','Arun','Tamang','kathmandu','9841141368','ACTIVE'),
(10,'pema','82c801c8b4165c80a027b85e556ae1698dd22b79','MANAGER','pema','tamang','ktm','984151514','ACTIVE'),
(11,'suraj','d033e22ae348aeb5660fc2140aec35850c4da997','ADMIN','suraj','awal','test','test','ACTIVE');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
