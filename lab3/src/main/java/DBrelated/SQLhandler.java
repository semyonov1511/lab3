package DBrelated;

import ReactorsRelated.DBReactor;
import ReactorsRelated.Reactor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

public class SQLhandler {

    public ArrayList<DBReactor> readDataBase(ArrayList<Reactor> reactorTypes) {
        SQLconnector connector = new SQLconnector();
        connector.setConnection();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<DBReactor> reactors = new ArrayList<>();
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement("""
                    SELECT reactor.id, reactor_name, thermal_capacity, country_name, region_name, type_name, operator_name, owner_name, shutdown_date
                    FROM reactor
                    LEFT JOIN country ON country_id = country.id
                    LEFT JOIN region ON region_id = region.id
                    LEFT JOIN type ON type_id = type.id
                    LEFT JOIN operator ON operator_id = operator.id
                    LEFT JOIN owner ON owner_id = owner.id""");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DBReactor reactor = new DBReactor();
                setParameters(reactor, resultSet, reactorTypes, connection);
                reactors.add(reactor);
            }
            System.out.println("База данных прочитана");
        } catch (SQLException e) {
            System.out.println("Oшибка при чтении: " + e.getMessage());
        } finally {
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
            }
        }   
        return reactors;
    }

    private Map<Integer, Double> readLoadFactor(Connection connection, int reactor_id) {
        Map<Integer, Double> loadFactor = new HashMap<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT load_factor, year FROM loadfactor WHERE reactor_id = ?")) {
            preparedStatement.setInt(1, reactor_id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    loadFactor.put(resultSet.getInt("year"), resultSet.getDouble("load_factor"));
                }
            }
        } catch (SQLException e) {
        }

        return loadFactor;
    }

    private void setParameters(DBReactor reactor, ResultSet resultSet, ArrayList<Reactor> reactorTypes, Connection connection) throws SQLException {
        reactor.setName(resultSet.getString("reactor_name"));
        reactor.setCountry(resultSet.getString("country_name"));
        reactor.setRegion(resultSet.getString("region_name"));
        reactor.setOwner(resultSet.getString("owner_name"));
        reactor.setOperator(resultSet.getString("operator_name"));
        reactor.setThermalCapacity(resultSet.getInt("thermal_capacity"));
        reactor.setReactor(resultSet.getString("type_name"), reactorTypes);
        reactor.setShutdownYear(findYear(resultSet.getString("shutdown_date")));
        reactor.setLoadFactor(readLoadFactor(connection, resultSet.getInt("id")));
    }

    public void calculateFuelLoad(ArrayList<DBReactor> reactors) {
        reactors.forEach(reactor -> {
            IntStream.range(2014, 2025)
                    .forEach(year -> {
                        double fuelLoad = 0;
                        if (reactor.getLoadFactor().containsKey(year)) {
                            fuelLoad = reactor.getThermalCapacity() * reactor.getLoadFactor().get(year) / 100 / reactor.getReactor().getBurnup();
                        } else if (reactor.getShutdownYear() >= year) {
                            fuelLoad = reactor.getThermalCapacity() * 85 / 100 / reactor.getReactor().getBurnup();
                        }
                        reactor.getFuelLoad().put(year, fuelLoad);
                    });
        });
    }

    public Map<String, Map<Integer, Double>> link(ArrayList<DBReactor> reactors, Function<DBReactor, String> getter) {
        Map<String, Map<Integer, Double>> map = new HashMap<>();
        for (DBReactor reactor : reactors) {
            String key = getter.apply(reactor);
            if (map.containsKey(key)) {
                Map<Integer, Double> fuelLoad = map.get(key);
                for (int year = 2014; year < 2025; year++) {
                    fuelLoad.put(year, reactor.getFuelLoad().get(year) + fuelLoad.get(year));
                }
            } else {
                map.put(key, new HashMap<>());
                Map<Integer, Double> fuelLoad = map.get(key);
                fuelLoad.putAll(reactor.getFuelLoad());
            }
        }
        return map;
    }

    private int findYear(String date) {
        if (date != null) {
            int year = Integer.parseInt(date.substring(0, 4));
            return year;
        } else {
            return 2025;
        }
    }
}
