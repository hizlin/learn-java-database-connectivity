DELETE FROM `product`;

INSERT INTO `product` (`product_id`, `name`, `create_time`)
VALUES (1, '桌子', NOW()),
       (2, '椅子', NOW()),
       (3, '电脑', NOW());
