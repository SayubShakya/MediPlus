package pkg1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String HOST = "localhost";
    private final int PORT = 3306;
    private final String DBNAME = "user";
    private final String USER = "root";
    private final String PASSWORD = "9828807288";
    private final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DBNAME;

    public Connection connect() {
        Connection con = null;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection successful!");
        } catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
        return con;
    }
}
