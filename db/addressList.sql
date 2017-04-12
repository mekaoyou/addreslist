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
	`contact_type` int(6) unsigned NOT NULL,
  	`contactname` varchar(32) NOT NULL COMMENT '联系人姓名',
  	`moble` varchar(32) NOT NULL COMMENT '电话',
  	`gender` varchar(6) NULL DEFAULT NULL COMMENT '性别',
  	`birthday` DATE NULL DEFAULT NULL COMMENT '生日',
  	`address` varchar(128) NULL DEFAULT NULL COMMENT '地址',
  	`qq` varchar(15) NULL DEFAULT NULL COMMENT 'QQ',
  	`email` varchar(32) NULL DEFAULT NULL COMMENT 'EMAIL',
  	`unit` varchar(32) NULL DEFAULT NULL COMMENT '单位',
  	`post` varchar(6) NULL DEFAULT NULL COMMENT '邮编',
  	`img` varchar(128) NULL DEFAULT NULL COMMENT '头像',
  	`remarked` varchar(128) NULL DEFAULT NULL COMMENT '备注',
	primary key(id)  
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='联系人表';

DROP TABLE IF EXISTS `tbl_contact_type`;
CREATE TABLE IF NOT EXISTS `tbl_contact_type`(
	`id` int(6) unsigned NOT NULL AUTO_INCREMENT,
	`type_name` varchar(32) NOT NULL,
	primary key(id)  
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='联系人分类';


DROP TABLE IF EXISTS `tbl_kd_code`;
CREATE TABLE IF NOT EXISTS `tbl_kd_code`(
	`code` varchar(32) NOT NULL,
	`kd_name` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='快递公司编号';

insert into tbl_kd_code values ('AJ','安捷快递');
insert into tbl_kd_code values ('ANE','安能物流');
insert into tbl_kd_code values ('AXD','安信达快递');
insert into tbl_kd_code values ('BQXHM','北青小红帽');
insert into tbl_kd_code values ('BFDF','百福东方');
insert into tbl_kd_code values ('BTWL','百世快运');
insert into tbl_kd_code values ('CCES','CCES快递');
insert into tbl_kd_code values ('CITY100','城市100');
insert into tbl_kd_code values ('COE','COE东方快递');
insert into tbl_kd_code values ('CSCY','长沙创一');
insert into tbl_kd_code values ('CDSTKY','成都善途速运');
insert into tbl_kd_code values ('DBL','德邦');
insert into tbl_kd_code values ('DSWL','D速物流');
insert into tbl_kd_code values ('DTWL','大田物流');
insert into tbl_kd_code values ('EMS','EMS');
insert into tbl_kd_code values ('FAST','快捷速递');
insert into tbl_kd_code values ('FEDEX','FEDEX联邦(国内件）');
insert into tbl_kd_code values ('FEDEX_GJ','FEDEX联邦(国际件）');
insert into tbl_kd_code values ('FKD','飞康达');
insert into tbl_kd_code values ('GDEMS','广东邮政');
insert into tbl_kd_code values ('GSD','共速达');
insert into tbl_kd_code values ('GTO','国通快递');
insert into tbl_kd_code values ('GTSD','高铁速递');
insert into tbl_kd_code values ('HFWL','汇丰物流');
insert into tbl_kd_code values ('HHTT','天天快递');
insert into tbl_kd_code values ('HLWL','恒路物流');
insert into tbl_kd_code values ('HOAU','天地华宇');
insert into tbl_kd_code values ('hq568','华强物流');
insert into tbl_kd_code values ('HTKY','百世快递');
insert into tbl_kd_code values ('HXLWL','华夏龙物流');
insert into tbl_kd_code values ('HYLSD','好来运快递');
insert into tbl_kd_code values ('JGSD','京广速递');
insert into tbl_kd_code values ('JIUYE','九曳供应链');
insert into tbl_kd_code values ('JJKY','佳吉快运');
insert into tbl_kd_code values ('JLDT','嘉里物流');
insert into tbl_kd_code values ('JTKD','捷特快递');
insert into tbl_kd_code values ('JXD','急先达');
insert into tbl_kd_code values ('JYKD','晋越快递');
insert into tbl_kd_code values ('JYM','加运美');
insert into tbl_kd_code values ('JYWL','佳怡物流');
insert into tbl_kd_code values ('KYWL','跨越物流');
insert into tbl_kd_code values ('LB','龙邦快递');
insert into tbl_kd_code values ('LHT','联昊通速递');
insert into tbl_kd_code values ('MHKD','民航快递');
insert into tbl_kd_code values ('MLWL','明亮物流');
insert into tbl_kd_code values ('NEDA','能达速递');
insert into tbl_kd_code values ('PADTF','平安达腾飞快递');
insert into tbl_kd_code values ('QCKD','全晨快递');
insert into tbl_kd_code values ('QFKD','全峰快递');
insert into tbl_kd_code values ('QRT','全日通快递');
insert into tbl_kd_code values ('RFD','如风达');
insert into tbl_kd_code values ('SAD','赛澳递');
insert into tbl_kd_code values ('SAWL','圣安物流');
insert into tbl_kd_code values ('SBWL','盛邦物流');
insert into tbl_kd_code values ('SDWL','上大物流');
insert into tbl_kd_code values ('SF','顺丰快递');
insert into tbl_kd_code values ('SFWL','盛丰物流');
insert into tbl_kd_code values ('SHWL','盛辉物流');
insert into tbl_kd_code values ('ST','速通物流');
insert into tbl_kd_code values ('STO','申通快递');
insert into tbl_kd_code values ('STWL','速腾快递');
insert into tbl_kd_code values ('SURE','速尔快递');
insert into tbl_kd_code values ('TSSTO','唐山申通');
insert into tbl_kd_code values ('UAPEX','全一快递');
insert into tbl_kd_code values ('UC','优速快递');
insert into tbl_kd_code values ('WJWL','万家物流');
insert into tbl_kd_code values ('WXWL','万象物流');
insert into tbl_kd_code values ('XBWL','新邦物流');
insert into tbl_kd_code values ('XFEX','信丰快递');
insert into tbl_kd_code values ('XYT','希优特');
insert into tbl_kd_code values ('XJ','新杰物流');
insert into tbl_kd_code values ('YADEX','源安达快递');
insert into tbl_kd_code values ('YCWL','远成物流');
insert into tbl_kd_code values ('YD','韵达快递');
insert into tbl_kd_code values ('YDH','义达国际物流');
insert into tbl_kd_code values ('YFEX','越丰物流');
insert into tbl_kd_code values ('YFHEX','原飞航物流');
insert into tbl_kd_code values ('YFSD','亚风快递');
insert into tbl_kd_code values ('YTKD','运通快递');
insert into tbl_kd_code values ('YTO','圆通速递');
insert into tbl_kd_code values ('YXKD','亿翔快递');
insert into tbl_kd_code values ('YZPY','邮政平邮/小包');
insert into tbl_kd_code values ('ZENY','增益快递');
insert into tbl_kd_code values ('ZHQKD','汇强快递');
insert into tbl_kd_code values ('ZJS','宅急送');
insert into tbl_kd_code values ('ZTE','众通快递');
insert into tbl_kd_code values ('ZTKY','中铁快运');
insert into tbl_kd_code values ('ZTO','中通速递');
insert into tbl_kd_code values ('ZTWL','中铁物流');
insert into tbl_kd_code values ('ZYWL','中邮物流');
insert into tbl_kd_code values ('AMAZON','亚马逊物流');
insert into tbl_kd_code values ('SUBIDA','速必达物流');
insert into tbl_kd_code values ('RFEX','瑞丰速递');
insert into tbl_kd_code values ('QUICK','快客快递');
insert into tbl_kd_code values ('CJKD','城际快递');
insert into tbl_kd_code values ('CNPEX','CNPEX中邮快递');
insert into tbl_kd_code values ('HOTSCM','鸿桥供应链');
insert into tbl_kd_code values ('HPTEX','海派通物流公司');
insert into tbl_kd_code values ('AYCA','澳邮专线');
insert into tbl_kd_code values ('PANEX','泛捷快递');
insert into tbl_kd_code values ('PCA','PCA Express');
insert into tbl_kd_code values ('UEQ','UEQ Express');


