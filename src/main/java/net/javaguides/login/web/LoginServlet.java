package net.javaguides.login.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.javaguides.login.bean.LoginBean;
import net.javaguides.login.database.LoginDao;

/**
 * @email 
 */

@WebServlet("/")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDao loginDao;

    public void init() {
      loginDao = new LoginDao(); 
    	
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
                LoginBean loginBean = new LoginBean();
                loginBean.setUsername(username);
                loginBean.setPassword(password);

                if (loginDao.validate(loginBean)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    response.sendRedirect("loginsuccess.jsp");
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("error", "Invalid username or password");
                    response.sendRedirect("login.jsp");
                }
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("error", "Please provide username and password");
                response.sendRedirect("login.jsp");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Trate a exceção apropriadamente, redirecionando para página de erro ou realizando o tratamento específico

        
        } 

    }
    
    
    
    
    
    
    
    
}