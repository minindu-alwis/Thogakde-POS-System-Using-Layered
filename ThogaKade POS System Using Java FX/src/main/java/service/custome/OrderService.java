package service.custome;

import service.SuperService;
import Models.Order;

import java.sql.SQLException;



public interface OrderService extends SuperService{

    String getLastOrderId() throws SQLException;
    boolean placeOrder(Order order) throws SQLException;

}
