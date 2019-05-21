package Warsztat;

import java.sql.*;
import java.util.Arrays;

public class SolutionDao {
    private static final String CREATE_QUERY =
            "INSERT INTO solution(created, updated, description, exercise_id, users_id) VALUES (?,?,?,?,?)";
    private static final String READ_QUERY =
            "SELECT * FROM solution where id = ?";
    private static final String UPDATE_QUERY =
            "UPDATE solution SET created=?, updated=?,description=?,exercise_id=?,users_id=?, rating=?, comment=? where id = ?";
    private static final String DELETE_QUERY =
            "DELETE FROM solution WHERE id = ?";
    private static final String FIND_ALL_QUERY =
            "SELECT * FROM solution";
    private static final String FIND_ALL_BY_USER_ID_QUERY =
            "SELECT * FROM solution where users_id=?";
    private static final String FIND_ALL_BY_EXERCISE_ID_QUERY =
            "SELECT * FROM solution ehere exercise.id=? ORDER BY solution.created DESC";

    public Solution create(Solution solution) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, solution.getCreated());
            statement.setString(2, solution.getUpdated());
            statement.setString(3, solution.getDescription());
            statement.setInt(4, solution.getExercise_id());
            statement.setInt(5, solution.getUsers_id());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            System.err.println("Nie utworzono rozwiązania.");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Solution read(int solutionId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_QUERY);
            statement.setInt(1, solutionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setDescription(resultSet.getString("exercise_id"));
                solution.setDescription(resultSet.getString("users_id"));
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Solution solution) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);
            statement.setString(1, solution.getCreated());
            statement.setString(2, solution.getUpdated());
            statement.setString(3, solution.getDescription());
            statement.setInt(4, solution.getExercise_id());
            statement.setInt(5, solution.getUsers_id());
//            statement.setInt(6, solution.getRating());
//            statement.setString(7, solution.getComment());
            statement.setInt(8, solution.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Nie zaktualizowano rozwiązania.");
            System.err.println(e.getMessage());
        }
    }

    public void delete(int solutionId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, solutionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Nie usunięto rozwiązania.");
            System.err.println(e.getMessage());
        }
    }
    private Solution[] findAllByUserId(int usersId){
        try (Connection conn = ConnectionUtil.getConnection()) {
            Solution[] solutions = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_BY_USER_ID_QUERY);
            statement.setInt(1, usersId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUsers_id(resultSet.getInt("users_id"));
                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    private Solution[] addToArray(Solution u, Solution[] solutions) {
        Solution[] tmpSolution = Arrays.copyOf(solutions, solutions.length + 1);
        tmpSolution[solutions.length] = u;
        return tmpSolution;
    }
    public Solution[] findAll() {
        try (Connection conn = ConnectionUtil.getConnection()) {
            Solution[] solutions = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUsers_id(resultSet.getInt("users_id"));
                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    private Solution[] findAllByExerciseId(int exerciseId){
        try (Connection conn = ConnectionUtil.getConnection()) {
            Solution[] solutions = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_BY_EXERCISE_ID_QUERY);
            statement.setInt(1, exerciseId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUsers_id(resultSet.getInt("users_id"));
                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}