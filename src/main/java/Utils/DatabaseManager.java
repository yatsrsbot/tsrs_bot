package Utils;

        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.SQLException;
        import java.util.Properties;

class DatabaseManager {
    private static Connection connection;

    private DatabaseManager(){}

        static Connection getConnection() {

        if (connection == null) {
            try {
//                Class.forName("org.postgresql.Driver");
//                String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
//                Properties props = new Properties();
//                props.setProperty("user","postgres");
//                props.setProperty("password","root");
                String dbUrl = System.getenv("JDBC_DATABASE_URL");
                connection = DriverManager.getConnection(dbUrl,props);

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;

    }
}

