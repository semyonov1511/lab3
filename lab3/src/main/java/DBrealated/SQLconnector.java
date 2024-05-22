package DBrealated;

import ReactorsRelated.Reactor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLconnector {

    Connection connection;

    public void setConnection() {
        this.connection = connectToDatabase();
    }

    public Connection connectToDatabase() {
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

    public ArrayList<Reactor> readDataBase(ArrayList<Reactor> reactorTypes) {
        ArrayList<Reactor> reactors = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("""
                    SELECT reactor.id, reactor_name, thermal_capacity, country_name, region_name, type_name, operator_name, owner_name
                    FROM reactor
                    LEFT JOIN country ON country_id = country.id
                    LEFT JOIN region ON region_id = region.id
                    LEFT JOIN type ON type_id = type.id
                    LEFT JOIN operator ON operator_id = operator.id
                    LEFT JOIN owner ON owner_id = owner.id"""); 
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
            }
            System.out.println("БД успешно прочитана");
        } catch (SQLException e) {
            System.out.println("Oшибка при чтении из БД " + e.getMessage());
        }
        return reactors;
    }
}
