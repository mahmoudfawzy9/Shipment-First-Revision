SET NAMES utf8 ;
SET character_set_client = utf8mb4 ;


CREATE TABLE `shipment`(
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`carrier_id` INTEGER(255) NOT NULL,
	`shipment_service_id` VARCHAR(255) NOT NULL DEFAULT 'UPSExpress',
	`carrier_type` INTEGER(255) NOT NULL DEFAULT 2,
	`carrier` VARCHAR(255) NOT NULL DEFAULT 'UPSExpress',
	`name` VARCHAR(255) NOT NULL DEFAULT 'UPSExpress',
	`status` VARCHAR(255) NOT NULL DEFAULT 'notconfirmed',
	`length` INTEGER(255) NOT NULL DEFAULT 0,
	`width` INTEGER(255) NOT NULL DEFAULT 0,
	`height` INTEGER(255) NOT NULL DEFAULT 0,
	`weight` INTEGER(255) NOT NULL DEFAULT 0,
	`package_details` ENUM('width', 'height', 'length', 'weight') NOT NULL,
	`action_type` INTEGER(255) NOT NULL DEFAULT 0,
    `action_value` VARCHAR(500) NOT NULL DEFAULT 'deliver to office',
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	`is_confirmed` TINYINT NOT NULL DEFAULT 0,
	`is_delivered` TINYINT NOT NULL DEFAULT 0,
	`is_deleted` TINYINT NOT NULL DEFAULT 0,
	PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO shipment (carrier_id, shipment_service_id, status, carrier_type, carrier, length,
width, height, action_type, action_value, created_at)
VALUES (1, 'UPSExpress', 'notconfirmed', 2, 'ups', 100, 100, 100, 0, 'deliver to office', NOW());


CREATE TABLE `carrier`(
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`carrier_id` INTEGER(255) NOT NULL,
	`carrier_service_id` VARCHAR(255) NOT NULL DEFAULT 'fedexAIR',
	`carrier_type` INTEGER(255) NOT NULL DEFAULT 1,
	`carrier` VARCHAR(255) NOT NULL DEFAULT 'fedex',
	`name` VARCHAR(255) NOT NULL DEFAULT 'fedex',
	`length` INTEGER(255) NOT NULL DEFAULT 0,
	`width` INTEGER(255) NOT NULL DEFAULT 0,
	`height` INTEGER(255) NOT NULL DEFAULT 0,
	`weight` INTEGER(255) NOT NULL DEFAULT 0,
	`package_detalis` ENUM('width', 'height', 'length', 'weight') NOT NULL,
	`action_type` INTEGER(255) NOT NULL DEFAULT 0,
    `action_value` VARCHAR(500) NOT NULL DEFAULT 'deliver to office',
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	`is_delivered` TINYINT NOT NULL DEFAULT 0,
	`is_confirmed` TINYINT NOT NULL DEFAULT 0,
	`is_deleted` TINYINT NOT NULL DEFAULT 0,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO carrier (carrier_id, carrier_type, carrier, length,
width, height, action_type,action_value, created_at)
VALUES (1,1, 'ups', 100,100,100,0,'deliver to office', NOW());
