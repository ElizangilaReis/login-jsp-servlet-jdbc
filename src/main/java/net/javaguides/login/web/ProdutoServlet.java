package net.javaguides.login.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javaguides.login.bean.ProdutoBean;
import net.javaguides.login.database.ProdutoDao;



@WebServlet("/")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdutoDao produtoDao;
	
	
	public void init() {
		produtoDao = new ProdutoDao();
		//System.out.println(userDAO);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertUser(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateUser(request, response);
				break;
			default:
				listProduto(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listProduto(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List<ProdutoBean> listUser = produtoDao.selectAllProdutos();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("produto-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("produto-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ProdutoBean existingProduto = produtoDao.selectProduto(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("produto-form.jsp");
		request.setAttribute("produto", existingProduto);
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String fornecedor = request.getParameter("fornecedor");
		
		ProdutoBean newProduto = new ProdutoBean(name, email, fornecedor);
		produtoDao.insertProduto(newProduto);
		response.sendRedirect("list");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String fornecedor = request.getParameter("fornecedor");

		ProdutoBean book = new ProdutoBean(id, name, email, fornecedor);
		produtoDao.updateProduto(book);
		response.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		produtoDao.deleteProduto(id);
		response.sendRedirect("list");

	}

}