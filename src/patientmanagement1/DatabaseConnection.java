package patientmanagement1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	Connection con;
      public void getConnction() {
    	  try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","Pro@2005;");
			System.out.println("Database connected succesfully");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
      }
}
