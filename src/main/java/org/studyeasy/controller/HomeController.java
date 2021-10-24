package org.studyeasy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.studyeasy.entity.User;
import org.studyeasy.model.UsersModel;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/project")
	private DataSource dataSource;
	
    public HomeController() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		page = page.toLowerCase();
		
		switch(page) {
			case "home":
				request.setAttribute("title", "Homepage");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				break;
			case "listusers":
				List<User> listUsers = new ArrayList<>();
				listUsers = new UsersModel().listUsers(dataSource);
				request.setAttribute("listUsers", listUsers);
				request.setAttribute("title", "List of users");
				request.getRequestDispatcher("listUser.jsp").forward(request, response);
			default:
				request.setAttribute("title", "Error page");
				request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
