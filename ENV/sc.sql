-- ----------------------------
-- Table structure for sc_permission
-- ----------------------------
DROP TABLE IF EXISTS `sc_permission`;
CREATE TABLE `sc_permission` (
                                  `id` varchar(32) NOT NULL,
                                  `pid` bigint(20) DEFAULT NULL COMMENT '父级权限id',
                                  `name` varchar(100) DEFAULT NULL COMMENT '名称',
                                  `value` varchar(200) DEFAULT NULL COMMENT '权限值',
                                  `icon` varchar(500) DEFAULT NULL COMMENT '图标',
                                  `type` int(1) DEFAULT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
                                  `uri` varchar(200) DEFAULT NULL COMMENT '前端资源路径',
                                  `status` int(1) DEFAULT NULL COMMENT '启用状态；0->禁用；1->启用',
                                  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                  `sort` int(11) DEFAULT NULL COMMENT '排序',
                                  `level` int(11) DEFAULT 0 COMMENT '层级',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='后台用户权限表';

-- ----------------------------
-- Records of sc_permission
-- ----------------------------
# BEGIN;
# INSERT INTO `sc_permission` VALUES (1, 0, '商品', NULL, NULL, 0, NULL, 1, '2018-09-29 16:15:14', 0);
# INSERT INTO `sc_permission` VALUES (2, 1, '商品列表', 'pms:product:read', NULL, 1, '/pms/product/index', 1, '2018-09-29 16:17:01', 0);
# INSERT INTO `sc_permission` VALUES (3, 1, '添加商品', 'pms:product:create', NULL, 1, '/pms/product/add', 1, '2018-09-29 16:18:51', 0);
# INSERT INTO `sc_permission` VALUES (4, 1, '商品分类', 'pms:productCategory:read', NULL, 1, '/pms/productCate/index', 1, '2018-09-29 16:23:07', 0);
# INSERT INTO `sc_permission` VALUES (5, 1, '商品类型', 'pms:productAttribute:read', NULL, 1, '/pms/productAttr/index', 1, '2018-09-29 16:24:43', 0);
# INSERT INTO `sc_permission` VALUES (6, 1, '品牌管理', 'pms:brand:read', NULL, 1, '/pms/brand/index', 1, '2018-09-29 16:25:45', 0);
# INSERT INTO `sc_permission` VALUES (7, 2, '编辑商品', 'pms:product:update', NULL, 2, '/pms/product/updateProduct', 1, '2018-09-29 16:34:23', 0);
# INSERT INTO `sc_permission` VALUES (8, 2, '删除商品', 'pms:product:delete', NULL, 2, '/pms/product/delete', 1, '2018-09-29 16:38:33', 0);
# INSERT INTO `sc_permission` VALUES (9, 4, '添加商品分类', 'pms:productCategory:create', NULL, 2, '/pms/productCate/create', 1, '2018-09-29 16:43:23', 0);
# INSERT INTO `sc_permission` VALUES (10, 4, '修改商品分类', 'pms:productCategory:update', NULL, 2, '/pms/productCate/update', 1, '2018-09-29 16:43:55', 0);
# INSERT INTO `sc_permission` VALUES (11, 4, '删除商品分类', 'pms:productCategory:delete', NULL, 2, '/pms/productAttr/delete', 1, '2018-09-29 16:44:38', 0);
# INSERT INTO `sc_permission` VALUES (12, 5, '添加商品类型', 'pms:productAttribute:create', NULL, 2, '/pms/productAttr/create', 1, '2018-09-29 16:45:25', 0);
# INSERT INTO `sc_permission` VALUES (13, 5, '修改商品类型', 'pms:productAttribute:update', NULL, 2, '/pms/productAttr/update', 1, '2018-09-29 16:48:08', 0);
# INSERT INTO `sc_permission` VALUES (14, 5, '删除商品类型', 'pms:productAttribute:delete', NULL, 2, '/pms/productAttr/delete', 1, '2018-09-29 16:48:44', 0);
# INSERT INTO `sc_permission` VALUES (15, 6, '添加品牌', 'pms:brand:create', NULL, 2, '/pms/brand/add', 1, '2018-09-29 16:49:34', 0);
# INSERT INTO `sc_permission` VALUES (16, 6, '修改品牌', 'pms:brand:update', NULL, 2, '/pms/brand/update', 1, '2018-09-29 16:50:55', 0);
# INSERT INTO `sc_permission` VALUES (17, 6, '删除品牌', 'pms:brand:delete', NULL, 2, '/pms/brand/delete', 1, '2018-09-29 16:50:59', 0);
# INSERT INTO `sc_permission` VALUES (18, 0, '首页', NULL, NULL, 0, NULL, 1, '2018-09-29 16:51:57', 0);
# COMMIT;

-- ----------------------------
-- Table structure for sc_role
-- ----------------------------
DROP TABLE IF EXISTS `sc_role`;
CREATE TABLE `sc_role` (
                            `id` varchar(32) NOT NULL,
                            `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
                            `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
                            `admin_count` int(11) DEFAULT NULL COMMENT '后台用户数量',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建者',
                            `status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
                            `sort` int(11) DEFAULT '0',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='后台用户角色表';

-- ----------------------------
-- Records of sc_role
-- ----------------------------
BEGIN;
INSERT INTO `sc_role` VALUES (1, '商品管理员', '商品管理员', 0, '2018-09-30 15:46:11', NULL, 1, 0);
INSERT INTO `sc_role` VALUES (2, '商品分类管理员', '商品分类管理员', 0, '2018-09-30 15:53:45', NULL, 1, 0);
INSERT INTO `sc_role` VALUES (3, '商品类型管理员', '商品类型管理员', 0, '2018-09-30 15:53:56', NULL, 1, 0);
INSERT INTO `sc_role` VALUES (4, '品牌管理员', '品牌管理员', 0, '2018-09-30 15:54:12', NULL, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for sc_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `sc_role_permission_relation`;
CREATE TABLE `sc_role_permission_relation` (
                                                `id` varchar(32) NOT NULL,
                                                `role_id` varchar(32) DEFAULT NULL,
                                                `permission_id` varchar(32) DEFAULT NULL,
                                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sc_role_permission_relation
-- ----------------------------
BEGIN;
INSERT INTO `sc_role_permission_relation` VALUES (1, 1, 1);
INSERT INTO `sc_role_permission_relation` VALUES (2, 1, 2);
INSERT INTO `sc_role_permission_relation` VALUES (3, 1, 3);
COMMIT;

-- ----------------------------
-- Table structure for sc_user
-- ----------------------------
DROP TABLE IF EXISTS `sc_user`;
CREATE TABLE `sc_user` (
                            `id` varchar(32) NOT NULL,
                            `member_level_id` bigint(20) DEFAULT NULL,
                            `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
                            `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
                            `nickname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '昵称',
                            `phone` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号码',
                            `status` int(1) DEFAULT NULL COMMENT '帐号启用状态:0->禁用；1->启用',
                            `create_time` datetime DEFAULT NULL COMMENT '注册时间',
                            `icon` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
                            `gender` int(1) DEFAULT NULL COMMENT '性别：0->未知；1->男；2->女',
                            `birthday` date DEFAULT NULL COMMENT '生日',
                            `city` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所做城市',
                            `job` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职业',
                            `personalized_signature` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '个性签名',
                            `source_type` int(1) DEFAULT NULL COMMENT '用户来源',
                            `integration` int(11) DEFAULT NULL COMMENT '积分',
                            `growth` int(11) DEFAULT NULL COMMENT '成长值',
                            `luckey_count` int(11) DEFAULT NULL COMMENT '剩余抽奖次数',
                            `history_integration` int(11) DEFAULT NULL COMMENT '历史积分数量',
                            `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建者',
                            PRIMARY KEY (`id`) USING BTREE,
                            UNIQUE KEY `idx_username` (`username`) USING BTREE,
                            UNIQUE KEY `idx_phone` (`phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='会员表';

-- ----------------------------
-- Records of sc_user
-- ----------------------------
BEGIN;
INSERT INTO `sc_user` VALUES (1, 4, 'test', '202cb962ac59075b964b07152d234b70', 'windir', '18061581849', 1, '2018-08-02 10:35:44', NULL, 1, '2009-06-01', '上海', '学生', 'test', NULL, 5000, NULL, NULL, NULL, NULL);
INSERT INTO `sc_user` VALUES (3, 4, 'windy', 'e10adc3949ba59abbe56e057f20f883e', 'windy', '18061581848', 1, '2018-08-03 16:46:38', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sc_user` VALUES (4, 4, 'zhengsan', 'e10adc3949ba59abbe56e057f20f883e', 'zhengsan', '18061581847', 1, '2018-11-12 14:12:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sc_user` VALUES (5, 4, 'lisi', 'e10adc3949ba59abbe56e057f20f883e', 'lisi', '18061581841', 1, '2018-11-12 14:12:38', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sc_user` VALUES (6, 4, 'wangwu', 'e10adc3949ba59abbe56e057f20f883e', 'wangwu', '18061581842', 1, '2018-11-12 14:13:09', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sc_user` VALUES (7, 4, 'lion', 'e10adc3949ba59abbe56e057f20f883e', 'lion', '18061581845', 1, '2018-11-12 14:21:39', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sc_user` VALUES (8, 4, 'shari', 'e10adc3949ba59abbe56e057f20f883e', 'shari', '18061581844', 1, '2018-11-12 14:22:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sc_user` VALUES (9, 4, 'aewen', 'e10adc3949ba59abbe56e057f20f883e', 'aewen', '18061581843', 1, '2018-11-12 14:22:55', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sc_user` VALUES (13, 4, 'uniteddata', '{bcrypt}$2a$10$9P7J2eOFarhtTTzLjyZkT.GvGu38NXEptwzTURjdUBFB3quSgc9Vq', 'BigTudouUniteddata', '18061581811', 1, '2019-03-06 04:11:18', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sc_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `sc_user_role_relation`;
CREATE TABLE `sc_user_role_relation` (
                                          `id` varchar(32) NOT NULL,
                                          `user_id` varchar(32) DEFAULT NULL,
                                          `role_id` varchar(32) DEFAULT NULL,
                                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sc_user_role_relation
-- ----------------------------
BEGIN;
INSERT INTO `sc_user_role_relation` VALUES (1, 13, 1);
INSERT INTO `sc_user_role_relation` VALUES (2, 13, 2);
INSERT INTO `sc_user_role_relation` VALUES (3, 13, 3);
INSERT INTO `sc_user_role_relation` VALUES (4, 13, 4);
COMMIT;
