package DBrelated;

import ReactorsRelated.Reactor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLconnector {

    Connection connection;

    public void setConnection() {
        this.connection = connectToDatabase();
    }
    public Connection getConnection(){
        return this.connection;
    }

    public Connection connectToDatabase() {
        Connection connection = null;
        try {
            String url = "jdbc:postgresql://10.0.4.197:5432/postgres";
            String user = "postgres";
            String password = "1559";
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Успешное подключение к базе данных PostgreSQL");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к базе данных: " + e.getMessage());
        }
        return connection;
    }
}
