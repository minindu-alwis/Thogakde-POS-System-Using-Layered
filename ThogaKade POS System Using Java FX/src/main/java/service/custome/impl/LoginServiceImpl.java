package service.custome.impl;

import DB.DBConnection;
import org.jasypt.util.text.BasicTextEncryptor;
import service.custome.LoginService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServiceImpl implements LoginService {

    private static LoginServiceImpl instance;

    private LoginServiceImpl() {
    }

    public static LoginServiceImpl getInstance() {
        return instance == null ? instance = new LoginServiceImpl() : instance;
    }


    @Override
    public boolean authenticateUser(String userName, String password) {
        try {
            String sql = "SELECT password FROM users WHERE email = ?";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);

            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                String encryptedPassword = rst.getString("password");
                String key = "12345";
                BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
                basicTextEncryptor.setPassword(key);
                String decryptpassword = basicTextEncryptor.decrypt(encryptedPassword);

                return password.equals(decryptpassword);
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
