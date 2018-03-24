package employee.management;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrationScreen extends JFrame {

	private JFrame mainFrame;
	private JTextField unTextField;
	private JTextField fnTextField;
	private JTextField lnTextField;
	private JTextField addressTextField;
	private JTextField passwordTextField;

	/*
	 * creating the frame below
	 * in the constructor
	 */
	public RegistrationScreen() {
		try {
			mainFrame = new JFrame();
			mainFrame.setResizable(false);
			mainFrame.setBounds(100, 100, 450, 450);
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainFrame.getContentPane().setLayout(null);
			mainFrame.setVisible(true);

			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBounds(44, 66, 357, 333);
			mainFrame.getContentPane().add(panel);
			panel.setLayout(null);

			JLabel userNameLabel = new JLabel(" User Name");
			userNameLabel.setBounds(6, 16, 81, 16);
			panel.add(userNameLabel);

			unTextField = new JTextField();
			unTextField.setBounds(6, 33, 345, 26);
			panel.add(unTextField);
			unTextField.setColumns(10);

			JLabel firstNameLabel = new JLabel("First Name");
			firstNameLabel.setBounds(6, 71, 81, 16);
			panel.add(firstNameLabel);

			fnTextField = new JTextField();
			fnTextField.setBounds(6, 90, 345, 26);
			panel.add(fnTextField);
			fnTextField.setColumns(10);

			JLabel lastNameLabel = new JLabel("Last Name");
			lastNameLabel.setBounds(6, 128, 81, 16);
			panel.add(lastNameLabel);

			lnTextField = new JTextField();
			lnTextField.setBounds(6, 146, 345, 26);
			panel.add(lnTextField);
			lnTextField.setColumns(10);

			JLabel addressLabel = new JLabel("Address");
			addressLabel.setBounds(6, 184, 61, 16);
			panel.add(addressLabel);

			addressTextField = new JTextField();
			addressTextField.setBounds(6, 204, 345, 26);
			panel.add(addressTextField);
			addressTextField.setColumns(10);

			JLabel passwordLabel = new JLabel("Password");
			passwordLabel.setBounds(6, 242, 61, 16);
			panel.add(passwordLabel);

			passwordTextField = new JTextField();
			passwordTextField.setBounds(6, 262, 345, 26);
			panel.add(passwordTextField);
			passwordTextField.setColumns(10);

			JButton finishButton = new JButton("Finish");
			
			//adding an action listener to the finish button
			finishButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					mainFrame.dispose();
					
					//used to store the entered information
					String userName = unTextField.getText();
					String firstName = fnTextField.getText();
					String lastName = lnTextField.getText();
					String address = addressTextField.getText();
					String pwd = passwordTextField.getText();
					
					/*
					 * Opens up a connection to the database
					 * then the text fields are checked to validate that
					 * user entered their information. If the fields are left blank a
					 * error message pops up. if user user did enter information in all the fields
					 * then, that information is then inserted into the database table
					 * Connection to the database is then closed
					 */
					try {
						DBConnectivity con = new DBConnectivity();
						if (userName.equals("") || firstName.equals("") || lastName.equals("") || address.equals("")
								|| pwd.equals("")) {
							JOptionPane.showMessageDialog(null, "Missing a field entry", "Error",
									JOptionPane.ERROR_MESSAGE);
							PromptScreen tmp = new PromptScreen();
							tmp.setVisible(true);
						} else {
							con.insert(userName, firstName, lastName, address, pwd);
							PromptScreen tmp = new PromptScreen();
							tmp.setVisible(true);
							con.closeConnect();
							mainFrame.dispose();
						}

					} catch (Exception x) {
						x.printStackTrace();
					}

				}
			});

			finishButton.setBounds(114, 298, 117, 29);
			panel.add(finishButton);

			JPanel panel_1 = new JPanel();
			panel_1.setBackground(UIManager.getColor("Button.background"));
			panel_1.setBounds(44, 16, 357, 50);
			mainFrame.getContentPane().add(panel_1);
			panel_1.setLayout(null);

			JLabel companyLogoLabel = new JLabel("XYZ Company");
			companyLogoLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
			companyLogoLabel.setBounds(113, 6, 177, 36);
			panel_1.add(companyLogoLabel);

			JLabel backgroundImage = new JLabel("");
			backgroundImage.setBounds(0, 0, 450, 428);
			mainFrame.getContentPane().add(backgroundImage);
			//adding background image
			backgroundImage.setIcon(new ImageIcon("icon.jpg"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
