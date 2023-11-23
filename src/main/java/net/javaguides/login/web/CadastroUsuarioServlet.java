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

@WebServlet("/login-jsp-servlet-jdbc/CadastroUsuarioServlet")
public class CadastroUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDao loginDao;

    public void init() {
        loginDao = new LoginDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            if (isValid(username) && isValid(password)) {
                LoginBean loginBean = new LoginBean();
                loginBean.setUsername(username);
                loginBean.setPassword(password);

                if (loginDao.cadastrar(loginBean)) {
                    request.getSession().setAttribute("successMessage", "Cadastro realizado com sucesso!");
                    response.sendRedirect("loginsucess.jsp");
                } else {
                    request.getSession().setAttribute("errorMessage", "Erro ao cadastrar usuário");
                    response.sendRedirect("cadastro.jsp");
                }
            } else {
                request.getSession().setAttribute("errorMessage", "Por favor, preencha todos os campos");
                response.sendRedirect("cadastro.jsp");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Lide com a exceção apropriadamente
        }
    }

    // Método de validação para strings não vazias
    private boolean isValid(String str) {
        return str != null && !str.isEmpty();
    }
}