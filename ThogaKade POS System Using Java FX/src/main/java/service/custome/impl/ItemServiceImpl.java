package service.custome.impl;

import DB.DBConnection;
import Models.Item;
import Models.OrderDetail;
import service.custome.ItemService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    private static ItemServiceImpl instance;

    public ItemServiceImpl() {
    }

    public static ItemServiceImpl getInstance() {
        return instance == null ? instance = new ItemServiceImpl() : instance;
    }

    @Override
    public List<Item> getAll() {
        List<Item> itemList = new ArrayList<>();
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("Select * from item");
            while (rst.next()) {
                itemList.add(new Item(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching customers from database", e);
        }
        return itemList;
    }

    @Override
    public boolean saveItem(Item item) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "INSERT INTO item (code, description, unitPrice, qtyOnHand) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, item.getCode());
        preparedStatement.setString(2, item.getDescription());
        preparedStatement.setDouble(3, item.getUnitPrice());
        preparedStatement.setInt(4, item.getQtyOnHand());

        int result = preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

        return result > 0;
    }

    @Override
    public boolean updateItem(Item item) throws SQLException {
        try {
            PreparedStatement prepareStm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE item SET description=?,unitPrice=?,qtyOnHand=? WHERE code=?");
            prepareStm.setString(1, item.getDescription());
            prepareStm.setDouble(2, item.getUnitPrice());
            prepareStm.setInt(3, item.getQtyOnHand());
            prepareStm.setString(4, item.getCode());
            return prepareStm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteItem(String itemid) {
        try {
            return DBConnection.getInstance().getConnection().createStatement().executeUpdate("DELETE FROM item WHERE code='" + itemid + "'") > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item searchItem(String itemid) {
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT description,unitPrice,qtyOnHand FROM item WHERE code='" + itemid + "'");
            if (rst.next()) {
                return new Item(null, rst.getString("description"), rst.getDouble("unitPrice"), rst.getInt("qtyOnHand"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateId(){
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1");
            if (rst.next()) {
                return rst.getString("code");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  ArrayList<String> loadAllItemCodes() throws SQLException {

        ResultSet rst=DBConnection.getInstance().getConnection().createStatement().executeQuery("select code from item");
        ArrayList<String> itemCodes=new ArrayList<>();
        while(rst.next()){
            itemCodes.add(rst.getString(1));
        }
        return itemCodes;

    }

    public Item searchItemforOrderForm(String itemid) {
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT description,unitPrice,qtyOnHand FROM item WHERE code='" + itemid + "'");
            if (rst.next()) {
                return new Item(null, rst.getString("description"), rst.getDouble("unitPrice"), rst.getInt("qtyOnHand"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateStock(ArrayList <OrderDetail>orderDetails) throws SQLException {
        for (OrderDetail orderDetail : orderDetails) {
            boolean updateStock = updateStock(orderDetail);
            if(!updateStock){
                return false;
            }
        }
        return !orderDetails.isEmpty(); //Not empty
    }
    public boolean updateStock(OrderDetail orderDetail) throws SQLException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Update Item set qtyOnHand=qtyOnHand-? where code=?");
        stm.setObject(1, orderDetail.getQty());
        stm.setObject(2, orderDetail.getItemCode());
        return stm.executeUpdate()>0;
    }



}
