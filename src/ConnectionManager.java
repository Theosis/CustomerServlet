import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

	Connection connection;
	
	public Connection getConnection(){
		
		try{
			Class.forName("com:mysql:jdbc:Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/Customer","root","password");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return connection;
	}
}
