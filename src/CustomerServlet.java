

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
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nextURL = "/output.jsp";
		String lastName = request.getParameter("lastname");
		Connection con = cm.getConnection();
		/*
		String action = request.getParameter("action");
		if (!action.equals("label")) {
			String url = "/index.html"; 
		}*/
		
		String query = "SELECT FullName, Title, FirstName, LastName, StreetAddress, City, State,ZipCode, EmailAddress, Position, Company  FROM customers WHERE LastName = '"+ lastName + "'";
		try{
			
			stm = con.createStatement();
			rs = stm.executeQuery(query);
		
			while(rs.next()){
				
				String CustomerDetail = rs.getString("FullName") + rs.getString("Title") +rs.getString("FirstName") +  rs.getString("LastName") +rs.getString("StreetAddress") +
				 rs.getString("City") + rs.getString("State") + rs.getString("ZipCode") + rs.getString("EmailAddress") + rs.getString("Position") + rs.getString("Company");
				request.setAttribute("message", CustomerDetail);
			}
			
			getServletContext().getRequestDispatcher(nextURL).forward(request,response);
			
					
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
