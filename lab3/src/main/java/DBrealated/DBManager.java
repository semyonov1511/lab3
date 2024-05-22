
package DBrealated;

import java.sql.Connection;

public class DBManager {
    Connection connection = null;
    PostgreSQLConnectionExample connector = new PostgreSQLConnectionExample();
    public void setConnection(){
        this.connection = connector.getConnection();
    }
}
