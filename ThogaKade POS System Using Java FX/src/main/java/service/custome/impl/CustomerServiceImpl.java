package service.custome.impl;

import DB.DBConnection;
import Models.Customer;
import repository.DaoFactory;
import repository.custom.CustomerDao;
import service.custome.CustomerService;
import util.DaoType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);

    private static CustomerServiceImpl instance;

    public CustomerServiceImpl() {
    }

    public static CustomerServiceImpl getInstance() {
        return instance == null ? instance = new CustomerServiceImpl() : instance;
    }

    public List<Customer> getAll() {
       return customerDao.getAll();
    }
    @Override
    public boolean saveCustomer(Customer customer) throws SQLException {
        return customerDao.save(customer);
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException {
        return customerDao.update(customer);
    }
    @Override
    public boolean deleteCustomer(String cusId) throws SQLException {
        return customerDao.delete(cusId);
    }

    @Override
    public Customer searchCustomer(String customerid) throws SQLException {
       return customerDao.search(customerid);
    }


    public String generateId() throws SQLException {
      return customerDao.generateId();
    }


    public  ArrayList<String> getAllCustomerIds() throws SQLException {
        return customerDao.getAllCustomerIds();
    }

    public Customer searchCustomerforOrderForm(String customerid) throws SQLException {
       return customerDao.searchCustomerforOrderForm(customerid);
    }

}
