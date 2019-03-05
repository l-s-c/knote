CREATE database resume;
use resume;
/*user表*/
CREATE TABLE user(
	user_id bigint(20) 	NOT NULL AUTO_INCREMENT COMMENT '用户id',
	user_name varchar(50) NOT NULL COMMENT '用户名',
	pwd   varchar(100) NOT NULL COMMENT '密码',
	role_id bigint(20) NOT NULL DEFAULT 0 COMMENT '角色id',
	resume_id bigint(20) NOT NULL  COMMENT '简历id',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	modify_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (user_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*role*/
CREATE TABLE role(
	role_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
	role_desc varchar(50) NOT NULL COMMENT '用户描述',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	modify_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (role_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*power*/
CREATE TABLE power(
	power_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权利id',
	power_desc varchar(50) NOT NULL COMMENT '权利描述',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	modify_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (power_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*roletopower*/
CREATE TABLE role_power(
	role_id bigint(20) NOT NULL COMMENT '角色id',
	power_id bigint(20) NOT NULL  COMMENT '权利id',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	modify_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*resume*/
CREATE TABLE resume(
	resume_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '简历id',
	name varchar(20) NULL DEFAULT '' COMMENT '姓名',
	gender tinyint(1) NULL DEFAULT 0 COMMENT '性别',
	work_ex_id bigint(20) NULL COMMENT '工作经历ID',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	modify_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (resume_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*work_ex*/
CREATE TABLE work_ex(
	work_ex_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '工作经历ID',
	company varchar(50)	 NOT NULL COMMENT '公司名称',
	work_desc varchar(255) NOT NULL COMMENT '工作描述',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	modify_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (work_ex_id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*企业选择简历字段表*/
CREATE TABLE user_resume(
	user_id bigint(20) 	NOT NULL  COMMENT '用户id',
	column_desc varchar(50) NOT NULL DEFAULT '' COMMENT '所需字段名（与简历表中字段相对应）',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	modify_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*字典表*/
CREATE TABLE dict (
	id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '',
	code varchar(10) NOT NULL  COMMENT '代码',
	name varchar(50) NOT NULL  COMMENT '描述',
	data_code varchar(30) NOT NULL  COMMENT '模块名' ,
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
	modify_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;