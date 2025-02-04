package service.custome;


import Models.User;
import service.SuperService;

import java.sql.SQLException;

public interface SignupService extends SuperService {

    boolean registerUser(User newUser) throws SQLException;

    String generateuserId();


}
