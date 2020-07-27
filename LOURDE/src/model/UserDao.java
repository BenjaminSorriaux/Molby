package model;

import entity.User;
import view.UserPanel;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {
    public static ArrayList<User> getUsers()
    {
        Connection connection = Database.connectDatabase();

        try
        {
            ArrayList<User> users = new ArrayList<User>();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user as u " +
                    "INNER JOIN link_user_level as lul ON u.id = lul.id_user " +
                    "INNER JOIN level as l ON lul.id_level = l.id " +
                    "INNER JOIN link_user_group as lug ON u.id = lug.id_user " +
                    "INNER JOIN `group` as g ON lug.id_group = g.id " +
                    "INNER JOIN year_group as yg ON yg.id = u.id_year_group");

            while (resultSet.next())
            {
                users.add(new User(
                        resultSet.getInt("u.id"),
                        resultSet.getString("u.picture"),
                        resultSet.getString("u.name"),
                        resultSet.getString("u.firstname"),
                        resultSet.getString("u.email"),
                        resultSet.getInt("u.xp"),
                        resultSet.getInt("l.id"),
                        resultSet.getString("l.label"),
                        resultSet.getInt("g.id"),
                        resultSet.getString("g.name"),
                        resultSet.getInt("yg.id"),
                        resultSet.getString("yg.name")
                ));
            }

            return users;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static void updateUserExp(User user)
    {
        Connection connection = Database.connectDatabase();

        try
        {
            System.out.println("New experience : " + user.getExperience());
            System.out.println("ID : " + user.getId());

            // Prepare an INSERT query
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET xp = ? WHERE id = ?");

            // Set parameters for query
            preparedStatement.setInt(1, user.getExperience());
            preparedStatement.setInt(2, user.getId());

            // Execute the query
            preparedStatement.executeUpdate();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
