package model;

import entity.Reward;

import java.sql.*;
import java.util.ArrayList;

public class RewardDao {

    public static ArrayList<Reward> getRewards()
    {
        Connection connection = Database.connectDatabase();

        try
        {
            ArrayList<Reward> rewards = new ArrayList<Reward>();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM reward INNER JOIN level ON reward.id_level = level.id");

            while (resultSet.next())
            {
                rewards.add(new Reward(
                        resultSet.getInt("reward.id"),
                        resultSet.getString("reward.label"),
                        resultSet.getInt("cost_xp"),
                        resultSet.getInt("nb_available"),
                        resultSet.getString("description"),
                        resultSet.getInt("id_level"),
                        resultSet.getString("level.label"),
                        resultSet.getString("skin")
                ));
            }

            return rewards;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static void createReward(Reward reward)
    {
        Connection connection = Database.connectDatabase();

        try
        {
            // Prepare an INSERT query
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO reward (label, cost_xp, nb_available, description, id_level, skin) VALUES (?, ?, ?, ?, ?, ?)");

            // Set parameters for query
            preparedStatement.setString(1, reward.getLabel());
            preparedStatement.setInt(2, reward.getCostXp());
            preparedStatement.setInt(3, reward.getNbAvailable());
            preparedStatement.setString(4, reward.getDescription());
            preparedStatement.setInt(5, reward.getIdLevel());
            preparedStatement.setString(6, reward.getSkin());

            // Execute the query
            preparedStatement.executeUpdate();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void updateReward(Reward reward)
    {
        Connection connection = Database.connectDatabase();

        try
        {
            // Prepare an INSERT query
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE reward SET label = ?, cost_xp = ?, nb_available = ?, description = ?, id_level = ? WHERE id = ?");

            // Set parameters for query
            preparedStatement.setString(1, reward.getLabel());
            preparedStatement.setInt(2, reward.getCostXp());
            preparedStatement.setInt(3, reward.getNbAvailable());
            preparedStatement.setString(4, reward.getDescription());
            preparedStatement.setInt(5, reward.getIdLevel());
            preparedStatement.setInt(6, reward.getId());

            // Execute the query
            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void removeReward(Reward reward)
    {
        Connection connection = Database.connectDatabase();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM reward WHERE id = ?");
            preparedStatement.setInt(1, reward.getId());

            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

}
