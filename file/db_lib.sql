/*
Navicat MySQL Data Transfer

Source Server         : aa
Source Server Version : 50088
Source Host           : localhost:3306
Source Database       : db_lib

Target Server Type    : MYSQL
Target Server Version : 50088
File Encoding         : 65001

Date: 2017-09-19 15:21:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_bookinfo
-- ----------------------------
DROP TABLE IF EXISTS `tb_bookinfo`;
CREATE TABLE `tb_bookinfo` (
  `id` int(11) NOT NULL auto_increment COMMENT '图书编号',
  `bookname` varchar(70) default NULL COMMENT '书名',
  `typeid` int(11) default NULL COMMENT '类型',
  `author` varchar(30) default NULL COMMENT '作者',
  `ISBN` varchar(20) default NULL COMMENT '出版社',
  `price` double(8,2) default NULL COMMENT '价格',
  `page` int(11) default NULL COMMENT '页数',
  `inTime` date default NULL COMMENT '录入时间',
  `operator` int(11) default NULL COMMENT '操作员',
  `del` tinyint(1) default '0' COMMENT '是否删除0存在1删除',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of tb_bookinfo
-- ----------------------------
INSERT INTO `tb_bookinfo` VALUES ('7', 'Java Web开发实战宝典', '4', '王国辉', '清华大学出版社', '89.00', '834', '2011-02-24', '1', '0');
INSERT INTO `tb_bookinfo` VALUES ('8', 'Java Web开发典型模块大全', '4', '王国辉、王毅、王殊宇', '清华大学出版社', '89.00', '752', '2011-02-24', '1', '0');
INSERT INTO `tb_bookinfo` VALUES ('9', 'njknjn', '5', 'mnm', '某某出版社', '11.00', '0', '2011-02-24', '1', '0');
INSERT INTO `tb_bookinfo` VALUES ('10', 'gfhgf', '4', 'gfd', '某某出版社', '26.00', '0', '2011-02-24', '1', '0');

-- ----------------------------
-- Table structure for tb_booktype
-- ----------------------------
DROP TABLE IF EXISTS `tb_booktype`;
CREATE TABLE `tb_booktype` (
  `id` int(11) NOT NULL auto_increment COMMENT '图书类型编号',
  `typename` varchar(30) default NULL COMMENT '图书类型名称',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of tb_booktype
-- ----------------------------
INSERT INTO `tb_booktype` VALUES ('4', '网络编程');
INSERT INTO `tb_booktype` VALUES ('5', '数据库开发');

-- ----------------------------
-- Table structure for tb_borrow_back
-- ----------------------------
DROP TABLE IF EXISTS `tb_borrow_back`;
CREATE TABLE `tb_borrow_back` (
  `id` int(11) NOT NULL auto_increment COMMENT '图书借还编号',
  `readerid` int(11) default NULL COMMENT '读者编号',
  `bookid` int(11) default NULL COMMENT '图书编号',
  `borrowTime` date default NULL COMMENT '借阅时间',
  `backTime` date default NULL COMMENT '应还时间',
  `realTime` date default NULL COMMENT '归还时间',
  `operator` int(11) default NULL COMMENT '操作员',
  `ifback` tinyint(1) default '0' COMMENT '是否归还0未还1已还',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of tb_borrow_back
-- ----------------------------
INSERT INTO `tb_borrow_back` VALUES ('7', '4', '7', '2011-02-24', '2011-03-16', '2011-03-16', '1', '1');
INSERT INTO `tb_borrow_back` VALUES ('8', '4', '7', '2011-02-24', '2011-03-16', null, '1', '0');
INSERT INTO `tb_borrow_back` VALUES ('9', '5', '8', '2011-02-24', '2011-04-05', null, '1', '0');

-- ----------------------------
-- Table structure for tb_library
-- ----------------------------
DROP TABLE IF EXISTS `tb_library`;
CREATE TABLE `tb_library` (
  `id` int(11) NOT NULL auto_increment COMMENT '图书馆信息编号',
  `libraryname` varchar(50) default NULL COMMENT '图书馆名称',
  `curator` varchar(20) default NULL COMMENT '馆长',
  `tel` varchar(20) default NULL COMMENT '联系电话',
  `address` varchar(100) default NULL COMMENT '联系地址',
  `email` varchar(100) default NULL COMMENT '联系邮箱',
  `url` varchar(100) default NULL COMMENT '图书馆网址',
  `createDate` date default NULL COMMENT '建馆时间',
  `introduce` text COMMENT '简介',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of tb_library
-- ----------------------------
INSERT INTO `tb_library` VALUES ('1', '图书馆', '孟涛', '0371-63228895', '郑州市中原路89号', 'libaray@libaray.com', 'http://www', '2005-06-16', '本市拥有计算机类图书最多的图书馆。');

-- ----------------------------
-- Table structure for tb_manager
-- ----------------------------
DROP TABLE IF EXISTS `tb_manager`;
CREATE TABLE `tb_manager` (
  `id` int(11) NOT NULL auto_increment COMMENT '用户编号',
  `username` varchar(30) default NULL COMMENT '用户名',
  `pwd` varchar(30) default NULL COMMENT '密码',
  `role` varchar(10) default NULL COMMENT '权限1管理员2操作员3读者',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of tb_manager
-- ----------------------------
INSERT INTO `tb_manager` VALUES ('1', 'admin', 'admin', '管理员');
INSERT INTO `tb_manager` VALUES ('7', 'wgh', '111', '操作员');
INSERT INTO `tb_manager` VALUES ('9', 'tom', '111', '读者');

-- ----------------------------
-- Table structure for tb_reader
-- ----------------------------
DROP TABLE IF EXISTS `tb_reader`;
CREATE TABLE `tb_reader` (
  `id` int(11) NOT NULL auto_increment COMMENT '读者编号',
  `name` varchar(20) default NULL COMMENT '姓名',
  `typeid` int(11) default NULL COMMENT '类型',
  `sex` varchar(4) default NULL COMMENT '性别',
  `vocation` varchar(50) default NULL COMMENT '职业',
  `birthday` date default NULL COMMENT '出生日期',
  `paperType` varchar(10) default NULL COMMENT '有效证件',
  `paperNO` varchar(20) default NULL COMMENT '证件号码',
  `tel` varchar(20) default NULL COMMENT '电话',
  `email` varchar(100) default NULL COMMENT '电子邮件',
  `createDate` date default NULL COMMENT '登记日期',
  `operator` int(11) default NULL COMMENT '操作员',
  `remark` text COMMENT '备注',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of tb_reader
-- ----------------------------
INSERT INTO `tb_reader` VALUES ('4', '琦琦', '4', '女', '学生', '2010-07-10', '身份证', '220104201007100001', '84978981', 'wgh717@sohu.com', '2011-02-24', '1', '无');
INSERT INTO `tb_reader` VALUES ('5', 'wgh', '4', '女', '程序员', '1980-07-10', '工作证', '20010228', '84978981', 'wgh717@sohu.com', '2011-02-24', '1', '无');
INSERT INTO `tb_reader` VALUES ('6', 'uui', '4', '男', '', null, '身份证', 'iuoi', '', 'nmb', '2011-02-24', '1', '');

-- ----------------------------
-- Table structure for tb_readertype
-- ----------------------------
DROP TABLE IF EXISTS `tb_readertype`;
CREATE TABLE `tb_readertype` (
  `id` int(11) NOT NULL auto_increment COMMENT '读者类型编号',
  `name` varchar(50) default NULL COMMENT '读者类型名称',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of tb_readertype
-- ----------------------------
INSERT INTO `tb_readertype` VALUES ('4', '学生');
