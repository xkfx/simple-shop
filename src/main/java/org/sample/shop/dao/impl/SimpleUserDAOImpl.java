package org.sample.shop.dao.impl;

import org.sample.shop.dao.SimpleUserDAO;
import org.sample.shop.db.ConnectionFactory;
import org.sample.shop.entity.SimpleUser;
import org.sample.shop.exception.DaoException;
import org.sample.shop.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public enum SimpleUserDAOImpl implements SimpleUserDAO {

    INSTANCE;

    @Override
    public SimpleUser getByUsername(String username) {
        SimpleUser result = null;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "SELECT  id, username, password, type " +
                    "FROM simple_user " +
                    "WHERE username=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        result = DbUtil.extractSimpleUserFromResultSet(rs);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public int saveUser(SimpleUser user) {
        int i = 0;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO simple_user(type, username, password) " +
                    "VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, user.getType());
                ps.setString(2, user.getUsername());
                ps.setString(3, user.getPassword());
                i = ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return i;
    }
}
