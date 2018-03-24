package employee.management;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PromptScreen extends JFrame {

	private JPanel contentPane;
	private JTextField userTextField;
	private JPasswordField passwordTextField;

	/**
	 * Create the frame
	 * below
	 */
	public PromptScreen() {
		try {
			setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 382, 288);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			JPanel mainLabel = new JPanel();
			mainLabel.setBackground(Color.WHITE);
			mainLabel.setBounds(56, 25, 260, 195);
			contentPane.add(mainLabel);
			mainLabel.setLayout(null);

			JLabel welcomeLabel = new JLabel("Welcome");
			welcomeLabel.setBounds(100, 6, 61, 16);
			mainLabel.add(welcomeLabel);

			JSeparator separator = new JSeparator();
			separator.setBounds(126, 27, 1, 12);
			mainLabel.add(separator);

			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(6, 27, 248, 12);
			mainLabel.add(separator_1);

			JLabel userLabel = new JLabel("User");
			userLabel.setBounds(6, 51, 61, 16);
			mainLabel.add(userLabel);

			userTextField = new JTextField();
			userTextField.setBounds(99, 46, 130, 26);
			mainLabel.add(userTextField);
			userTextField.setColumns(10);

			JLabel passwordLabel = new JLabel("Password");
			passwordLabel.setBounds(6, 98, 61, 16);
			mainLabel.add(passwordLabel);

			passwordTextField = new JPasswordField();
			passwordTextField.setBounds(100, 93, 129, 26);
			mainLabel.add(passwordTextField);

			JSeparator separator_2 = new JSeparator();
			separator_2.setBounds(6, 131, 248, 12);
			mainLabel.add(separator_2);

			JButton loginbtn = new JButton("Login");
			
			//adding action listener to the login button
			loginbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String userName = userTextField.getText();
						String password = String.valueOf(passwordTextField.getPassword());
						
						//checking to see if nothing was entered
						if (userName.equals("") || password.equals("")) {
							try {
								JOptionPane.showMessageDialog(null, "Missing a field entry", "error",
										JOptionPane.ERROR_MESSAGE);
							} catch (Exception h) {
								h.printStackTrace();
							}
						/*
						 * Opens up a connection to the database
						 * if the user enters their login information. That information is checked against the 
						 * database to validate the information. If their login information are in the database
						 * then the user is logged in, else a error message pops up.
						 * once they are logged in the clock in screen pops by
						 * creating an object of the class
						 * Connection to the database is then closed
						 */
						} else {
							DBConnectivity tmp = new DBConnectivity();
							if(tmp.loginCheck(userName, password)) {
								ClockInScreen clockIn = new ClockInScreen();
								clockIn.setTmpUser(userName);
								clockIn.setVisible(true);
								tmp.closeConnect();
								dispose();
							}else {
								JOptionPane.showMessageDialog(null, "Login info was Invalid", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					} catch (Exception z) {
						z.printStackTrace();
					}

				}
			});
			loginbtn.setBounds(6, 155, 69, 29);
			mainLabel.add(loginbtn);

			JButton resetbtn = new JButton("Reset");
			resetbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					passwordTextField.setText(null);
					userTextField.setText(null);
				}
			});
			resetbtn.setBounds(88, 155, 77, 29);
			mainLabel.add(resetbtn);

			JButton registerbtn = new JButton("Register");
			
			//adding action listener to the register button
			registerbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//brings up the registration screen by creating an object of the class
					try {
						RegistrationScreen tmp = new RegistrationScreen();
						dispose();
					} catch (Exception w) {
						w.printStackTrace();
					}

				}
			});
			registerbtn.setBounds(177, 155, 77, 29);
			mainLabel.add(registerbtn);

			JLabel backGroundImage = new JLabel("");
			backGroundImage.setBounds(0, 0, 382, 266);
			//adding the background image to the label
			backGroundImage.setIcon(new ImageIcon("icon.jpg"));
			contentPane.add(backGroundImage);
			validate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
