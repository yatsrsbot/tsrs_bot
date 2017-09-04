package Utils;

        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.SQLException;

class DatabaseManager {
    private static Connection connection;

    private DatabaseManager(){}

        static Connection getConnection() {

        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                String dbUrl = System.getenv("JDBC_DATABASE_URL");
                connection = DriverManager.getConnection(dbUrl);

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;

    }
}

