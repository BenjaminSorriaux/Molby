package model;

import entity.Level;

import java.sql.*;
import java.util.ArrayList;

public class LevelDao {

    public static ArrayList<Level> getLevels()
    {
        Connection connection = Database.connectDatabase();

        try
        {
            ArrayList<Level> levels = new ArrayList<Level>();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM level");

            while (resultSet.next())
            {
                levels.add(new Level(
                        resultSet.getInt("id"),
                        resultSet.getString("label"),
                        resultSet.getString("description"),
                        resultSet.getInt("cost_xp"),
                        resultSet.getInt("required_xp"),
                        resultSet.getString("skin")
                ));
            }

            return levels;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static void createLevel(Level level)
    {
        Connection connection = Database.connectDatabase();

        try
        {
            // Prepare an INSERT query
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO level (label, description, cost_xp, required_xp, skin) VALUES (?, ?, ?, ?, ?)");

            // Set parameters for query
            preparedStatement.setString(1, level.getLabel());
            preparedStatement.setString(2, level.getDescription());
            preparedStatement.setInt(3, level.getCostXp());
            preparedStatement.setInt(4, level.getRequiredXp());
            preparedStatement.setString(5, level.getSkin());

            // Execute the query
            preparedStatement.executeUpdate();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void updateLevel(Level level)
    {
        Connection connection = Database.connectDatabase();

        try
        {
            // Prepare an INSERT query
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE level SET label = ?, description = ?, cost_xp = ?, required_xp = ? WHERE id = ?");

            // Set parameters for query
            preparedStatement.setString(1, level.getLabel());
            preparedStatement.setString(2, level.getDescription());
            preparedStatement.setInt(3, level.getCostXp());
            preparedStatement.setInt(4, level.getRequiredXp());
            preparedStatement.setInt(5, level.getId());

            // Execute the query
            preparedStatement.executeUpdate();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void removeLevel(Level level)
    {
        Connection connection = Database.connectDatabase();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM level WHERE id = ?");
            preparedStatement.setInt(1, level.getId());

            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
