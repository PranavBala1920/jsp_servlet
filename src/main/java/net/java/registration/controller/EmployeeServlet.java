package net.java.registration.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.registration.dao.EmployeeDaoImpl;
import net.java.registration.dao.EmployeeDao;
import net.java.registration.model.Employee;

@WebServlet("/register")
public class EmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private EmployeeDao employeeDao;

	@Override
	public void init() {
		employeeDao = new EmployeeDao();
	}

	public EmployeeServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String contact = request.getParameter("contact");
		String submitType = request.getParameter("submit");

		EmployeeDaoImpl connectionInterface = new EmployeeDao();
		Employee employee = connectionInterface.getEmployee(username, password);

		if (submitType.equals("login") && employee != null && employee.getUsername() != null) {
			request.setAttribute("message", employee.getUsername());
			RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
			dispatcher.forward(request, response);
		} else if (submitType.equals("register")) {
			Employee employeeRegister = new Employee();
			employeeRegister.setFirstName(firstName);
			employeeRegister.setLastName(lastName);
			employeeRegister.setUsername(username);
			employeeRegister.setPassword(password);
			employeeRegister.setContact(contact);
			employeeRegister.setAddress(address);
			try {
				employeeDao.registerEmployee(employeeRegister);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("Sucessmessage", "Registration Done, Please Login");
			request.getRequestDispatcher("employeedetails.jsp").forward(request, response);
		} else {
			request.setAttribute("Errormessage", "Data is Invalid.");
			request.getRequestDispatcher("employeelogin.jsp").forward(request, response);
		}
	}

}