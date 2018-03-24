package employee.management;

public class Driver {
	public static void main(String[] args) {
		 //DBConnectivity test = new DBConnectivity();
		// test.deleteTimeSheet("oj");
		// remember to close the connection after each open
		try {
			PromptScreen frame = new PromptScreen();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
