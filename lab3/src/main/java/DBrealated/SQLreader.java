package DBrealated;

import ReactorsRelated.DBReactor;
import ReactorsRelated.Reactor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SQLreader {

    public ArrayList<Reactor> readDataBase(ArrayList<Reactor> reactorTypes) {
        SQLconnector connector = new SQLconnector();
        connector.setConnection();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        connector.setConnection();
        ArrayList<Reactor> reactors = new ArrayList<>();
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement("""
                    SELECT reactor.id, reactor_name, thermal_capacity, country_name, region_name, type_name, operator_name, owner_name
                    FROM reactor
                    LEFT JOIN country ON country_id = country.id
                    LEFT JOIN region ON region_id = region.id
                    LEFT JOIN type ON type_id = type.id
                    LEFT JOIN operator ON operator_id = operator.id
                    LEFT JOIN owner ON owner_id = owner.id""");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DBReactor reactor = new DBReactor();
                reactor.setName(resultSet.getString("reactor_name"));
                reactor.setCountry(resultSet.getString("country_name"));
                reactor.setRegion(resultSet.getString("region_name"));
                reactor.setOwner(resultSet.getString("owner_name"));
                reactor.setOperator(resultSet.getString("operator_name"));
                reactor.setThermalCapacity(resultSet.getInt("thermal_capacity"));
                reactor.setReactor(resultSet.getString("type_name"), reactorTypes);
                reactor.setLoadFactor(readLoadFactor(connection, resultSet.getInt("id")));
                System.out.println(resultSet.getInt("id") + ". " + reactor.getName() + ": " + reactor.getReactor().getBurnup());
            }
            System.out.println("БД успешно прочитана");
        } catch (SQLException e) {
            System.out.println("Oшибка при чтении из БД " + e.getMessage());
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println("Oшибка при закрытии " + e.getMessage());
            }
        }
        return reactors;
    }

    private Map<Integer, Double> readLoadFactor(Connection connection, int reactor_id) {
        Map<Integer, Double> loadFactor = new HashMap<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT load_factor, year FROM load_factor WHERE reactor_id = ?")) {
            preparedStatement.setInt(1, reactor_id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    loadFactor.put(resultSet.getInt("year"), resultSet.getDouble("load_factor"));
                }
                System.out.println("База данных успешно прочитана");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при чтении базы данных: " + e.getMessage());
        }

        return loadFactor;
    }
}
