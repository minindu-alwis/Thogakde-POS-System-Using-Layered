package service.custome;

import Models.Customer;
import service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerService extends SuperService {

    List<Customer> getAll();

    boolean saveCustomer(Customer customer) throws SQLException;

    boolean updateCustomer(Customer customer) throws SQLException;

    boolean deleteCustomer(String cusId) throws SQLException;

    Customer searchCustomer(String customerid) throws SQLException;

    String generateId() throws SQLException;

    ArrayList<String> getAllCustomerIds() throws SQLException;

    Customer searchCustomerforOrderForm(String customerid) throws SQLException;
}
