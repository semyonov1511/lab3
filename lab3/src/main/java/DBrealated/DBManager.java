package DBrealated;

public class DBManager {
    SQLconnector connector = new SQLconnector();
    public void connect(){
        connector.setConnection();
    }
}
