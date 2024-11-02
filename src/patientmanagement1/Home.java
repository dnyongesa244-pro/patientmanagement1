package patientmanagement1;

import java.awt.EventQueue;

import javax.swing.JFrame;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		UserInfo userinfo  = new UserInfo();
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
				new RegisterPatient().setVisible(true);				
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
				new AddUser().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(31, 43, 172, 23);
		contentPane.add(btnNewButton_1);
	}
}
