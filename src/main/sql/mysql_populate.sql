-- simple_user
INSERT ignore INTO simple_user(type, username, password)
VALUES (0, 'test_customer', '123');
INSERT ignore INTO simple_user(type, username, password)
VALUES (1, 'test_merchant', '123');
INSERT ignore INTO simple_user(type, username, password)
VALUES (2, 'test_transport', '123');
-- item
INSERT INTO item(user_id, name, price, status, quantity)
VALUES (1001, '耐克NBA联名板鞋', 813.40, 0, 322);
INSERT INTO item(user_id, name, price, status, quantity)
VALUES (1001, '潮范时光机', 218.00, 0, 20);
INSERT INTO item(user_id, name, price, status, quantity)
VALUES (1001, '加拿大MAC子弹头唇膏', 143.55, 0, 191);
INSERT INTO item(user_id, name, price, status, quantity)
VALUES (1001, '三星9.7寸高清平板电脑', 4999, 1, 1);
INSERT INTO item(user_id, name, price, status, quantity)
VALUES (1001, '晨光AJD97326透明封箱胶胶带胶布4.8CM/30码', 19.90, 1, 2);
