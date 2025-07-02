package com.engeto.Genesis.Resources.repository;

import com.engeto.Genesis.Resources.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    private final boolean detail;

    public UserRowMapper(boolean detail) {
        this.detail = detail;
    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        if (detail) {
            return new User(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("person_id"),
                    rs.getString("uuid")
            );
        } else {
            return new User(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    null,
                    null
            );
        }
    }
}
