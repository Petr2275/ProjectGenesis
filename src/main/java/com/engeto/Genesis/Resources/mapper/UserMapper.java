package com.engeto.Genesis.Resources.mapper;

import com.engeto.Genesis.Resources.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {
    public static User map(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("person_id"),
                rs.getString("uuid")
        );
    }
}
