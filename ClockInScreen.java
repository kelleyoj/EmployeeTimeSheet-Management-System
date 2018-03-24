package employee.management;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;


public class ClockInScreen extends JFrame {

	private JPanel contentPane;
	private JLabel timeTextfield;
	//used to store the user name form the login screen
	private static String tmpUser;

	public String getTmpUser() {
		return tmpUser;
	}

	public void setTmpUser(String tmpUser) {
		ClockInScreen.tmpUser = tmpUser;
	}

	/**
	 * Create the frame.
	 */
	public ClockInScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 266, 178);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton clockIn = new JButton("Clock In");
		
		//adding an action listener to the clock in button
		clockIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*
				 *Opens a connection to the database.
				 *Gets the current date and time using LocalDateTime and DateTimeFormatter
				 *for the format. The current date is then inserted into the database using
				 *the insertTimeSheet method from the DBConnectivity class.
				 *connection is then closed
				 */
				
				DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
				LocalDateTime localTime = LocalDateTime.now();
				JOptionPane.showMessageDialog(null, "You have successfully\nclocked IN.", "Message",
						JOptionPane.INFORMATION_MESSAGE);
				try {
					DBConnectivity con = new DBConnectivity();
					con.insertTimeSheet(getTmpUser(), dateTime.format(localTime));
					dispose(); //closing the frame
					PromptScreen tmp = new PromptScreen(); //popping up the login screen
					tmp.setVisible(true);
					con.closeConnect();
				} catch (Exception w) {
					w.printStackTrace();
				}

			}
		});
		clockIn.setBounds(6, 73, 117, 29);
		contentPane.add(clockIn);

		JButton clockOut = new JButton("Clock Out");
		
		//adding an action listener to the clock out button
		clockOut.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				/*
				 *Opens a connection to the database.
				 *Gets the current date and time using LocalDateTime and DateTimeFormatter
				 *for the format. The current date is then used to update into the database using
				 *the "updateClock" method from the DBConnectivity class.
				 *The "timeDiff" method from the DBConnectivity class is then used to update the 
				 *hour column in the database with the difference from the clock-in time and clock-out time
				 *connection is then closed
				 */
				
				DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
				LocalDateTime localTime = LocalDateTime.now();
				JOptionPane.showMessageDialog(null, "You have successfully\nclocked OFF.", "Message",
						JOptionPane.INFORMATION_MESSAGE);
				try {
					DBConnectivity con = new DBConnectivity();
					con.updateClock(dateTime.format(localTime), getTmpUser());
					con.timeDiff(getTmpUser());
					con.closeConnect();
				} catch (Exception w) {
					w.printStackTrace();
				}
				dispose();
				PromptScreen tmp = new PromptScreen();
				tmp.setVisible(true);
			}
		});
		clockOut.setBounds(143, 73, 117, 29);
		contentPane.add(clockOut);

		timeTextfield = new JLabel();
		timeTextfield.setBounds(130, 19, 130, 26);
		contentPane.add(timeTextfield);
		DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("hh:mm a");
		LocalDateTime localTime = LocalDateTime.now();
		timeTextfield.setText(dateTime.format(localTime));

		JLabel currentTimeLabel = new JLabel("Current Time is:");
		currentTimeLabel.setBounds(20, 24, 103, 16);
		contentPane.add(currentTimeLabel);

		JLabel backgroundImage = new JLabel("");
		//adding the background image to the label
		backgroundImage.setIcon(new ImageIcon("icon.jpg"));
		backgroundImage.setBounds(0, 0, 266, 156);
		contentPane.add(backgroundImage);
	}
}
