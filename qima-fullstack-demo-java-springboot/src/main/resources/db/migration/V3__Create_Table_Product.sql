CREATE TABLE `product` (
  `product_id` bigint NOT NULL AUTO_INCREMENT,
  `available` bit(1) NOT NULL,
  `description` varchar(255) NOT NULL,
  `name` varchar(80) NOT NULL,
  `price` double NOT NULL,
  `category_id` bigint NOT NULL,
  PRIMARY KEY (`product_id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
);