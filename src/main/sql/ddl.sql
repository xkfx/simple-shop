-- 用户
DROP TABLE simple_user;
DROP SEQUENCE simple_user_id_seq;
CREATE TABLE simple_user (
  id NUMBER(8, 0),
  type NUMBER(1,0) NOT NULL, -- 0卖家，1卖家，2物流
  username VARCHAR2(20) UNIQUE NOT NULL,
	password VARCHAR2(20) NOT NULL,
	PRIMARY KEY (id)
);
CREATE SEQUENCE simple_user_id_seq
MINVALUE 1000
MAXVALUE 99999999
START WITH 1000
INCREMENT BY 1
CACHE 50;
CREATE OR REPLACE TRIGGER simple_user_autoincrement
BEFORE INSERT ON simple_user
FOR EACH ROW
BEGIN
  SELECT simple_user_id_seq.nextval INTO:NEW.id FROM DUAL;
END;
/

-- 订单
DROP TABLE simple_order;
DROP SEQUENCE order_seq;
CREATE TABLE simple_order (
  id NUMBER(8, 0),
  user_id NUMBER(8, 0) NOT NULL,
  total NUMBER(8, 2), -- 最高消费999999.99
	PRIMARY KEY (id),
	FOREIGN KEY(user_id) REFERENCES simple_user(id)
);
CREATE SEQUENCE order_seq
MINVALUE 1000
MAXVALUE 99999999
START WITH 1000
INCREMENT BY 1
CACHE 50;
CREATE OR REPLACE TRIGGER order_autoincrement
BEFORE INSERT ON simple_order
FOR EACH ROW
BEGIN
  SELECT order_seq.nextval INTO:NEW.id FROM DUAL;
END;
/

-- 物品
DROP TABLE item;
DROP SEQUENCE item_seq;
CREATE TABLE item (
  id NUMBER(8, 0),
  user_id NUMBER(8, 0) NOT NULL,
  name VARCHAR2(20) NOT NULL,
	price NUMBER(8, 2) NOT NULL,
	status NUMBER(1, 0) NOT NULL, -- 0下架，1上架
	quantity NUMBER(4, 0) NOT NULL, -- 数量上限9999
	PRIMARY KEY (id),
	FOREIGN KEY(user_id) REFERENCES simple_user(id)
);
CREATE SEQUENCE item_seq
MINVALUE 1000
MAXVALUE 99999999
START WITH 1000
INCREMENT BY 1
CACHE 50;
CREATE OR REPLACE TRIGGER item_autoincrement
BEFORE INSERT ON item
FOR EACH ROW
BEGIN
  SELECT item_seq.nextval INTO:NEW.id FROM DUAL;
END;
/

-- 订单详情
DROP TABLE order_detail;
DROP SEQUENCE order_detail_seq;
DROP TRIGGER order_detail_autoincrement;
CREATE TABLE order_detail (
  id NUMBER(8, 0),
  order_id NUMBER(8, 0) NOT NULL,
  item_id NUMBER(8, 0) NOT NULL,
  user_id NUMBER(8, 0) NOT NULL,
  status NUMBER(1, 0) NOT NULL, -- 0待付款，1待发货，2已发货，3等待买家确认收获，4交易完成
  quantity NUMBER(4, 0) NOT NULL, -- 数量上限9999
  price NUMBER(8, 2) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY(order_id) REFERENCES simple_order(id),
	FOREIGN KEY(item_id) REFERENCES item(id),
	FOREIGN KEY(user_id) REFERENCES item(id)
);
CREATE SEQUENCE order_detail_seq
MINVALUE 1000
MAXVALUE 99999999
START WITH 1000
INCREMENT BY 1
CACHE 50;
CREATE OR REPLACE TRIGGER order_detail_autoincrement
BEFORE INSERT ON order_detail
FOR EACH ROW
BEGIN
  SELECT order_detail_seq.nextval INTO:NEW.id FROM DUAL;
END;
/

-- 运单
DROP TABLE transport_order;
DROP SEQUENCE transport_order_seq;
CREATE TABLE transport_order (
  id NUMBER(8, 0),
  user_id NUMBER(8, 0) NOT NULL,
  order_detail_id NUMBER(8, 0) NOT NULL,
  location VARCHAR2(20),
  status NUMBER(1, 0) NOT NULL, -- 0运送中，1正在派件，2完成
	PRIMARY KEY (id),
	FOREIGN KEY(order_detail_id) REFERENCES order_detail(id),
	FOREIGN KEY(user_id) REFERENCES simple_user(id)
);
CREATE SEQUENCE transport_order_seq
MINVALUE 1000
MAXVALUE 99999999
START WITH 1000
INCREMENT BY 1
CACHE 50;
CREATE OR REPLACE TRIGGER transport_order_autoincrement
BEFORE INSERT ON transport_order
FOR EACH ROW
BEGIN
  SELECT transport_order_seq.nextval INTO:NEW.id FROM DUAL;
END;
/