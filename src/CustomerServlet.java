

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ConnectionManager cm = new ConnectionManager();
	Statement stm;
	ResultSet rs;
	
    public CustomerServlet() {
        super();
        
    }
    
    

	protected void doPost(HttpServletRequest lastName, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con = cm.getConnection();
		String query = "SELECT CustomerID, FirstName, LastName, StreetAddress, City, State FROM Customers WHERE LastName = '"+ lastName + "'";
		try{
			
			stm = con.createStatement();
			rs = stm.executeQuery(query);
			while(rs.next()){
				
				String customerID = rs.getString("CustomerID");
				String firstName = rs.getString("FirstName");
				//String lastName = rs.getString("LastName");
				String address = rs.getString("StreetAddress");
				String city = rs.getString("City");
				String state = rs.getString("State");
				
				System.out.println("CustomerNumber: "+customerID);
				System.out.println(firstName);
				System.out.println(address);
				System.out.println(city);
				System.out.println(state);
				
			}
			
					
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
