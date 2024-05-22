package DBrealated;

import ReactorsRelated.Reactor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLreader {
    public ArrayList<Reactor> readDataBase(ArrayList<Reactor> reactorTypes) {
        SQLconnector connector = new SQLconnector();
        ArrayList<Reactor> reactors = new ArrayList<>();
        try (Connection connection = connector.getConnection();PreparedStatement preparedStatement = connection.prepareStatement("""
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
