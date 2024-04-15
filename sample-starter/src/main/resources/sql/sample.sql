#todo 你的建表语句,包含索引


CREATE TABLE `order` (
                         `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单号',
                         `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '购买人',
                         `sku_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'SkuId',
                         `amount` int DEFAULT NULL COMMENT '购买数量',
                         `money` decimal(25,8) DEFAULT NULL COMMENT '购买金额',
                         `pay_time` datetime DEFAULT NULL COMMENT '购买时间',
                         `pay_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '支付状态',
                         `del_flag` bigint DEFAULT NULL COMMENT '删除标志（0代表存在 1代表删除）',
                         `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人',
                         `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                         `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '修改人',
                         `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;