package com.bl.addressbookhdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddressBookJDBC {
	static String insertQuery = "insert into contact(firstName, lastName, address,"
			+ " city, state, zip, phoneNumber) values('Simran', 'Khan', 'Jhotwara','Jaipur','Rajasthan','302012','8890801074')";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println("Welcome to Address Book of MySQL");
		Scanner sc = new Scanner(System.in);
		int input = 0;
		try {
			// load driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded...");

			// make connection
			String DBName = "addressbook";
			String URL = "jdbc:mysql://localhost:3306/" + DBName;
			String dbUserName = "root";
			String dbPass = "1074";
			Connection con = DriverManager.getConnection(URL, dbUserName, dbPass);
			System.out.println("Connected with " + dbUserName);

			// create statement
			Statement stmt = con.createStatement();
			System.out.println("Statement Obj Created...");
			do {
				System.out.println("1. Insert Query\n 2. Select Query\n 3. Update query\n 4. Delete Query\n 5. Exit");

				System.out.println("Enter the operation you want to perform : ");
				input = sc.nextInt();

				switch (input) {
				case 1:
					// execute query
					int noOFRowsAffected = stmt.executeUpdate(insertQuery);
					if (noOFRowsAffected > 0)
						System.out.println("Record Inserted!");
					break;
				case 2:
					String selectQuery = "select * from contact";
					ResultSet rs = stmt.executeQuery(selectQuery);
					System.out.println(rs);
					System.out.println("Id | firstName | lastName | address | city | state | zip | phoneNumber");
					while (rs.next()) {
						System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | " + rs.getString(4) + " | " + rs.getString(5) + " | " + rs.getString(6) + " | " + rs.getString(7) + " | " + rs.getString(8) );
					}
					break;
				case 3:
					String updateQuery = "update name from contact where id=2";
					noOFRowsAffected = stmt.executeUpdate(updateQuery);
					System.out.println("Execute statement....");
					if(noOFRowsAffected > 0) {
						System.out.println("Record updated....");
					}
					
					break;
				case 4: 
					String deleteQuery = "delete from contact where id=1";
					noOFRowsAffected = stmt.executeUpdate(deleteQuery);
					System.out.println("Execute statement....");
					if(noOFRowsAffected > 0) {
						System.out.println("Record deleted....");
					}
					break;
				case 5: 
					System.out.println("Exited");
					break;
				default:
					System.out.println("Invalid Input");
					break;
				}

			} while (input != 5);

			// close connection
			stmt.close();
			con.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}