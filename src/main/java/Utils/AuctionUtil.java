package Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

class AuctionUtil {
    static Map<String, Integer> getAuctionRecordsFromDatabase() {
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
}
