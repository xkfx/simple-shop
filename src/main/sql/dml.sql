-- saveUser
INSERT INTO simple_user(type, username, password) VALUES (0, 'admin123', 123);
COMMIT;


-- removeById
DELETE FROM item WHERE id=1052

-- updateById
UPDATE item SET name='updete', price=20.5, status=0, quantity=100 WHERE id=1000

-- saveOrder
INSERT INTO simple_order(user_id, total) VALUES (1000, 333.33);
COMMIT;
INSERT INTO order_detail(order_id, item_id, user_id, quantity, price, status) VALUES(1000, 1000, 1000, 22, 32.5, 0);
COMMIT;

-- List<Order> getByUid(long uid)
SELECT id, user_id, total FROM simple_order WHERE user_id=1000;
SELECT id, order_id AS orderId, item_id AS itemId, user_id AS userId, quantity, price, status FROM order_detail WHERE order_id=1000;

-- List<OrderDetail> getByOrderId(long orderId)
SELECT id, order_id AS orderId, item_id AS itemId, user_id AS userId, quantity, price, status FROM order_detail WHERE order_id=1000;

-- List<OrderDetail> getByUid(long uid)
SELECT id, order_id AS orderId, item_id AS itemId, user_id AS userId, quantity, price, status FROM order_detail WHERE user_id=1000;