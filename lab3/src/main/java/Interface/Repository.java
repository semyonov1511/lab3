
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
    public void setList(ArrayList<Reactor> rlist){
        list = rlist;
    }
    
    public ArrayList<Reactor> getList(){
        return list;
    }
}
