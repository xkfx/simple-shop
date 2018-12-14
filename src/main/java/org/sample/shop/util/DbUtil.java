package org.sample.shop.util;

import org.sample.shop.entity.SimpleUser;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtil {
    public static SimpleUser extractSimpleUserFromResultSet(ResultSet rs) throws SQLException {
        SimpleUser simpleUser = new SimpleUser();
        simpleUser.setId(rs.getLong("id"));
        simpleUser.setUsername(rs.getString("username"));
        simpleUser.setPassword(rs.getString("password"));
        simpleUser.setType(rs.getInt("type"));
        return simpleUser;
    }
}
