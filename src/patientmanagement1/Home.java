package patientmanagement1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtHomePage;
	public static String role;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home(role);
					if(role == null || role.isEmpty()) {
						
						new login().setVisible(true);
						//Home.this.dispose();
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
	public Home(String role) {
		this.role = role;
		UserInfo userinfo  = new UserInfo();
		System.out.println(role);
//		if(userinfo.getUserName()==null || userinfo.getUserName().isEmpty()) {
//			new login().setVisible(true);
//			this.dispose();
//			return;
//		}
		setTitle("Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Register Patient");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton.setForeground(new Color(0, 0, 255));
		btnNewButton.setBackground(new Color(128, 255, 128));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegisterPatient(role).setVisible(true);			
			}
		});
		btnNewButton.setBounds(31, 77, 172, 23);
		contentPane.add(btnNewButton);
		
		txtHomePage = new JTextField();
		txtHomePage.setBounds(280, 11, 86, 20);
		txtHomePage.setText("home page");
		contentPane.add(txtHomePage);
		txtHomePage.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Add user");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(role != "admin") {
					JOptionPane.showMessageDialog(null, "Action denied");
				}
			}
		});
		btnNewButton_1.setBounds(31, 43, 172, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Logout");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new login().setVisible(true);
				return;
			}
		});
		btnNewButton_2.setBounds(475, 0, 77, 23);
		contentPane.add(btnNewButton_2);
	}
}
