package patientmanagement1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

public class RegisterPatient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtfname;
	private JTextField txtlname;
	private ButtonGroup btn = new ButtonGroup();
	private String txtGender;
	public static String role;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterPatient frame = new RegisterPatient(role);
					if(role == null || role.isEmpty()) {
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
	public RegisterPatient(String role) {
		this.role = role;
		UserInfo userinfo = new UserInfo();
//		if(userinfo.getUserName()==null || userinfo.getUserName().isEmpty()) {
//			new login().setVisible(true);
//			this.dispose();
//			return;
//
		DatabaseConnection connect = new DatabaseConnection();
		connect.getConnction();
		FormatDate formatter = new FormatDate();
		setTitle("Patient registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 362);
		JPanel contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(53, 34, 73, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(53, 68, 73, 14);
		contentPane.add(lblLastName);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(53, 106, 46, 14);
		contentPane.add(lblAge);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(53, 145, 46, 14);
		contentPane.add(lblGender);
		
		JLabel lblDeptment = new JLabel("Deptment");
		lblDeptment.setBounds(53, 186, 73, 14);
		contentPane.add(lblDeptment);
		
		txtfname = new JTextField();
		txtfname.setBounds(147, 31, 128, 20);
		contentPane.add(txtfname);
		txtfname.setColumns(10);
		
		txtlname = new JTextField();
		txtlname.setColumns(10);
		txtlname.setBounds(147, 65, 128, 20);
		contentPane.add(txtlname);
		
		
		
		JRadioButton btnMale = new JRadioButton("Male");
		btnMale.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(btnMale.isSelected() == true) {
					txtGender = "Male";
				}
			}
		});
		btnMale.setBounds(166, 145, 52, 23);
		contentPane.add(btnMale);
		
		JRadioButton btnFemale = new JRadioButton("Female");
		btnFemale.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(btnFemale.isSelected() == true) {
					txtGender = "Female";
				}
			}
		});
		btnFemale.setBounds(236, 145, 109, 23);
		contentPane.add(btnFemale);
		btn.add(btnFemale);
		btn.add(btnMale);
		
		JComboBox txtDept = new JComboBox();
		txtDept.setModel(new DefaultComboBoxModel(new String[] {"Dentale", "eye", "Orthopaedic", "Xray", "Ultrasound", "martarnity"}));
		txtDept.setBounds(147, 182, 128, 22);
		contentPane.add(txtDept);
		
		JLabel lblNewLabel_1 = new JLabel("Type");
		lblNewLabel_1.setBounds(53, 228, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(136, 106, 139, 20);
		contentPane.add(dateChooser);
		
		JComboBox txtType = new JComboBox();
		txtType.setModel(new DefaultComboBoxModel(new String[] {"Outpatient", "Inpatient"}));
		txtType.setBounds(147, 224, 128, 22);
		contentPane.add(txtType);
		
		JButton registerBtn = new JButton("Register new");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fname = txtfname.getText();
				String lname = txtlname.getText();
				String gender = txtGender;
				String type = txtType.getSelectedItem().toString();
				Date selectedDate = dateChooser.getDate();
				String DOB = (selectedDate != null) ? formatter.format(selectedDate): null;
				//String type = txtType.getSelectedItem().toString();
				String dept = txtDept.getSelectedItem().toString();
				String query = "insert into patient1(fname, lname, DOB, gender, type, dept) values(?, ?, ?, ?, ?,?)";
				try(PreparedStatement psmt = connect.con.prepareStatement(query)){
					psmt.setString(1, fname);
					psmt.setString(2, lname);
					psmt.setString(3, DOB);
					psmt.setString(4, gender);
					psmt.setString(5,dept);
					psmt.setString(6,type);
					int inserted = psmt.executeUpdate();
					if(inserted > 0) {
						System.out.println("Data inserted succesfuly");
						JOptionPane.showMessageDialog(null, "Data inserted succesfully");
						txtfname.setText("");
						txtlname.setText("");
						txtGender = "";
						btnFemale.setSelected(false);
						btnMale.setSelected(false);
						txtType.setSelectedIndex(1);
						txtDept.setSelectedIndex(1);
					} else {
						JOptionPane.showMessageDialog(null, "An error ocureed");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "An error ocureed");
				} finally{
					try {
						connect.con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		});
		registerBtn.setForeground(new Color(0, 0, 255));
		registerBtn.setFont(new Font("Tahoma", Font.BOLD, 17));
		registerBtn.setBackground(new Color(128, 255, 128));
		registerBtn.setBounds(198, 274, 175, 23);
		contentPane.add(registerBtn);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(471, 68, 7, 20);
		contentPane.add(textPane);
		
		JButton btnNewButton = new JButton("Home");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterPatient.this.dispose();
			}
		});
		btnNewButton.setBounds(0, 0, 59, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Logout");
		btnNewButton_2.setBounds(475, 0, 65, 23);
		contentPane.add(btnNewButton_2);
	}
}
