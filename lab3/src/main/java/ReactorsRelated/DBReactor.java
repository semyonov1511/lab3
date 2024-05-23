
package ReactorsRelated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBReactor {
    private String name;
    private Reactor reactor;
    private String country;
    private String operator;
    private String owner;
    private int thermalCapacity;
    private String region;
    private Map<Integer, Double> loadFactor;
    private Map<Integer, Double> fuelLoad = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReactor(String type_name, ArrayList<Reactor> reactorTypes) {
        chooseType(type_name, reactorTypes);
    }
    public Reactor getReactor(){
        return this.reactor;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getThermalCapacity() {
        return thermalCapacity;
    }

    public void setThermalCapacity(int thermalCapacity) {
        this.thermalCapacity = thermalCapacity;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Map<Integer, Double> getLoadFactor() {
        return loadFactor;
    }

    public void setLoadFactor(Map<Integer, Double> loadFactor) {
        this.loadFactor = loadFactor;
    }

    public Map<Integer, Double> getFuelLoad() {
        return fuelLoad;
    }

    public void setFuelLoad(Map<Integer, Double> fuelLoad) {
        this.fuelLoad = fuelLoad;
    }

    private void chooseType(String type_name, ArrayList<Reactor> reactors) {
        switch (type_name) {
            case "PWR" ->
                findType(reactors, "PWR");
            case "PHWR" ->
                findType(reactors, "PHWR");
            case "BWR" ->
                findType(reactors, "BWR");
            case "LWGR" ->
                findType(reactors, "RBMK");
            case "GCR" ->
                findType(reactors, "MAGNOX");
            case "FBR" ->
                findType(reactors, "BN");
            case "RBMK" ->
                findType(reactors, "RBMK");
            case "VVER" ->
                findType(reactors, "VVER_1200");
        }
    }

    private void findType(ArrayList<Reactor> reactors, String type) {
        for (Reactor reactor : reactors) {
            if (type.equals(reactor.getType())) {
                this.reactor = reactor;
                break;
            }
        }
    }
}
