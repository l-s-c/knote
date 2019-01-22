
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

/**笔记表*/
CREATE table note_info (
	id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增Id',
	note_title char(50) NOT NULL DEFAULT '' COMMENT '笔记标题',
	note_text  varchar(255) NOT NULL DEFAULT '' COMMENT '笔记内容',
	label tinyint(1) NULL DEFAULT 0 COMMENT '标签',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	modify_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/**插入笔记表测试数据*/
insert into note_info(note_title,note_text,label,create_time,modify_time) value('note1','noteText1',1,now(),now());

);

/**增加笔记表电话字段*/
alter table note_info add column phone int(15) NOT NULL default 0 COMMENT '手机号' ;

/**添加 好友关联表*/
CREATE TABLE friend_info (
	id bigint(20) not null AUTO_INCREMENT COMMENT '',
	my_phone int(15) NOT NULL COMMENT '自己手机号',
	fr_phone int(15) NOT NULL COMMENT '好友手机号',
	remark char(15)	NULL DEFAULT '' COMMENT '备注',
	is_first tinyint NOT NULL DEFAULT 0 COMMENT '是否置顶',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
	modify_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT charset=UTF8;


/**备忘录表*/
CREATE TABLE remind_info(
	id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '',
	phone int(15) NOT NULL COMMENT '手机号',
 	text  varchar(100) NOT NULL DEFAULT '' COMMENT '备忘录内容',
 	label tinyint(1) NULL DEFAULT 0 COMMENT '标签',
	cron varchar(20)  NULL DEFAULT '' COMMENT '提醒表达式',
	start_time varchar(20) NULL DEFAULT '' COMMENT '开始时间',
	end_time varchar(20) NULL DEFAULT '' COMMENT '结束时间',
	rate tinyint NOT NULL DEFAULT 0 COMMENT '重复提醒频率',
	PRIMARY KEY(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;
alter table remind_info modify column cron varchar(100) NULL DEFAULT '' COMMENT '提醒表达式';