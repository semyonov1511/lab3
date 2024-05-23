package ReactorsRelated;

import java.util.ArrayList;

public class Repository {

    ArrayList<Reactor> list = new ArrayList<>();
    ArrayList<DBReactor> list = new ArrayList<>();

    public void setList(ArrayList<Reactor> rlist) {
        list = rlist;
    }

    public ArrayList<Reactor> getList() {
        return list;
    }
}
