/*
Navicat MySQL Data Transfer

Source Server         : mysqlLocl
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-05-12 15:29:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for u_permission
-- ----------------------------
DROP TABLE IF EXISTS `u_permission`;
CREATE TABLE `u_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(256) DEFAULT NULL COMMENT 'url地址',
  `name` varchar(64) DEFAULT NULL COMMENT 'url描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_permission
-- ----------------------------

-- ----------------------------
-- Table structure for u_role
-- ----------------------------
DROP TABLE IF EXISTS `u_role`;
CREATE TABLE `u_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `type` varchar(10) DEFAULT NULL COMMENT '角色类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_role
-- ----------------------------
INSERT INTO `u_role` VALUES ('1', 'admin', 'page');
INSERT INTO `u_role` VALUES ('2', 'sel', 'page');
INSERT INTO `u_role` VALUES ('3', 'deal', 'page');

-- ----------------------------
-- Table structure for u_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `u_role_permission`;
CREATE TABLE `u_role_permission` (
  `rid` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `pid` bigint(20) DEFAULT NULL COMMENT '权限ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for u_user
-- ----------------------------
DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱|登录帐号',
  `pswd` varchar(32) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` bigint(1) DEFAULT '1' COMMENT '1:有效，0:禁止登录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_user
-- ----------------------------
INSERT INTO `u_user` VALUES ('1', 'sunrui', 'rui.849217@163.com', '123456', '2018-05-12 11:35:27', '2018-05-12 11:35:23', '1');
INSERT INTO `u_user` VALUES ('2', 'maozhi', 'asjdlkf', '123456', '2018-05-12 13:41:46', '2018-05-12 13:41:48', '1');

-- ----------------------------
-- Table structure for u_user_role
-- ----------------------------
DROP TABLE IF EXISTS `u_user_role`;
CREATE TABLE `u_user_role` (
  `uid` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `rid` bigint(20) DEFAULT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_user_role
-- ----------------------------
INSERT INTO `u_user_role` VALUES ('1', '1');
INSERT INTO `u_user_role` VALUES ('2', '2');
INSERT INTO `u_user_role` VALUES ('1', '2');
INSERT INTO `u_user_role` VALUES ('1', '3');
