
package DBrealated;

import java.sql.Connection;

public class DBManager {
    Connection connection = null;
    SQLconnector connector = new SQLconnector();
    public void setConnection(){
        this.connection = connector.getConnection();
    }
}
