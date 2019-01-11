
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
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/**增加是否激活字段*/
alter table user_info add column is_aclivate tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否激活';
/*增加时间默认值*/
alter table user_info modify column create_time timestamp not null default current_timestamp COMMENT '创建时间';
alter table user_info modify column modify_time timestamp NOT NULL default current_timestamp on UPDATE CURRENT_TIMESTAMP COMMENT '修改时间';

/*增加頭像路徑字段名*/
alter table user_info add column head_path varchar(100) NOT NULL DEFAULT 'file/default.png' COMMENT '頭像路徑';