DROP DATABASE IF EXISTS `addlist`;
CREATE DATABASE IF NOT EXISTS `addlist` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `addlist`;

DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE IF NOT EXISTS `tbl_user` (
  `id` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `pwquestion` varchar(128) NOT NULL COMMENT '密保问题',
  `pwanswer` varchar(128) NOT NULL COMMENT '密保答案',
  `default_login` char(1) DEFAULT '0' COMMENT '默认0，记住密码1，自动登陆2',
  `remarked` varchar(128) NOT NULL COMMENT '备注',
   primary key(id)  
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='用户表';

DROP TABLE IF EXISTS `tbl_contact`;
CREATE TABLE IF NOT EXISTS `tbl_contact`(
	`id` int(6) unsigned NOT NULL AUTO_INCREMENT,
	`user_id` int(6) unsigned NOT NULL,
  	`contactname` varchar(32) NOT NULL COMMENT '联系人用户名',
  	`moble` varchar(32) NOT NULL COMMENT '电话',
  	`gender` varchar(6) NULL DEFAULT NULL COMMENT '性别',
  	`birthday` DATE NULL DEFAULT NULL COMMENT '生日',
  	`address` varchar(128) NULL DEFAULT NULL COMMENT '地址',
  	`remarked` varchar(128) NULL DEFAULT NULL COMMENT '备注',
	primary key(id)  
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='联系人表';

