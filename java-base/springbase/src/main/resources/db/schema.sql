
DROP TABLE IF EXISTS `tb_enum`;
CREATE TABLE `tb_enum` (
  `id` int(11) NOT NULL AUTO_INCREMENT ,
  `enum_type` varchar(255) DEFAULT NULL ,
  `enum_key` varchar(255) DEFAULT NULL,
  `enum_value` varchar(255) DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=228308 DEFAULT CHARSET=utf8 ;


DROP TABLE IF EXISTS `tb_regist_channle`;
CREATE TABLE `tb_regist_channle` (
  `id` int(11) NOT NULL AUTO_INCREMENT ,
  `regist_channel` varchar(20) DEFAULT NULL ,
  `number` int(11) DEFAULT NULL  ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8 ;


DROP TABLE IF EXISTS `tb_regist_result`;
CREATE TABLE `tb_regist_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT ,
  `behavior` varchar(20) DEFAULT NULL  ,
  `channel` varchar(20) DEFAULT NULL ,
  `min` int(11) DEFAULT NULL ,
  `max` int(11) DEFAULT NULL ,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20767 DEFAULT CHARSET=utf8 ;

DROP TABLE IF EXISTS `tb_regist_user`;
CREATE TABLE `tb_regist_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT ,
  `behavior_time` datetime DEFAULT NULL,
  `behavior` varchar(255) DEFAULT NULL ,
  `regist_channel` varchar(20) DEFAULT NULL,
  `user_registration_number` int(11) DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=228311 DEFAULT CHARSET=utf8 ;
