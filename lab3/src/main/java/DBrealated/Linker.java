package DBrealated;

import ReactorsRelated.DBReactor;
import ReactorsRelated.Reactor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Linker {

    Map<String, Map<Integer, Double>> link(ArrayList<DBReactor> reactors) {
        Map<String, Map<Integer, Double>> map = new HashMap<>();
        for (DBReactor reactor : reactors) {
            if (!map.containsKey(reactor.getOperator())) {
                map.put(reactor.getOperator(), new HashMap<>());
            }
            Map<Integer, Double> fuelLoad = map.get(reactor.getOperator());
            for (int year = 2014; year <= 2024; year++) {
                double currentLoad = fuelLoad.getOrDefault(year, 0.0);
                double newLoad = reactor.getFuelLoad().get(year);
                fuelLoad.put(year, currentLoad + newLoad);
            }
        }
        return map;
    }
}
