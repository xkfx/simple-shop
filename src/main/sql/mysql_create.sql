-- CREATE DATABASE shop1;
-- recreate tables
DROP TABLE IF EXISTS `cart`;
DROP TABLE IF EXISTS `transport_order`;
DROP TABLE IF EXISTS `order_detail`;
DROP TABLE IF EXISTS `item`;
DROP TABLE IF EXISTS `simple_order`;
DROP TABLE IF EXISTS `simple_user`;
-- simple_user
DROP TABLE IF EXISTS `simple_user`;
CREATE TABLE simple_user (
  id BIGINT NOT NULL AUTO_INCREMENT,
  type TINYINT NOT NULL, -- 0买家，1卖家，2物流
  username VARCHAR(20) UNIQUE NOT NULL,
	password VARCHAR(20) NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
-- simple_order
DROP TABLE IF EXISTS `simple_order`;
CREATE TABLE simple_order (
  id BIGINT NOT NULL AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  total DECIMAL(8, 2), -- 最高消费999999.99
	PRIMARY KEY (id),
	FOREIGN KEY(user_id) REFERENCES simple_user(id)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
-- item
DROP TABLE IF EXISTS `item`;
CREATE TABLE item (
  id BIGINT NOT NULL AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  name VARCHAR(63) NOT NULL,
	price DECIMAL(8, 2) NOT NULL,
	status TINYINT NOT NULL, -- 0下架，1上架
	quantity DECIMAL(4, 0) NOT NULL, -- 数量上限9999
	PRIMARY KEY (id),
	FOREIGN KEY(user_id) REFERENCES simple_user(id)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
-- order_detail
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE order_detail (
  id BIGINT NOT NULL AUTO_INCREMENT,
  order_id BIGINT NOT NULL,
  item_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  status TINYINT NOT NULL, -- 0待付款，1待发货，2已发货，3等待买家确认收获，4交易完成
  quantity DECIMAL(4, 0), -- 数量上限9999
  price DECIMAL(8, 2), -- 简单起见，这个字段暂时没用到，直接和item表进行连表计算。
	PRIMARY KEY (id),
	FOREIGN KEY(order_id) REFERENCES simple_order(id),
	FOREIGN KEY(item_id) REFERENCES item(id),
	FOREIGN KEY(user_id) REFERENCES simple_user(id)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
-- transport_order
DROP TABLE IF EXISTS `transport_order`;
CREATE TABLE transport_order (
  id BIGINT NOT NULL AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  order_detail_id BIGINT NOT NULL,
  location VARCHAR(63),
  status TINYINT NOT NULL, -- 0运送中，1正在派件，2完成
	PRIMARY KEY (id),
	FOREIGN KEY(order_detail_id) REFERENCES order_detail(id),
	FOREIGN KEY(user_id) REFERENCES simple_user(id)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
-- cart
DROP TABLE IF EXISTS `cart`;
CREATE TABLE cart (
  id BIGINT NOT NULL AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  item_id BIGINT NOT NULL,
  quantity DECIMAL(4, 0), -- 数量上限9999，ps:简单起见，未用该字段，假设只能一个一个买。
  PRIMARY KEY (id),
  FOREIGN KEY(user_id) REFERENCES simple_user(id),
  FOREIGN KEY(item_id) REFERENCES item(id)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

-- 2019-02-09 秒杀模块
-- 秒杀活动表
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE seckill (
  id BIGINT NOT NULL AUTO_INCREMENT,
  item_id BIGINT NOT NULL,
  price DECIMAL(8, 2),
  quantity DECIMAL(4, 0),
  create_time TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  start_time TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
  end_time TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
  PRIMARY KEY (id),
  FOREIGN KEY(item_id) REFERENCES item(id)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
-- seckill 测试数据
INSERT INTO seckill(item_id, price, quantity, start_time, end_time)
VALUES
  (1001, 1, 100, '2019-02-10 00:00:00', '2019-02-10 00:01:00'),
  (1002, 1, 100, '2019-02-10 00:00:00', '2019-02-10 00:01:00'),
  (1003, 0.1, 100, '2019-02-10 00:00:00', '2019-02-10 00:01:00'),
  (1004, 0.1, 100, '2019-02-10 00:00:00', '2019-02-10 00:01:00');
-- 没有及时付款的秒杀是无效的 ...
ALTER TABLE order_detail
ADD end_time TIMESTAMP COMMENT '截至日期'; -- 暂时没用到