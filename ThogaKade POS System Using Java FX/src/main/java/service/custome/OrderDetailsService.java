package service.custome;

import Models.OrderDetail;
import service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsService extends SuperService {

    boolean addOrderDetail(OrderDetail orderDetail) throws SQLException;
    boolean addOrderDetail(ArrayList<OrderDetail> orderDetailList) throws SQLException;

}
