package Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class AuctionUtil {
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
            auctionName = CommonsUtil.capitalize(auctionName); //capitalize
        try {
            Statement stmt = DatabaseManager.getConnection().createStatement();
            stmt.executeQuery("INSERT INTO Auction_data (Servicename,order_value) Values ('" + auctionName + "', " + value + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
