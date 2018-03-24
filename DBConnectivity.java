package employee.management;

import java.sql.*;

public class DBConnectivity {

	private Connection con = null;
	private final String jdbcDriver = "com.mysql.jdbc.Driver";
	private final String dbUrl = "jdbc:mysql://localhost/login";
	private final String user = "root";
	private final String pwd = "root";

	public DBConnectivity() {
		try {
			Class.forName(jdbcDriver);
			System.out.print(" Connecting to database");
			// opening a connection
			con = DriverManager.getConnection(dbUrl, user, pwd);
			System.out.print(" Connection succesful ");
		} catch (SQLException e) {
			// handle error for JDBC
			e.printStackTrace();
		} catch (Exception e) {
			// handle error for class.forName
			e.printStackTrace();
		}
	}
	
	/*
	 * The insert method inserts the the information gained from 
	 * the registration screen into the database
	 */
	public void insert(String userName, String firstName, String lastName, String address, String pwd) {
		//query syntax to insert the information into the database
		String query = " INSERT INTO employee (userName,firstName,lastName,address,password)" + " values (?,?,?,?,?)";

		try {
			//create a PreparedStatement to hold the query syntax
			PreparedStatement ps = con.prepareStatement(query);
			/*
			 * replacing the question marks from the query variable, with 
			 * the actual value i want to execute on the database.
			 */
			ps.setString(1, userName);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setString(4, address);
			ps.setString(5, pwd);
			//execute the query
			ps.execute();
			//close the PreparedStatement
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * The insertTimeSheet method inserts the clock-in time from the user
	 * into the database
	 */
	public void insertTimeSheet(String id, String clockin) {
		//query syntax to insert the information into the database
		String query = " INSERT INTO timesheet (id,clockin,hours)" + " values (?,?,?)";
		try {
			//create a PreparedStatement to hold the query syntax
			PreparedStatement ps = con.prepareStatement(query);
			/*
			 * replacing the question marks from the query variable, with 
			 * the actual value i want to execute on the database.
			 */
			ps.setString(1, id);
			ps.setString(2, clockin);
			ps.setInt(3, 0);
			//execute the query
			ps.execute();
			//close the PreparedStatement
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * The delete method deletes the user from the employee table table
	 */

	public void delete(String userName) {
		//query syntax to insert the information into the database
		String query = "DELETE FROM employee WHERE userName = ?";
		try {
			//create a PreparedStatement to hold the query syntax
			PreparedStatement ps = con.prepareStatement(query);
			/*
			 * replacing the question marks from the query variable, with 
			 * the actual value i want to execute on the database.
			 */
			ps.setString(1, userName);
			//execute the query
			ps.execute();
			//close the PreparedStatement
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * The deleteTimeSheet method deletes the user from the timesheet table
	 */
	public void deleteTimeSheet(String userName) {
		//query syntax to insert the information into the database
		String query = "DELETE FROM timesheet WHERE id = ?";
		try {
			//create a PreparedStatement to hold the query syntax
			PreparedStatement ps = con.prepareStatement(query);
			/*
			 * replacing the question marks from the query variable, with 
			 * the actual value i want to execute on the database.
			 */
			ps.setString(1, userName);
			//execute the query
			ps.execute();
			//close the PreparedStatement
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	 * The updateClock method updates the clock-out time from the user
	 * into the database
	 */

	public void updateClock(String clockout, String userName) {
		//query syntax to insert the information into the database
		String query = "UPDATE timesheet SET clockout= ? WHERE id = ?";
		try {
			//create a PreparedStatement to hold the query syntax
			PreparedStatement ps = con.prepareStatement(query);
			/*
			 * replacing the question marks from the query variable, with 
			 * the actual value i want to execute on the database.
			 */
			ps.setString(1, clockout);
			ps.setString(2, userName);
			//execute the query
			ps.execute();
			//close the PreparedStatement
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * The insertTimeSheet method inserts the clock-in time from the user
	 * into the database
	 */

	public void timeDiff(String id) {
		//query syntax to insert the information into the database
		String query = "UPDATE timesheet SET hours=(SELECT TIMEDIFF(clockout,clockin))  WHERE id = ?";

		try {
			//create a PreparedStatement to hold the query syntax
			PreparedStatement ps = con.prepareStatement(query);
			/*
			 * replacing the question marks from the query variable, with 
			 * the actual value i want to execute on the database.
			 */
			ps.setString(1, id);
			//execute the query
			ps.execute();
			//close the PreparedStatement
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//method to close the opened connection to the database
	public void closeConnect() {
		try {
			con.close();
			System.out.println("connection closed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * The loginCheck method checks to see if the user entered login
	 * info is in the database. If it is then the method returns true.
	 * If it is not, then the method returns false.
	 */
	public boolean loginCheck(String userName, String password) {
		//query syntax to insert the information into the database
		String query = "SELECT * FROM employee WHERE userName=? AND password=?";
		try {
			//create a PreparedStatement to hold the query syntax
			PreparedStatement ps = con.prepareStatement(query);
			/*
			 * replacing the question marks from the query variable, with 
			 * the actual value i want to execute on the database.
			 */
			ps.setString(1, userName);
			ps.setString(2, password);
			
			//create a ResultSet to hold the info from the executed query
			ResultSet rs = ps.executeQuery();
			
			//checking to see if the user information is within the database
			//returns true
			//returns false if the user name and password is not found
			if (rs.next()) {
				String storeUser, storePassword;
				storeUser = rs.getString("userName");
				storePassword = rs.getString("password");
				if (storeUser.equals(userName) && storePassword.equals(password)) {
					return true;
				}
			} else if (!rs.next()) {
				return false;
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

}
