/*
Navicat MySQL Data Transfer

Source Server         : localtest
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : bookdb

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2020-03-06 12:58:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` varchar(50) NOT NULL,
  `bookname` varchar(255) DEFAULT NULL,
  `booktype` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `translator` varchar(255) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `publish_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `price` float(10,0) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `page` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('A00001', '国际商务英语', '英语', '无心', '无', '人民日报出版社', '2020-03-06 11:26:18', '19', '10', '111');
INSERT INTO `book` VALUES ('A00002', '万历十五年', '历史', '佚名', '无', '中国邮电出版社', '2020-03-06 11:27:29', '18', '10', '222');
INSERT INTO `book` VALUES ('A00003', '天龙八部', '小说', '金庸', '无', '北京邮电出版社', '2020-03-06 11:28:35', '12', '10', '235');
INSERT INTO `book` VALUES ('A00004', 'C语言程序设计', '程序设计', '佚名', '无', '人民日报', '2020-03-06 00:00:00', '19', '2', '122');

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow` (
  `id` varchar(50) NOT NULL,
  `book_id` varchar(50) DEFAULT NULL,
  `reader_id` varchar(50) DEFAULT NULL,
  `borrow_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `if_back` varchar(255) DEFAULT NULL,
  `back_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES ('14', 'A00001', '1', '2020-03-06 11:29:27', '是', '2020-03-06 11:29:55');
INSERT INTO `borrow` VALUES ('15', 'A00001', '1', '2020-03-06 11:31:23', '否', '2020-03-06 11:31:23');
INSERT INTO `borrow` VALUES ('16', 'A00002', '2', '2020-03-06 11:31:50', '是', '2020-03-06 11:31:50');
INSERT INTO `borrow` VALUES ('17', 'A00003', '2', '2020-03-06 11:32:21', '是', '2020-03-06 11:32:21');

-- ----------------------------
-- Table structure for reader
-- ----------------------------
DROP TABLE IF EXISTS `reader`;
CREATE TABLE `reader` (
  `id` varchar(50) NOT NULL,
  `readername` varchar(255) DEFAULT NULL,
  `readertype` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `max_num` int(11) DEFAULT NULL,
  `days_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reader
-- ----------------------------
INSERT INTO `reader` VALUES ('1', '张良', '教师', '男', '10', '90');
INSERT INTO `reader` VALUES ('2', '马超', '学生', '男', '10', '60');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `is_admin` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123', '是');
