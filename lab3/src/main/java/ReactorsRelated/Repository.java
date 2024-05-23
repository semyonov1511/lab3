package ReactorsRelated;

import java.util.ArrayList;

public class Repository {

    ArrayList<Reactor> list = new ArrayList<>();
    ArrayList<DBReactor> DBlist = new ArrayList<>();

    public void setList(ArrayList<Reactor> rlist) {
        list = rlist;
    }

    public ArrayList<Reactor> getList() {
        return list;
    }

    public void setDBList(ArrayList<DBReactor> rlist) {
        DBlist = rlist;
    }

    public ArrayList<DBReactor> getDBList() {
        return DBlist;
    }
}
