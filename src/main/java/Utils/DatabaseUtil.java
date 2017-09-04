package Utils;

import Enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DatabaseUtil {
    public static Map<String, Integer> getAuctionRecordsFromDatabase() {
        Map<String, Integer> resultSet = new HashMap<>();
        try {
            Statement stmt = DatabaseManager.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("Select servicename,order_value from auction_data");
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
            stmt.executeQuery("update auction_data set order_value = " + value + " where servicename = '" + auctionName + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAuctionRecordFromDataBase(String auctionName) {
        try {
            Statement stmt = DatabaseManager.getConnection().createStatement();
            stmt.executeQuery("delete from auction_data where servicename = '" + auctionName + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertAuctionRecordsIntoDatabase(String auctionName, Integer value) {
        try {
            Statement stmt = DatabaseManager.getConnection().createStatement();
            stmt.executeQuery("INSERT INTO Auction_data (Servicename,order_value) Values ('" + auctionName + "', " + value + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Map<Integer, Role> reloadUserRolesFromDatabase(){
        Map<Integer, Role> resultSet = new HashMap<>();
        try {
            Statement stmt = DatabaseManager.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("Select userid,available_role from users");
            while (rs.next()) {
                Integer userid = rs.getInt("userid");
                Role available_role = Role.valueOf(rs.getString("available_role"));
                resultSet.put(userid, available_role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;

    }
}