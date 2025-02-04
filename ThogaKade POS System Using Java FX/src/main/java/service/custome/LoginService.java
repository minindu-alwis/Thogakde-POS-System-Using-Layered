package service.custome;

import service.SuperService;

public interface LoginService extends SuperService {

    boolean authenticateUser(String userName, String password);


}
