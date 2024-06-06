package DBrelated;

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
            String url = "jdbc:postgresql://aws-0-eu-central-1.pooler.supabase.com:6543/postgres";
            String user = "postgres.edqvorgrdblzanmnbhqs";
            String password = "DebilDebil228_debil";
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
