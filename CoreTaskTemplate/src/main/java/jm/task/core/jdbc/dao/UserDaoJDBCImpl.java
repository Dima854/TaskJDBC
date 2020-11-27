package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE users (" +
                "  id INT NOT NULL AUTO_INCREMENT," +
                "  name VARCHAR(255) NOT NULL," +
                "  lastName VARCHAR(255) NOT NULL," +
                "  age INT NOT NULL," +
                "  PRIMARY KEY (id));";

        Statement statement = null;

        try{
            statement = connection.createStatement();
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void dropUsersTable() {
        String sql = "DROP TABLE users";

        Statement statement = null;

        try{
            statement = connection.createStatement();
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO users (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void removeUserById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM users WHERE ID=?";

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, 1);

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();

        String sql ="SELECT * FROM users";

        Statement statement = null;
        try{
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                int age = resultSet.getByte(4);

                User user = new User(id, name, lastName, (byte) age);

                userList.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql ="TRUNCATE TABLE users";

        Statement statement = null;

        try {
            statement = connection.createStatement();
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
