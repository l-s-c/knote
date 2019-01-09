
CREATE database note;
use note;

/**用户表*/
CREATE table user_info (
	id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	phone int(15) NOT NULL COMMENT '手机号',
	pwd varchar(100) NOT NULL COMMENT '密码',
	open_id varchar(100) NULL COMMENT '微信key',
	create_time datetime(0) NOT NULL COMMENT '创建时间',
	modify_time datetime(0) NOT NULL COMMENT '修改时间',
	PRIMARY  KEY (id)
) ;