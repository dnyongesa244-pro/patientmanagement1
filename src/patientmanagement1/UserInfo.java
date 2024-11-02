package patientmanagement1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfo {
    private String userName;
    private String role;
    private DatabaseConnection connect;

    public UserInfo() {
        connect = new DatabaseConnection();
        connect.getConnction(); // Ensure this method is available in your DatabaseConnection class
    }

    public void setUser(String userName) {
        this.userName = userName;
    }
    
    public void setRole() {
        String query = "SELECT role FROM workers WHERE username = ?";
        try (PreparedStatement pstm = connect.con.prepareStatement(query)) {
            pstm.setString(1, userName);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                role = rs.getString("role");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public String getRole() {
        setRole(); // Call to set the role
        return role; // Return the current role
    }
    
    public String getUserName() {
        return userName; // Return the username
    }
}

