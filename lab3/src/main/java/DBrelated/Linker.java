package DBrelated;

import ReactorsRelated.DBReactor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Linker {

    <T> Map<String, Map<Integer, Double>> link(ArrayList<DBReactor> reactors, Function<DBReactor, T> getter) {
        Map<String, Map<Integer, Double>> map = new HashMap<>();
        for (DBReactor reactor : reactors) {
            T key = getter.apply(reactor);
            if (map.containsKey(key.toString())) {
                Map<Integer, Double> fuelLoad = map.get(key.toString());
                for (int year = 2014; year <= 2024; year++) {
                    fuelLoad.put(year, reactor.getFuelLoad().get(year) + fuelLoad.get(year));
                }
            } else {
                map.put(key.toString(), new HashMap<>());
                Map<Integer, Double> load = map.get(key.toString());
                load.putAll(reactor.getFuelLoad());
            }
        }
        return map;
    }
}
