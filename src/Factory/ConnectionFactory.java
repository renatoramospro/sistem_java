package Factory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Computador
 */
public class ConnectionFactory {
     public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/sistemjava", "postgres","postgres");
        }
        catch(ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        catch(SQLException excecao) {
               throw new RuntimeException(excecao);
        }
     }
}
     

