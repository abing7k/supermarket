/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : supermarket

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 24/09/2022 17:57:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bills
-- ----------------------------
DROP TABLE IF EXISTS `bills`;
CREATE TABLE `bills`  (
                          `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '账单id',
                          `u_id` int(0) NULL DEFAULT NULL COMMENT '用户id',
                          `g_id` int(0) NULL DEFAULT NULL COMMENT '商品id',
                          `count` int(0) NULL DEFAULT NULL COMMENT '购买数量',
                          `price` float NULL DEFAULT NULL COMMENT '总价格',
                          `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                          `modified_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bills
-- ----------------------------
INSERT INTO `bills` VALUES (1, 1, 1, 1, 10, '2022-09-14 10:40:35', NULL);

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
                         `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
                         `u_id` int(0) NULL DEFAULT NULL COMMENT '用户id',
                         `g_id` int(0) NULL DEFAULT NULL COMMENT '商品id',
                         `count` int(0) NULL DEFAULT NULL COMMENT '商品数量',
                         `deleted` int(0) NULL DEFAULT NULL COMMENT '是否被删除',
                         `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                         `modified_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '删除时间',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES (1, 1, 1, 0, 0, '2022-09-24 14:09:10', '2022-09-24 14:09:10');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
                          `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '商品id',
                          `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名称',
                          `t_id` int(0) NULL DEFAULT NULL COMMENT '商品所属的类型id',
                          `number` bigint(0) NULL DEFAULT NULL COMMENT '商品剩余数量',
                          `price` float NULL DEFAULT NULL COMMENT '商品单价',
                          `discount` float NULL DEFAULT NULL COMMENT '商品折扣',
                          `enabled` tinyint(0) NULL DEFAULT NULL COMMENT '1-正常，0-下架（库存数量为0了）',
                          `deleted` tinyint(0) NULL DEFAULT NULL COMMENT '是否被删除(1代表删除)',
                          `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                          `modified_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '删除时间',
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '汉堡2', 1, 989, 11, 10, 1, 0, '2022-09-24 16:09:00', '2022-09-24 16:09:00');
INSERT INTO `goods` VALUES (3, '炸鸡', 1, 100, 10, 10, 1, 0, '2022-09-24 16:12:09', '2022-09-24 16:12:09');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
                         `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '角色id',
                         `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
                         `deleted` int(0) NULL DEFAULT NULL COMMENT '是否被删除',
                         `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                         `modified_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin', 0, '2022-09-14 10:26:03', NULL);
INSERT INTO `role` VALUES (2, 'cashier', 0, '2022-09-14 10:26:03', NULL);
INSERT INTO `role` VALUES (3, 'member', 0, '2022-09-19 17:25:46', NULL);

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
                         `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '类型id',
                         `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型名称',
                         `father` int(0) NULL DEFAULT NULL COMMENT '父id,没有上一级为0',
                         `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '描述',
                         `deleted` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除',
                         `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                         `modified_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, '食品', 0, NULL, 0, '2022-09-14 10:38:05', NULL);
INSERT INTO `type` VALUES (2, '11', 0, '1', 0, '2022-09-23 21:28:36', '2022-09-23 21:28:36');
INSERT INTO `type` VALUES (3, 't2', 1, '2', 0, '2022-09-23 21:16:09', '2022-09-23 21:16:09');
INSERT INTO `type` VALUES (4, 't3', 0, NULL, 1, '2022-09-23 21:41:45', '2022-09-23 21:41:45');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `id` int(10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '用户id',
                         `r_id` int(0) NULL DEFAULT NULL COMMENT '角色id',
                         `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
                         `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户密码',
                         `tel` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户电话',
                         `integral` float NULL DEFAULT NULL COMMENT '消费积分',
                         `balance` float NULL DEFAULT NULL COMMENT '用户余额',
                         `deleted` tinyint(0) NULL DEFAULT NULL COMMENT '是否被删除',
                         `enabled` tinyint(1) NULL DEFAULT NULL COMMENT '是否启用',
                         `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                         `modified_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (0000000001, 1, 'hanbing', '$2a$10$ogvUqZZAxrBwrmVI/e7.SuFYyx8my8d.9zJ6bs9lPKWvbD9eefyCe', '15993056607', 100, 1000, 0, 1, '2022-09-14 10:27:37', NULL);
INSERT INTO `user` VALUES (0000000002, 2, 'zhangsan', '$2a$10$ogvUqZZAxrBwrmVI/e7.SuFYyx8my8d.9zJ6bs9lPKWvbD9eefyCe', '110', 10, 1000, 0, 1, '2022-09-19 17:27:35', NULL);
INSERT INTO `user` VALUES (0000000003, 3, 'demo02', '$2a$10$I.WmsSJaBP9abyKmrlE.c.6ymxOmZ2YTUQmK8bRalXCGwHNCE96zK', '120', 1000, 667, 0, 1, '2022-09-20 15:20:43', '2022-09-20 15:20:43');
INSERT INTO `user` VALUES (0000000004, 2, '111', '$2a$10$ogvUqZZAxrBwrmVI/e7.SuFYyx8my8d.9zJ6bs9lPKWvbD9eefyCe', '', NULL, 1, 0, 1, '2022-09-20 16:09:10', '2022-09-20 16:09:10');
INSERT INTO `user` VALUES (0000000005, 2, 't1', '$2a$10$fJiWqwisFyrDFbV6fGNpoOE0ddO/WDj2ZmQy28AataNDBxmrL11c.', '', NULL, NULL, 0, 1, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
