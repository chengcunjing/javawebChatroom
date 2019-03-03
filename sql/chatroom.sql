create database if not exists chatroom;
use chatroom;
drop table if exists tb_chat_message;  -- 先删除外键表 (注释的时候需要注意 -- 后面需要空格一下)
drop table if exists tb_user_info;   -- 再删除主键表
create table tb_user_info (
	u_id int auto_increment primary key comment '编号',
	u_name varchar(20) unique not null comment '账户',
	u_pwd varchar(20) not null comment '密码',
	u_nick varchar(20) unique comment '昵称',
	u_img varchar(256) default 'images/u_icon.png' comment '头像',
	u_email varchar(50) comment '邮箱',
	u_phone char(13) comment '电话' check(regexp_like(u_phone,'^\d{3}-\d{4}-\d{4}$|^\d{4}-\d{8}$')),
	u_card_id char(18) comment '身份证' check(len(u_card_id) in (15,18)),
	u_register_time timestamp not null comment '注册时间',
	u_state bit default 0 comment '状态',
	u_remark varchar(256) comment '备注'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8; 

create table tb_chat_message(
	c_id int auto_increment primary key comment '编号',
	c_send_content text not null comment '消息内容',
	c_send_time datetime not null comment '发送时间',
	c_send_id int not null comment '发送方',
	c_receiver_id int comment '接收方',
	c_remark varchar(256) comment'备注'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

alter table tb_chat_message add	constraint fk_msg_sendid foreign key(c_send_id) references  tb_user_info(u_id) on delete cascade;
alter table tb_chat_message add	constraint fk_msg_receiverid foreign key(c_receiver_id) references  tb_user_info(u_id) on delete set null;

-- 插入数据
insert into tb_user_info (u_name,u_pwd,u_nick,u_email,u_phone,u_card_id,u_register_time,u_state) values 
    ('张三','333333','老三','zhangsan@qq.com','3333-33333333',333333333333333333,'2018-08-30 11:11:11',0),
	('李四','444444','老四','lisi@qq.com','4444-44444444',444444444444444444,'2018-08-30 11:11:11',0),
	('王五','555555','老五','wangwu@qq.com','5555-55555555',555555555555555555,'2018-08-30 11:11:11',0),
	('赵六','666666','老六','zhaoliu@qq.com','6666-66666666',666666666666666666,'2018-08-30 11:11:11',0),
	('马七','777777','老七','maqi@qq.com','7777-77777777',777777777777777777,'2018-08-30 11:11:11',0);

select * from tb_user_info;	
select * from tb_chat_message;


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
