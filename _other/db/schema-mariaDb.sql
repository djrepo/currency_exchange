CREATE DATABASE IF NOT EXISTS trade;

DROP TABLE IF EXISTS trade.quote_entity;

CREATE TABLE trade.quote_entity (
                                `ASK` int(11) DEFAULT NULL,
                                `ASK_VOLUME` int(11) DEFAULT NULL,
                                `BID` int(11) DEFAULT NULL,
                                `BID_VOLUME` int(11) DEFAULT NULL,
                                `TIME` bigint(20) DEFAULT NULL,
                                `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                PRIMARY KEY (`id`),
                                KEY `time_idx` (`TIME`)
) ENGINE=InnoDB AUTO_INCREMENT=116561 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;