package com.github.wololock.q45287455;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Profile {
    private final int id;
    private final String name;

    public Profile(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static class Mapper implements ResultSetMapper<Profile> {
        public Profile map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
            return new Profile(resultSet.getInt("profile_id"), resultSet.getString("name"));
        }
    }
}
