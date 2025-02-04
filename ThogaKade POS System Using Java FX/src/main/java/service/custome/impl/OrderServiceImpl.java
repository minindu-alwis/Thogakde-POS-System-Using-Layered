package service.custome.impl;

import DB.DBConnection;
import Models.Order;
import service.ServiceFactory;
import service.custome.ItemService;
import service.custome.OrderService;
import util.ServiceType;

import java.sql.*;

public class OrderServiceImpl implements OrderService {

    ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.ITEM);

    private static OrderServiceImpl instance;

    public static OrderServiceImpl getInstance() {
        return instance == null ? instance = new OrderServiceImpl() : instance;
    }

    @Override
    public String getLastOrderId() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT id FROM Orders ORDER BY id DESC LIMIT 1");
        return rst.next() ? rst.getString("id") : null;
    }

    @Override
    public boolean placeOrder(Order order) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement("Insert into Orders values(?,?,?)");
            stm.setObject(1, order.getId());
            stm.setObject(2, order.getDate());
            stm.setObject(3, order.getCustomerId());
            boolean isAddedOrder = stm.executeUpdate() > 0;
            if (isAddedOrder) {
                boolean addOrderDetails = OrderDetailServiceImpl.getInstance().addOrderDetail(order.getOrderDetailList());
                if (addOrderDetails) {
                    boolean updateStock = itemService.updateStock(order.getOrderDetailList());
                    if (updateStock) {
                        connection.commit();
                        return true;

                    }
                }
            }
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

}
