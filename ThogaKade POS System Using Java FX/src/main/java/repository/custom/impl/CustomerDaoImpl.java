package repository.custom.impl;

import DB.DBConnection;
import Models.Customer;
import repository.custom.CustomerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(Customer entity) throws SQLException {
        String sql = "INSERT INTO customer (id, name, address, salary) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Set parameters
            preparedStatement.setString(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setString(3, entity.getAddress());
            preparedStatement.setDouble(4, entity.getSalary());


            int result = preparedStatement.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean update(Customer entity) throws SQLException {
        PreparedStatement prepareStm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE customer SET name=?,address=?,salary=? WHERE id=?");
        prepareStm.setString(1, entity.getName());
        prepareStm.setString(2, entity.getAddress());
        prepareStm.setDouble(3, entity.getSalary());
        prepareStm.setString(4, entity.getId());
        return prepareStm.executeUpdate() > 0;
    }

    @Override
    public Customer search(String s) throws SQLException {
        ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT name,address,salary FROM customer WHERE id='" + s + "'");
        if (rst.next()) {
            return new Customer(null, rst.getString("name"), rst.getString("address"), rst.getDouble("salary"));
        }
        return null;
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("DELETE FROM customer WHERE id='" + s + "'") > 0;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customerList = new ArrayList<>();
        ResultSet rst = null;
        try {
            rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("Select * from Customer");
            while (rst.next()) {
                String id = rst.getString(1);
                String name = rst.getString(2);
                String address = rst.getString(3);
                double balance = rst.getDouble(4);
                customerList.add(new Customer(id, name, address, balance));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching customers from database", e);
        } finally {
            if (rst != null) {
                try {
                    rst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return customerList;
    }

    @Override
    public String generateId() throws SQLException {
        ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT id FROM customer ORDER BY id DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString("id");
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllCustomerIds() throws SQLException {
        ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("Select id From Customer");
        ArrayList<String> cusids=new ArrayList<>();
        while(rst.next()){
            cusids.add(rst.getString(1));
        }
        return cusids;
    }

    @Override
    public Customer searchCustomerforOrderForm(String customerid) throws SQLException {
        ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT name,address,salary FROM customer WHERE id='" + customerid + "'");
        if (rst.next()) {
            return new Customer(null, rst.getString("name"), rst.getString("address"), rst.getDouble("salary"));
        }
        return null;
    }
}
