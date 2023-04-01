package com.example.alarm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MonthlySubscriptionDAO {

    private Connection connection;

    public MonthlySubscriptionDAO() throws SQLException, ClassNotFoundException {
        // Initialize the database connection
        connection =new Database_connection().conn;
    }

    public List<MonthlySubscription> getMonthlySubscriptionsByMonth(String month) {
        List<MonthlySubscription> subscriptions = new ArrayList<>();
        String query = "SELECT * FROM monthly_subscription WHERE LOWER(delivered_till) LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, month + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1)+  "  " +resultSet.getString(6));
                MonthlySubscription subscription = new MonthlySubscription(
                        resultSet.getString("idmonthly_subscription"),
                        resultSet.getString("User_name"),
                        resultSet.getString("Order_memo"),
                        resultSet.getString("Delivery_address"),
                        resultSet.getString("Phone_no"),
                        resultSet.getString("Total_Cost_Monthly"),
                        resultSet.getString("Delivered_till")

                );

                subscriptions.add(subscription);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscriptions;
    }

    public boolean updateMonthlySubscription(MonthlySubscription subscription) {
        String query = "UPDATE monthly_subscription SET Delivered_till = ? WHERE User_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, subscription.getDeliveredTill());
            statement.setString(2, subscription.getUserName());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<MonthlySubscription> getAllSubscriptions() {
        List<MonthlySubscription> subscriptions = new ArrayList<>();
        try (Connection connection =new Database_connection().conn;
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM monthly_subscription")) {
            while (resultSet.next()) {
                String id = resultSet.getString("idmonthly_subscription");
                String userName = resultSet.getString("User_name");
                String orderMemo = resultSet.getString("Order_memo");
                String deliveryAddress = resultSet.getString("Delivery_address");
                String phoneNo = resultSet.getString("Phone_no");
                String totalCostMonthly = resultSet.getString("Total_Cost_Monthly");
                String deliveredTill = resultSet.getString("Delivered_till");
                //System.out.println(id+ userName+totalCostMonthly+deliveredTill);
                MonthlySubscription subscription = new MonthlySubscription(id, userName, orderMemo, deliveryAddress, phoneNo, totalCostMonthly, deliveredTill);
                subscriptions.add(subscription);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return subscriptions;
    }

}
