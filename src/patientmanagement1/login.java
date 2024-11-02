package patientmanagement1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JTextField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setTitle("Login");
		DatabaseConnection connect = new DatabaseConnection ();
		connect.getConnction();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 571, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		username = new JTextField();
		username.setBounds(142, 55, 176, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		pass = new JTextField();
		pass.setBounds(142, 122, 176, 20);
		contentPane.add(pass);
		pass.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(42, 55, 90, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(42, 125, 79, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 23));
		lblNewLabel_2.setForeground(new Color(0, 128, 192));
		lblNewLabel_2.setBounds(189, 11, 90, 33);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = username.getText();
				String password = pass.getText();
				try {
                    
                    String query = "SELECT * FROM workers WHERE username = ? and password = ?";//Database query for username and passoord
                    
					PreparedStatement psmt = connect.con.prepareStatement(query);//Executing recompiled SQL query
					
					psmt.setString(1, user);
					psmt.setString(2, password);
					
					ResultSet rs = psmt.executeQuery();
					if(rs.next()) {
				        UserInfo info = new UserInfo(); //creating object of user info class
				        info.setUser(user); //Setting username in setters method from userinfo class
				        //System.out.println(info.getUserName());
				        connect.con.close();
				       System.out.println(info.getRole());
						new Home().setVisible(true); //opening the home page frame
						login.this.dispose();
						
					} else {
						JOptionPane.showMessageDialog(null, "Username or password is incorrect"); //Display an error message for wrong credentials

					}
				} catch (SQLException ex) {
					ex.printStackTrace();//console error message
					JOptionPane.showMessageDialog(null, "An error occured"); //jreme error message
				} catch(Exception ex) {
					ex.printStackTrace();//console error message
					JOptionPane.showMessageDialog(null, "An error occured"); //jreme error message
				}
			}
		});
		btnNewButton.setForeground(new Color(0, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(245, 175, 89, 23);
		contentPane.add(btnNewButton);
	}
}
