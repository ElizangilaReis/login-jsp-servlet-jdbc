package net.javaguides.cadastro.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.cadastro.bean.ProdutoBean;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * 
 *
 */
public class ProdutoDao {
	// private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcURL = "jdbc:mysql://localhost:3306/mysql_database?user=root";
	private String jdbcUsername = "root";
	private String jdbcPassword = "Elsamaria12!";

	private static final String INSERT_USERS_SQL = "INSERT INTO produto" + "  (name, email, fornecedor) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "select id,name,email,fornecedor from produto where id =?";
	private static final String SELECT_ALL_USERS = "select * from produto";
	private static final String DELETE_USERS_SQL = "delete from produto where id = ?;";
	private static final String UPDATE_USERS_SQL = "update produto set name = ?,email= ?, fornecedor =? where id = ?;";

	public ProdutoDao() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertProduto(ProdutoBean produto) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
			
			preparedStatement.setString(1, produto.getName());
			preparedStatement.setString(2, produto.getEmail());
			preparedStatement.setString(3, produto.getFornecedor());
			
			// System.out.println(preparedStatement);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public ProdutoBean selectProduto(int id) {
		ProdutoBean produto = null;
		try {
			// Step 1: Establishing a Connection
			Connection connection = getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
			preparedStatement.setInt(1, id);
			// System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String fornecedor = rs.getString("fornecedor");
				produto = new ProdutoBean(id, name, email, fornecedor);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return produto;
	}

	public List<ProdutoBean> selectAllProdutos() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<ProdutoBean> produtos = new ArrayList<>();
		try {
			// Step 1: Establishing a Connection
			Connection connection = getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
			// System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String fornecedor = rs.getString("fornecedor");
				produtos.add(new ProdutoBean(id, name, email, fornecedor));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return produtos;
	}

	public boolean deleteProduto(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateProduto(ProdutoBean produto) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, produto.getName());
			statement.setString(2, produto.getEmail());
			statement.setString(3, produto.getFornecedor());
			statement.setInt(4, produto.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
