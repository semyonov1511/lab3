package DBrealated;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLconnector {

    public Connection getConnection() {
        Connection connection = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String user = "postgres";
            String password = "1559";
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Успешное подключение к базе данных PostgreSQL");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к базе данных: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Ошибка при закрытии соединения: " + e.getMessage());
            }
        }
        return connection;
    }
}
