package patientmanagement1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;


public class AddUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFname;
	private JTextField txtLname;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private ButtonGroup btn = new ButtonGroup();
	private String gender;
	public static String userRole;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUser frame = new AddUser(userRole);
					if(userRole == null || userRole.isEmpty()) {
					     new login().setVisible(true);
					     return;
					}
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
	public AddUser(String userRole) {
		this.userRole = userRole;
		DatabaseConnection connect = new DatabaseConnection();
		connect.getConnction();
		FormatDate   myFormatter = new FormatDate();
		setTitle("Add User");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First name");
		lblNewLabel.setBounds(24, 42, 89, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblSecondName = new JLabel("Last name");
		lblSecondName.setBounds(24, 79, 89, 22);
		contentPane.add(lblSecondName);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(24, 124, 89, 22);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(311, 124, 52, 22);
		contentPane.add(lblPassword);
		
		txtFname = new JTextField();
		txtFname.setBounds(130, 43, 129, 20);
		contentPane.add(txtFname);
		txtFname.setColumns(10);
		
		txtLname = new JTextField();
		txtLname.setColumns(10);
		txtLname.setBounds(130, 80, 129, 20);
		contentPane.add(txtLname);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(130, 125, 129, 20);
		contentPane.add(txtUsername);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(396, 125, 129, 20);
		contentPane.add(txtPassword);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(24, 171, 89, 22);
		contentPane.add(lblGender);
		
		JLabel lblDob = new JLabel("DOB");
		lblDob.setBounds(311, 46, 29, 22);
		contentPane.add(lblDob);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setBounds(311, 83, 29, 22);
		contentPane.add(lblRole);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(rdbtnMale.isSelected()==true) {
					gender = ("male");
				}
			}
		});
		rdbtnMale.setBounds(131, 167, 52, 23);
		contentPane.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Male");
		rdbtnFemale.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(rdbtnFemale.isSelected()==true) {
					gender = "Female";
				}
			}
		});
		rdbtnFemale.setBounds(195, 167, 52, 23);
		contentPane.add(rdbtnFemale);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(406, 48, 119, 20);
		contentPane.add(dateChooser);
		
		JComboBox txtRole = new JComboBox();
		txtRole.setModel(new DefaultComboBoxModel(new String[] {"user", "Admin"}));
		txtRole.setBounds(416, 83, 109, 22);
		contentPane.add(txtRole);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			//Date selecteDate = dateChooser.getModel().getValue();
			

			public void actionPerformed(ActionEvent e) {
				String fname = txtFname.getText();
				String lname = txtLname.getText();
				String username = txtUsername.getText();
				System.out.println(dateChooser);
				Date selectedDate = dateChooser.getDate(); // Get the date here
                String DOB = (selectedDate != null) ? myFormatter.format(selectedDate) : null;
				String role = txtRole.getSelectedItem().toString();
				String password = txtPassword.getText();
				
				System.out.println(DOB);
				String query = "insert into workers(fname ,lname, username, gender, DOB, role, password) values(?, ?, ?, ?, ?, ?, ?)";
				try(PreparedStatement pstm = connect.con.prepareStatement(query)){
					pstm.setString(1, fname);
					pstm.setString(2, lname);
					pstm.setString(3, username);
					pstm.setString(4, gender);
					pstm.setString(5, DOB);
					pstm.setString(6, role);
					pstm.setString(7, password);
					
					int insertion  = pstm.executeUpdate();
					if(insertion > 0) {
						JOptionPane.showMessageDialog(null, "New user created succesfuly");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "An error occured");
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(325, 228, 89, 23);
		contentPane.add(btnNewButton);
		btn.add(rdbtnFemale);
		btn.add(rdbtnMale);
		
		JButton btnNewButton_2 = new JButton("Logout");
		btnNewButton_2.setBounds(495, 0, 65, 23);
		contentPane.add(btnNewButton_2);
	}
}
