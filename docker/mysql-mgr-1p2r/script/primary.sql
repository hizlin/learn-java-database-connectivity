
CREATE TABLE `log_record`  (
    `id` bigint NOT NULL,
    `name` varchar(16) NOT NULL,
    `time` datetime NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
);

