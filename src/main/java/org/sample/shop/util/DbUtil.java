package org.sample.shop.util;

import org.sample.shop.entity.Item;
import org.sample.shop.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtil {
    public static User extractSimpleUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setType(rs.getInt("type"));
        return user;
    }

    public static Item extractItemFromResultSet(ResultSet rs) throws SQLException {
        Item item = new Item();
        item.setId(rs.getLong("id"));
        item.setUid(rs.getLong("user_id"));
        item.setName(rs.getString("name"));
        item.setPrice(rs.getDouble("price"));
        item.setQuantity(rs.getInt("quantity"));
        item.setStatus(rs.getInt("status"));
        return item;
    }
}
