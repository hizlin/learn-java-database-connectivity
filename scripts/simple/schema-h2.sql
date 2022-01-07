DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`
(
    `product_id`  bigint      NOT NULL,
    `name`        varchar(32) NOT NULL,
    `create_time` datetime    NOT NULL,
    PRIMARY KEY (`product_id`)
);
