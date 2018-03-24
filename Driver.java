package employee.management;

public class Driver {
	public static void main(String[] args) {
		try {
			PromptScreen frame = new PromptScreen();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
