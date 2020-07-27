package model;

import entity.Purchase;

import java.sql.*;
import java.util.ArrayList;

public class PurchaseDao {

    public static ArrayList<Purchase> getPurchases()
    {
        Connection connection = Database.connectDatabase();

        try
        {
            ArrayList<Purchase> purchases = new ArrayList<Purchase>();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT r.id AS rewardid, r.label AS rewardname, r.cost_xp AS rewardprice, r.nb_available AS rewardavailable, " +
                    "u.id AS userid, u.name AS username, u.firstname AS userfirstname, u.xp AS userxp, " +
                    "lru.date_validation AS validationdate, lru.date_used AS usedate, lru.date_asked AS requestdate FROM `reward` AS r " +
                    "INNER JOIN `link_reward_user` AS lru ON lru.id_reward = r.id " +
                    "INNER JOIN `user` AS u ON u.id = lru.id_user");

            while (resultSet.next())
            {
                purchases.add(new Purchase(
                        resultSet.getInt("rewardid"),
                        resultSet.getString("rewardname"),
                        resultSet.getInt("rewardprice"),
                        resultSet.getInt("rewardavailable"),
                        resultSet.getInt("userid"),
                        resultSet.getString("username"),
                        resultSet.getString("userfirstname"),
                        resultSet.getInt("userxp"),
                        resultSet.getTimestamp("requestdate"),
                        resultSet.getTimestamp("validationdate"),
                        resultSet.getTimestamp("usedate")
                ));
            }

            return purchases;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static void updatePurchase(Purchase purchase)
    {
        Connection connection = Database.connectDatabase();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE link_reward_user AS lru " +
                    "INNER JOIN user AS u ON u.id = lru.id_user " +
                    "SET lru.date_validation = ?, u.xp = ? WHERE id_reward = ? AND id_user = ?");

            preparedStatement.setTimestamp(1, purchase.getValidationDate());
            preparedStatement.setInt(2, purchase.getUserPoints());
            preparedStatement.setInt(3, purchase.getRewardId());
            preparedStatement.setInt(4, purchase.getUserId());

            // Execute the query
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
