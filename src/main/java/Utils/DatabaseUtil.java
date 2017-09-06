package Utils;

import Enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseUtil {
    public static Map<String, Integer> getAuctionRecordsFromDatabase() {
        Map<String, Integer> resultSet = new HashMap<>();
        try {
            Statement stmt = DatabaseManager.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT servicename,order_value FROM auction_data");
            while (rs.next()) {
                String service = rs.getString("servicename");
                Integer order = rs.getInt("order_value");
                resultSet.put(service, order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void updateAuctionRecordsFromDatabase(String auctionName, Integer value) {
        try {
            Statement stmt = DatabaseManager.getConnection().createStatement();
            stmt.executeUpdate("update auction_data set order_value = " + value + " where servicename = '" + auctionName + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAuctionRecordFromDataBase(String auctionName) {
        try {
            Statement stmt = DatabaseManager.getConnection().createStatement();
            stmt.executeUpdate("delete from auction_data where servicename = '" + auctionName + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertAuctionRecordsIntoDatabase(String auctionName, Integer value) {
        try {
            Statement stmt = DatabaseManager.getConnection().createStatement();
            stmt.executeUpdate("INSERT INTO Auction_data (Servicename,order_value) Values ('" + auctionName + "', " + value + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, UserHolder.UserData> reloadUserRolesFromDatabase() {
        Map<Integer, UserHolder.UserData> resultSet = new HashMap<>();
        try {
            Statement stmt = DatabaseManager.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT userid,available_role,username FROM users");
            while (rs.next()) {
                Integer userid = rs.getInt("userid");
                Role available_role = Role.valueOf(rs.getString("available_role").toUpperCase());
                String userName = rs.getString("username");
                resultSet.put(userid, new UserHolder.UserData(available_role, userName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;

    }

    public static ArrayList<Integer> getAdminRecordsFromDatabase() {
        ArrayList<Integer> resultSet = new ArrayList<>();
        try {
            Statement stmt = DatabaseManager.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT userid FROM users WHERE available_role = 'ADMIN'");
            while (rs.next()) {
                Integer userId = rs.getInt("userid");
                resultSet.add(userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void insertUserRecordsIntoDatabase(Integer userId, String userName) {
        try {
            Statement stmt = DatabaseManager.getConnection().createStatement();
            stmt.executeUpdate("INSERT INTO users (userid,available_role,username) VALUES ('" + userId + "', 'DEFAULT','" + userName + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUserRecordFromDataBase(String userName) {
        try {
            Statement stmt = DatabaseManager.getConnection().createStatement();
            stmt.executeUpdate("delete from users where username = '" + userName + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUserRecordsFromDatabase(String userName, Integer userId) {
        try {
            Statement stmt = DatabaseManager.getConnection().createStatement();
            stmt.executeUpdate("update users set available_role = 'ADMIN' where userid = '" + userId + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
