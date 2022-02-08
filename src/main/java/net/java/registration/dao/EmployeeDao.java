package net.java.registration.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.java.registration.model.Employee;
import util.DatabaseConnection;

public class EmployeeDao implements EmployeeDaoImpl {

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public void registerEmployee(Employee employee) throws Exception {
		try {
			connection = DatabaseConnection.createConnection();
			String insertQuery = "INSERT INTO employee" +
					" (first_name, last_name, username, password, address, contact) VALUES " +
					" (?, ?, ?, ?, ?, ?);";
			preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getUsername());
			preparedStatement.setString(4, employee.getPassword());
			preparedStatement.setString(5, employee.getAddress());
			preparedStatement.setString(6, employee.getContact());
			preparedStatement.executeUpdate(); // Execute the query or update query
		} catch (Exception e) { // Process exception
			System.out.print(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.print(e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.print(e);
				}
			}
		}
	}

	@Override
	public Employee getEmployee(String username, String password) {
		Employee employee = new Employee();
		try {
			connection = DatabaseConnection.createConnection();
			preparedStatement = connection.prepareStatement("SELECT * from employee WHERE username = ? AND password = ?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				employee.setFirstName(resultSet.getString(1));
				employee.setLastName(resultSet.getString(2));
				employee.setUsername(resultSet.getString(3));
				employee.setPassword(resultSet.getString(4));
				employee.setAddress(resultSet.getString(5));
				employee.setContact(resultSet.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return employee;
	}

}