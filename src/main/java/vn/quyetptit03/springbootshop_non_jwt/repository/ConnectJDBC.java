package vn.quyetptit03.springbootshop_non_jwt.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectJDBC {
    private String DB_URL ="jdbc:mysql://localhost:3306/estatebasic?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private String DB_USER ="root";
    private String DB_PASS ="@Quyet180903";

    public Connection connection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
            System.out.println("Connected to database successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}

