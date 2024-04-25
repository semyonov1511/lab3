package Interface;

import java.util.ArrayList;

public class Repository {

    ArrayList<Reactor> list = new ArrayList<>();

    private static Repository INSTANCE;

    public static Repository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Repository();
        }
        return INSTANCE;
    }

    public void setList(ArrayList<Reactor> rlist) {
        list = rlist;
    }

    public ArrayList<Reactor> getList() {
        return list;
    }

    public Object Returner(int i, int j) {
        switch (i) {
            case 0 -> {
                return "Type of file - " + list.get(j).getFiletype();
            }
            case 1 -> {
                return "Class - " +list.get(j).getsetClass();
            }
            case 2 -> {
                return "Burnup - " +list.get(j).getBurnup();
            }
            case 3 -> {
                return "KPD - " +list.get(j).getKPD();
            }
            case 4 -> {
                return "Enrichment   - " +list.get(j).getEnrichment();
            }
            case 5 -> {
                return "Termal capacity - " +list.get(j).getTCapacity();
            }
            case 6 -> {
                return "Electrical capacity - " +list.get(j).getECapacity();
            }
            case 7 -> {
                return "Life time - " +list.get(j).getLifetime();
            }
            case 8 -> {
                return "First load - " +list.get(j).getFirstload();
            }
            default -> {
                return null;
            }
        }
    }
}

