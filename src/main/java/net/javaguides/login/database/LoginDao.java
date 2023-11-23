package net.javaguides.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.javaguides.login.bean.LoginBean;


public class LoginDao {
    private static final String URL = "jdbc:mysql://localhost:3306/mysql_database?user=root";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Elsamaria12!";

    public boolean validate(LoginBean loginBean) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "SELECT * FROM login WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, loginBean.getUsername());
            preparedStatement.setString(2, loginBean.getPassword());

            resultSet = preparedStatement.executeQuery();
            
            return resultSet.next(); // Retorna true se encontrar um usuário com as credenciais fornecidas
        } finally {
            // Feche as conexões e recursos no bloco finally para garantir a liberação dos recursos
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public boolean cadastrar(LoginBean loginBean) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "INSERT INTO login (username, password) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, loginBean.getUsername());
            preparedStatement.setString(2, loginBean.getPassword());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0; // Retorna true se o cadastro for bem-sucedido
        } finally {
            // Feche as conexões e recursos no bloco finally para garantir a liberação dos recursos
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}

