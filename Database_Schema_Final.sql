CREATE TABLE `capstore`.`admin` (
 	`admin_id` int(5) NOT NULL,
 	`admin_name` varchar(50) NOT NULL,
 	`admin_pswd` varchar(50) NOT NULL,
 	PRIMARY KEY (`admin_id`)
	) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;

CREATE TABLE `capstore`.`customers` (
	`customer_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
	`customer_name` varchar(50) NOT NULL,
	`customer_email` varchar(50) NOT NULL UNIQUE,
	`customer_pswd` varchar(50) NOT NULL,
  	`customer_mobile` varchar(50) NOT NULL UNIQUE,
	`customer_flag` boolean NOT NULL,
	PRIMARY KEY (`customer_id`)
	) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=latin1;
	
CREATE TABLE `capstore`.`merchants` (
	`merchant_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
	`merchant_name` varchar(50) NOT NULL,
	`merchant_email` varchar(50) NOT NULL UNIQUE,
	`merchant_pswd` varchar(50) NOT NULL,
  	`merchant_mobile` varchar(50) NOT NULL UNIQUE,
 	`merchant_type` varchar(50) NOT NULL,
 	`merchant_address` varchar(255) NOT NULL,
	`merchant_rating` varchar(50),
	`merchant_flag` boolean NOT NULL,	
	PRIMARY KEY (`merchant_id`)
	) ENGINE=InnoDB AUTO_INCREMENT=20001 DEFAULT CHARSET=latin1;

CREATE TABLE `capstore`.`products` (
 	`product_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
 	`product_name` varchar(50) NOT NULL UNIQUE,
 	`product_price` double(10,2) NOT NULL,
 	`product_category` enum('Books','Electronics','Fashion','Home and Furnishing','Personal Car','Sports','Others') NOT NULL,
 	`product_quantity` int(10) NOT NULL,
 	`product_rating` double(1,1),
 	`total_sold` int(5),
	PRIMARY KEY (`product_id`)
	) ENGINE=InnoDB AUTO_INCREMENT=30001 DEFAULT CHARSET=latin1;

CREATE TABLE `capstore`.`pincodes` (
 	`pincode` int(6) unsigned NOT NULL UNIQUE,
  	`area` varchar(50) NOT NULL,
  	`city` varchar(50) NOT NULL,
  	`state` varchar(50) NOT NULL,
	PRIMARY KEY (`pincode`)
	) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `capstore`.`customer_address` (
 	`address_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  	`customer_id` int(10) unsigned,
	`address_line` varchar(100) NOT NULL,  	
  	`pincode` int(6) unsigned,
	PRIMARY KEY (`address_id`),
	FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
	FOREIGN KEY (pincode) REFERENCES pincodes(pincode)
	) ENGINE=InnoDB AUTO_INCREMENT=40001 DEFAULT CHARSET=latin1;

CREATE TABLE `capstore`.`carts` (
 	`cart_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
 	`quantity` int(2) NOT NULL,
 	`product_id` int(10) unsigned,
 	`merchant_id` int(10) unsigned,
 	`customer_id` int(10) unsigned,
	PRIMARY KEY (`cart_id`),
	FOREIGN KEY (product_id) REFERENCES products(product_id),
	FOREIGN KEY (merchant_id) REFERENCES merchants(merchant_id),
	FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
	) ENGINE=InnoDB AUTO_INCREMENT=80001 DEFAULT CHARSET=latin1;

CREATE TABLE `capstore`.`wishlist` (
 	`wishlist_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
 	`product_id` int(10) unsigned,
 	`merchant_id` int(10) unsigned,
 	`customer_id` int(10) unsigned,
	PRIMARY KEY (`wishlist_id`),
	FOREIGN KEY (product_id) REFERENCES products(product_id),
	FOREIGN KEY (merchant_id) REFERENCES merchants(merchant_id),
	FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
	) ENGINE=InnoDB AUTO_INCREMENT=90001 DEFAULT CHARSET=latin1;

CREATE TABLE `capstore`.`coupons` (
 	`coupon_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
 	`customer_id` int(10) unsigned, 	
 	`coupon_code` varchar(50) NOT NULL UNIQUE,
 	`coupon_date` date NOT NULL,
 	`coupon_duration` varchar(50) NOT NULL,
 	`coupon_amount` int(10) NOT NULL,
	PRIMARY KEY (`coupon_id`),
	FOREIGN KEY (customer_id) REFERENCES customers(customer_id)	
	) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=latin1;
	
CREATE TABLE `capstore`.`feedbacks` (
 	`feedback_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
 	`feedback_message` varchar(50) NOT NULL,
 	`feedback_date` date NOT NULL,
 	`feedback_rating` double(1,1),
 	`product_id` int(10) unsigned,
 	`merchant_id` int(10) unsigned,
 	`customer_id` int(10) unsigned,
	PRIMARY KEY (`feedback_id`),
	FOREIGN KEY (product_id)	REFERENCES products(product_id),
	FOREIGN KEY (merchant_id) REFERENCES merchants(merchant_id),
	FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
	) ENGINE=InnoDB AUTO_INCREMENT=2001 DEFAULT CHARSET=latin1;
	
CREATE TABLE `capstore`.`discounts` (
 	`discount_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
 	`discount_percent` int(2) NOT NULL,
 	`product_id` int(10) unsigned,
 	`merchant_id` int(10) unsigned,
	PRIMARY KEY (`discount_id`),
	FOREIGN KEY (product_id)	REFERENCES products(product_id),
	FOREIGN KEY (merchant_id) REFERENCES merchants(merchant_id)
	) ENGINE=InnoDB AUTO_INCREMENT=3001 DEFAULT CHARSET=latin1;
	
CREATE TABLE `capstore`.`merchant_pincodes` (
 	`pincode_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  	`merchant_id` int(10) unsigned,
  	`pincode` int(6) NOT NULL,
	PRIMARY KEY (`pincode_id`),
	FOREIGN KEY (merchant_id) REFERENCES merchants(merchant_id)
	) ENGINE=InnoDB AUTO_INCREMENT=4001 DEFAULT CHARSET=latin1;
	
CREATE TABLE `capstore`.`products_merchants` (
 	`product_id` int(10) unsigned,
  	`merchant_id` int(10) unsigned,
	FOREIGN KEY (product_id) REFERENCES products(product_id),
	FOREIGN KEY (merchant_id) REFERENCES merchants(merchant_id)
	) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `capstore`.`customer_orders` (
 	`order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  	`customer_id` int(10) unsigned,
  	`coupon_id` int(10) unsigned,
  	`address_id` int(10) unsigned,
 	`order_total` double(10,2) NOT NULL,
 	`timestamp` timestamp NOT NULL,
	PRIMARY KEY (`order_id`),
	FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
	FOREIGN KEY (coupon_id) REFERENCES coupons(coupon_id),
	FOREIGN KEY (address_id) REFERENCES customer_address(address_id)
	) ENGINE=InnoDB AUTO_INCREMENT=50001 DEFAULT CHARSET=latin1;

CREATE TABLE `capstore`.`order_entries` (
 	`entry_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  	`order_id` int(10) unsigned,
  	`product_id` int(10) unsigned,
  	`merchant_id` int(10) unsigned,	   
 	`quantity` int(10) NOT NULL,
	`base_price` double(10,2) NOT NULL, 
	`discount` int(10) NOT NULL,
	`status` enum('processing','delivered','returned') NOT NULL,		
	PRIMARY KEY (`entry_id`),
	FOREIGN KEY (order_id) REFERENCES customer_orders(order_id),
	FOREIGN KEY (product_id) REFERENCES products(product_id),	
	FOREIGN KEY (merchant_id) REFERENCES merchants(merchant_id)	
	) ENGINE=InnoDB AUTO_INCREMENT=60001 DEFAULT CHARSET=latin1;
	
CREATE TABLE `capstore`.`invoice` (
 	`transaction_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  	`order_id` int(10) unsigned,
 	`status` enum('paid','unpaid') NOT NULL,
	PRIMARY KEY (`transaction_id`),
	FOREIGN KEY (order_id) REFERENCES customer_orders(order_id)
	) ENGINE=InnoDB AUTO_INCREMENT=70001 DEFAULT CHARSET=latin1;
