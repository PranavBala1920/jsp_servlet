package net.java.registration.dao;

import net.java.registration.model.Employee;

public interface EmployeeDaoImpl {

	public Employee getEmployee(String username, String password);

}