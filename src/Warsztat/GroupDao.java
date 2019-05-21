package Warsztat;

import java.sql.*;
import java.util.Arrays;

public class GroupDao {

    private static final String CREATE_USER_QUERY =
            "INSERT INTO user_group(name) VALUES (?)";
    private static final String READ_USER_QUERY =
            "SELECT * FROM user_group where id = ?";
    private static final String UPDATE_USER_QUERY =
            "UPDATE user_group SET id = ?, name = ?";
    private static final String DELETE_USER_QUERY =
            "DELETE FROM user_group WHERE id = ?";
    private static final String FIND_ALL_GROUP_QUERY =
            "SELECT * FROM user_group";

    public User_group create(User_group group) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, group.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                group.setId(resultSet.getInt(1));
            }
            return group;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User_group read(int groupId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User_group group = new User_group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                return group;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User_group group) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, group.getName());
            statement.setInt(2, group.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private User_group[] addToArray(User_group u, User_group[] groups) {
        User_group[] tmpGroup= Arrays.copyOf(groups, groups.length + 1);
        tmpGroup[groups.length] = u;
        return tmpGroup;
    }
    public User_group[] findAll() {
        try (Connection conn = ConnectionUtil.getConnection()) {
            User_group[] groups = new User_group[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_GROUP_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User_group group = new User_group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                groups = addToArray(group, groups);
            }
            return groups;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
